package com.epam.jwd.hotel_booking.dao.impl;

import com.epam.jwd.hotel_booking.connections.ConnectionPool;
import com.epam.jwd.hotel_booking.connections.ProxyConnection;
import com.epam.jwd.hotel_booking.dao.CommonDao;
import com.epam.jwd.hotel_booking.dao.EnumDao;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.OrderSearchPattern;
import com.epam.jwd.hotel_booking.model.Room;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OrderDao extends CommonDao<Order> {

    public static final String SQL_SET_ORDER_STATUS
            = "UPDATE orders SET status_id=? where id=?";
    public static final String SQL_CHECK_AND_SET_OUTOFDAY_STATUS
            = "UPDATE  orders SET status_id=? WHERE date_in<? AND (status_id=? OR status_id=?)";
    public static final String SQL_CHECK_AND_SET_NOTCLOSED_STATUS
            = "UPDATE  orders SET status_id=? WHERE date_out<? AND status_id=?";
    private static final String SQL_START_TRANSACTION
            = "START TRANSACTION";
    private static final String SQL_COMMIT_TRANSACTION
            = "COMMIT";
    private static final String SQL_ROLLBACK_TRANSACTION
            = "ROLLBACK";
    private static final String SQL_CREATE_ORDER
            = "INSERT INTO orders (status_id, date_in, date_out) VALUES (?, ?, ?)";
    private static final String SQL_CREATE_RELATION_ORDER_WITH_CLIENTS
            = "INSERT INTO users_orders (user_id, order_id, food_plan_id) VALUES (?, ?, ?)";
    private static final String SQL_CREATE_RELATION_ORDER_WITH_ROOMS
            = "INSERT INTO orders_rooms (order_id, room_id) VALUES (?, ?)";
    public static final String SQL_DELETE_RELATION_ORDER_WITH_ROOMS
            = "DELETE FROM orders_rooms WHERE order_id=?";
    private static final String SQL_SELECT_INSERT_ID
            = "SELECT LAST_INSERT_ID()";
    private static final String SELFROM_BLOCK
            = "SELECT orders.id, orders.date_in, orders.date_out, statuses.status,logins.login " +
            "FROM logins, logins_users, users, users_orders, orders, statuses ";
    private static final String WHEREAND_BLOCK
            = "WHERE logins.id=logins_users.login_id " +
            "AND logins_users.user_id=users.id " +
            "AND users.id=users_orders.user_id " +
            "AND users_orders.order_id=orders.id " +
            "AND orders.status_id=statuses.id ";
    private static final String DATES_BLOCK
            = "AND orders.date_out>? " +
            "AND orders.date_in<? ";
    private static final String GROUP_AND_SORT_BLOCK
            = "GROUP BY orders.id ORDER BY orders.id ASC";
    private static final String SQL_SELECT_ORDERS_BY_FILTER
            = SELFROM_BLOCK +
            WHEREAND_BLOCK +
            "AND orders.id LIKE ? " +
            "AND users.id LIKE ? " +
            "AND logins.login LIKE ? " +
            "AND users.name LIKE ? " +
            "AND users.sname LIKE ? " +
            "AND statuses.status LIKE ? " +
            DATES_BLOCK +
            GROUP_AND_SORT_BLOCK;
    public static final String SQL_SELECT_BY_ID
            = SELFROM_BLOCK +
            WHEREAND_BLOCK +
            "AND orders.id=? " +
            GROUP_AND_SORT_BLOCK;

    private static final String ID_COLUMN = "orders.id";
    private static final String DATE_IN_COLUMN = "orders.date_in";
    private static final String DATE_OUT_COLUMN = "orders.date_out";
    private static final String STATUS_COLUMN = "statuses.status";
    private static final String RELATED_LOGIN_COLUMN = "logins.login";

    Logger logger = LoggerFactory.getLogger(OrderDao.class);

    @Override
    public Optional<List<Order>> findByPattern(String strIncl) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> findEntityByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Order entity) {
        return false;
    }

    @Override
    public boolean create(Order entity) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection()) {
            startTransactionWithConnection(cn);
            recordOrderWithConnection(entity, cn);
            recordClientsOfOrderWithConnection(entity, cn);
            recordRoomsOfOrderWithConnection(entity, cn);
            commitTransactionWithConnection(cn);
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<List<Order>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Order> findEntityById(long id) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_ID)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            List<Order> orders = completeOrdersWithRelatedRoomsAndClients(rs);
            return Optional.of(orders.get(0));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Order> update(Order entity) {
        return Optional.empty();
    }


    private Order readOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong(ID_COLUMN));
        order.setDateIn(rs.getDate(DATE_IN_COLUMN).toLocalDate());
        order.setDateOut(rs.getDate(DATE_OUT_COLUMN).toLocalDate());
        order.setStatus(OrderStatus.valueOf(rs.getString(STATUS_COLUMN)));
        order.setRelatedLogin(rs.getString(RELATED_LOGIN_COLUMN));
        return order;
    }

    public Optional<List<Order>> findByOrderPattern(OrderSearchPattern p) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection()) {
            PreparedStatement st = cn.prepareStatement(SQL_SELECT_ORDERS_BY_FILTER);
            st.setString(1, p.getOrderIdForSQL());
            st.setString(2, p.getClientIdForSql());
            st.setString(3, p.getLoginForSQL());
            st.setString(4, p.getClientNameForSQL());
            st.setString(5, p.getClientSNameForSQL());
            st.setString(6, p.getStatusForSQL());
            st.setDate(7, p.getDateFromForSQL());
            st.setDate(8, p.getDateBeforeForSQL());
            ResultSet rs = st.executeQuery();
            List<Order> orders = completeOrdersWithRelatedRoomsAndClients(rs);
            return Optional.of(orders);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    private List<Order> completeOrdersWithRelatedRoomsAndClients(ResultSet rs) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            orders.add(readOrder(rs));
        }
        for (Order order : orders) {
            addRelatedClients(order);
            addRelatedRooms(order);
        }
        return orders;
    }

    private void addRelatedRooms(Order order) {
        RoomDao roomDao = new RoomDao();
        Optional<List<Room>> optionalRooms = roomDao.findAllRelatedToOrderId(order.getId());
        if (optionalRooms.isPresent()) {
            for (Room room : optionalRooms.get()) {
                order.addRoom(room);
            }
        }
    }

    private void addRelatedClients(Order order) {
        ClientDao clientDao = new ClientDao();
        Optional<List<Client>> optionalClients = clientDao.findAllRelatedToOrderId(order.getId());
        if (optionalClients.isPresent()) {
            for (Client client : optionalClients.get()) {
                order.addUserWithFoodPlan(client, clientDao.getFoodplanByClientIdAndOrderId(client.getId(), order.getId()));
            }
        }
    }

    private void recordRoomsOfOrderWithConnection(Order entity, ProxyConnection cn) throws SQLException {
        for (Room room : entity.getRooms()) {
            int sizeId = EnumDao.getRoomSizeIdByRoomSizeWithConnection(room.getSize(), cn);
            int typeId = EnumDao.getRoomTypeIdByRoomTypeWithConnection(room.getType(), cn);
            int roomId = room.getId() < 0
                    ?
                    EnumDao.getSubZeroRoomIdByRoomSizeIdAndTypeIdWithConnection(sizeId, typeId, cn)
                    :
                    room.getId();

            PreparedStatement st = cn.prepareStatement(SQL_CREATE_RELATION_ORDER_WITH_ROOMS);
            st.setLong(1, entity.getId());
            st.setLong(2, roomId);
            st.execute();
        }
    }

    private void updateRoomsOfOrderWithConnection(Order entity, ProxyConnection cn) throws SQLException {
        PreparedStatement st = cn.prepareStatement(SQL_DELETE_RELATION_ORDER_WITH_ROOMS);
        st.setLong(1, entity.getId());
        st.execute();
        recordRoomsOfOrderWithConnection(entity, cn);
    }

    private void recordClientsOfOrderWithConnection(Order entity, ProxyConnection cn) throws SQLException {
        for (Client client : entity.getClients().keySet()) {
            int foodPlanId = EnumDao.getFoodplanIdByFoodplanWithConnection(entity.getClients().get(client), cn);
            PreparedStatement st = cn.prepareStatement(SQL_CREATE_RELATION_ORDER_WITH_CLIENTS);
            st.setLong(1, client.getId());
            st.setLong(2, entity.getId());
            st.setLong(3, foodPlanId);
            st.execute();
        }
    }

    private void startTransactionWithConnection(ProxyConnection cn) throws SQLException {
        PreparedStatement st = cn.prepareStatement(SQL_START_TRANSACTION);
        st.execute();
    }

    private void commitTransactionWithConnection(ProxyConnection cn) throws SQLException {
        PreparedStatement st = cn.prepareStatement(SQL_COMMIT_TRANSACTION);
        st.execute();
    }

    private void rollbackTransactionWithConnection(ProxyConnection cn) throws SQLException {
        PreparedStatement st = cn.prepareStatement(SQL_ROLLBACK_TRANSACTION);
        st.execute();
    }

    private void recordOrderWithConnection(Order entity, ProxyConnection cn) throws SQLException {
        int statusId = EnumDao.getOrderStatusIdByStatusWithConnection(entity.getStatus(), cn);
        PreparedStatement st = cn.prepareStatement(SQL_CREATE_ORDER);
        st.setInt(1, statusId);
        st.setDate(2, Date.valueOf(entity.getDateIn()));
        st.setDate(3, Date.valueOf(entity.getDateOut()));
        st.executeUpdate();
        entity.setId(getLastInsertedRecordId(cn));
    }

    private long getLastInsertedRecordId(ProxyConnection cn) throws SQLException {
        PreparedStatement st = cn.prepareStatement(SQL_SELECT_INSERT_ID);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        } else {
            throw new SQLException();
        }
    }

    public boolean setStatus(long orderId, OrderStatus status) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection()) {
            int statusId = EnumDao.getOrderStatusIdByStatusWithConnection(status, cn);
            PreparedStatement st = cn.prepareStatement(SQL_SET_ORDER_STATUS);
            st.setInt(1, statusId);
            st.setLong(2, orderId);
            st.execute();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public void checkAndSetOutofdayAndNotClosedStatus() {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection()) {
            int outOfDateStatusID = EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.OUT_OF_DATE, cn);
            int awaitingStatusID = EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.AWAITING, cn);
            int confirmedStatusID = EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.CONFIRMED, cn);
            int activeStatusID = EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.ACTIVE, cn);
            int notClosedStatusID = EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.NOT_CLOSED, cn);
            LocalDate dateNow = LocalDate.now();
            PreparedStatement st = cn.prepareStatement(SQL_CHECK_AND_SET_OUTOFDAY_STATUS);
            st.setInt(1, outOfDateStatusID);
            st.setDate(2, Date.valueOf(dateNow));
            st.setInt(3, awaitingStatusID);
            st.setInt(4, confirmedStatusID);
            st.execute();
            st = cn.prepareStatement(SQL_CHECK_AND_SET_NOTCLOSED_STATUS);
            st.setInt(1, notClosedStatusID);
            st.setDate(2, Date.valueOf(dateNow));
            st.setInt(3, activeStatusID);
            st.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public boolean updateRooms(Order entity) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();) {
            updateRoomsOfOrderWithConnection(entity, cn);
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

}
