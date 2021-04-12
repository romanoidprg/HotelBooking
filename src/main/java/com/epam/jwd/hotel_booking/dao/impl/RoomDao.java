package com.epam.jwd.hotel_booking.dao.impl;

import com.epam.jwd.hotel_booking.connections.ConnectionPool;
import com.epam.jwd.hotel_booking.connections.ProxyConnection;
import com.epam.jwd.hotel_booking.dao.CommonDao;
import com.epam.jwd.hotel_booking.dao.EnumDao;
import com.epam.jwd.hotel_booking.model.Room;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;
import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDao extends CommonDao<Room> {


    private static final String SQL_SELECT_BY_ORDER_ID
            = "SELECT rooms.id, room_sizes.size, room_types.type " +
            "FROM rooms INNER JOIN orders_rooms ON (orders_rooms.room_id=rooms.id), " +
            "room_sizes, room_types " +
            "WHERE order_id=? AND rooms.size_id=room_sizes.id AND rooms.type_id=room_types.id";
    public static final String SQL_SELECT_EMPTY_ROOMS_FOR_PERIOD
            = "SELECT rooms.id, room_sizes.size, room_types.type FROM rooms, room_sizes, room_types " +
            "WHERE rooms.id NOT IN " +
            "(SELECT room_id FROM orders_rooms LEFT JOIN orders ON (orders_rooms.order_id=orders.id) " +
            "WHERE (orders.status_id=? OR orders.status_id=? OR orders.status_id=? OR orders_rooms.room_id<0) " +
            "AND ((? BETWEEN orders.date_in AND orders.date_out) " +
            "OR (? BETWEEN orders.date_in AND orders.date_out) " +
            "or ((orders.date_in BETWEEN ? AND ?) AND (orders.date_out BETWEEN ? AND ?) )) " +
            "GROUP BY room_id) " +
            "AND rooms.id>0 AND rooms.size_id=room_sizes.id AND rooms.type_id=room_types.id ORDER BY rooms.id";
    public static final String SQL_SELECT_BY_ID
            = "SELECT rooms.id AS id, room_sizes.size, room_types.type " +
            "FROM rooms, room_sizes, room_types " +
            "WHERE rooms.id=? AND rooms.size_id=room_sizes.id AND rooms.type_id=room_types.id";
    private static final String ID_COLUMN = "id";
    private static final String SIZE_COLUMN = "size";
    private static final String TYPE_COLUMN = "type";


    Logger logger = LogManager.getLogger(RoomDao.class);

    private Room readRoom(ResultSet rs) throws SQLException {
        return new Room(rs.getInt(ID_COLUMN),
                RoomSize.valueOf(rs.getString(SIZE_COLUMN)),
                RoomType.valueOf(rs.getString(TYPE_COLUMN)));
    }

    @Override
    public Optional<List<Room>> findAll() {
        return Optional.empty();
    }

    public Optional<List<Room>> findAllRelatedToOrderId(long orderId) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_ORDER_ID)) {
            st.setLong(1, orderId);
            ResultSet rs = st.executeQuery();
            List<Room> rooms = new ArrayList<>();
            while (rs.next()) {
                rooms.add(readRoom(rs));
            }
            return Optional.of(rooms);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<Room>> findEmptyForPeriod(LocalDate dateIn, LocalDate dateOut) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_EMPTY_ROOMS_FOR_PERIOD)) {
            int activeStatusId = EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.ACTIVE, cn);
            int awaitingStatusId = EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.AWAITING, cn);
            int confirmedStatusId = EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.CONFIRMED, cn);
            st.setInt(1, activeStatusId);
            st.setInt(2, awaitingStatusId);
            st.setInt(3, confirmedStatusId);
            st.setDate(4, Date.valueOf(dateIn));
            st.setDate(5, Date.valueOf(dateOut));
            st.setDate(6, Date.valueOf(dateIn));
            st.setDate(7, Date.valueOf(dateOut));
            st.setDate(8, Date.valueOf(dateIn));
            st.setDate(9, Date.valueOf(dateOut));
            ResultSet rs = st.executeQuery();
            List<Room> rooms = new ArrayList<>();
            while (rs.next()) {
                rooms.add(readRoom(rs));
            }
            return Optional.of(rooms);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Room> findEntityById(long id) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_ID)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return Optional.of(readRoom(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Room>> findByPattern(String strIncl) {
        return Optional.empty();
    }

    @Override
    public Optional findEntityByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean create(Room entity) {
        return false;
    }

    @Override
    public Optional<Room> update(Room entity) {
        return Optional.empty();
    }

}
