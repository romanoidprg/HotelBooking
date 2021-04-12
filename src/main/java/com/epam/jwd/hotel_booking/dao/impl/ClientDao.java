package com.epam.jwd.hotel_booking.dao.impl;

import com.epam.jwd.hotel_booking.connections.ConnectionPool;
import com.epam.jwd.hotel_booking.connections.ProxyConnection;
import com.epam.jwd.hotel_booking.dao.CommonDao;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.ClientSearchPattern;
import com.epam.jwd.hotel_booking.model.Login;
import com.epam.jwd.hotel_booking.model.enums.FoodPlan;
import com.epam.jwd.hotel_booking.model.enums.Sex;
import com.epam.jwd.hotel_booking.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class  ClientDao extends CommonDao<Client> {

    private static final String SQL_CREATE_CLIENT
            = "INSERT INTO users (name, sname, email, phone, bday, sex, country_id, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_CREATE_LINK_TO_LOGIN
            = "INSERT INTO logins_users (login_id, user_id) VALUES (?, ?)";
    public static final String SQL_SELECT_ALL_LINKED_TO_LOGIN_NAME
            = "SELECT users.id, users.name, users.sname, users.email, users.phone, " +
            "users.bday, users.sex, countries.name AS country, users.address " +
            "FROM ((logins LEFT JOIN logins_users ON logins.id=logins_users.login_id) " +
            "LEFT JOIN users ON logins_users.user_id=users.id), countries " +
            "WHERE logins.login=? and users.country_id=countries.id";
    public static final String SQL_DELETE_CLIENT_BY_ID_1
            = "DELETE FROM logins_users WHERE user_id=?";
    public static final String SQL_DELETE_CLIENT_BY_ID_2
            = "DELETE FROM users WHERE id=?";
    private static final String SQL_SELECT_FOODPLAN_BY_CLIENT_ID_AND_ORDER_ID
            = "SELECT food_plans.plan FROM users_orders LEFT JOIN food_plans " +
            "ON (food_plans.id=users_orders.food_plan_id) WHERE user_id=? AND order_id=?";
    private static final String SQL_SELECT_BY_ORDER_ID
            = "SELECT users.id, users.name, users.sname, users.email, users.phone, " +
            "users.bday, users.sex, countries.name AS country, users.address " +
            "FROM users INNER JOIN users_orders ON (users_orders.user_id=users.id), countries " +
            "WHERE order_id=? AND users.country_id=countries.id";
    private static final String SQL_LAST_INSER_ID = "SELECT LAST_INSERT_ID()";
    private static final String SQL_SELECT_PATTERN
            = "SELECT users.id, users.name, users.sname, users.email, users.phone, " +
            "users.bday, users.sex, countries.name AS country, users.address " +
            "FROM users, countries " +
            "WHERE (users.name LIKE ?) AND (sname LIKE ?) AND (email LIKE ?)" +
            "AND (phone LIKE ?) AND (bday BETWEEN ? AND ?) AND (sex LIKE ?) AND (countries.name LIKE ?)" +
            "AND (address LIKE ?) " +
            "AND (users.country_id=countries.id)";
    private static final String SQL_SELECT_BY_ID
            = "SELECT users.id, users.name, users.sname, users.email, " +
            "users.phone, users.bday, users.sex, countries.name AS country, users.address " +
            "FROM users, countries WHERE users.id=? AND users.country_id=countries.id";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String SNAME_COLUMN = "sname";
    private static final String EMAIL_COLUMN = "email";
    private static final String PHONE_COLUMN = "phone";
    private static final String BDAY_COLUMN = "bday";
    private static final String SEX_COLUMN = "sex";
    private static final String COUNTRY_COLUMN = "country";
    private static final String ADDRESS_COLUMN = "address";

    private final Logger logger = LoggerFactory.getLogger(ClientDao.class);


    private Client readClient(ResultSet rs) throws SQLException {
        return new Client(rs.getLong(ID_COLUMN),
                rs.getString(NAME_COLUMN),
                rs.getString(SNAME_COLUMN),
                rs.getString(EMAIL_COLUMN),
                rs.getString(PHONE_COLUMN),
                rs.getDate(BDAY_COLUMN).toLocalDate(),
                Sex.fromString(rs.getString(SEX_COLUMN)),
                rs.getString(COUNTRY_COLUMN),
                rs.getString(ADDRESS_COLUMN));

    }

    @Override
    public Optional<List<Client>> findAll() {
        return Optional.empty();
    }

    public Optional<List<Client>> findAllLinkedToLoginName(String loginName) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_ALL_LINKED_TO_LOGIN_NAME)) {
            st.setString(1, loginName);
            ResultSet rs = st.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (rs.next()) {
                clients.add(readClient(rs));
            }
            return Optional.of(clients);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<Client>> findAllRelatedToOrderId(long orderId) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_ORDER_ID)) {
            st.setLong(1, orderId);
            ResultSet rs = st.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (rs.next()) {
                clients.add(readClient(rs));
            }
            return Optional.of(clients);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    public FoodPlan getFoodplanByClientIdAndOrderId(long clientId, long orderId) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_FOODPLAN_BY_CLIENT_ID_AND_ORDER_ID)) {
            st.setLong(1, clientId);
            st.setLong(2, orderId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return FoodPlan.ofString(rs.getString(1));
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return FoodPlan.NONE;
        }
    }

    @Override
    public Optional<List<Client>> findByPattern(String strIncl) {
        return Optional.empty();
    }

    public Optional<List<Client>> findByClientPattern(ClientSearchPattern cp) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_PATTERN);) {
            st.setString(1, "%" + cp.getName() + "%");
            st.setString(2, "%" + cp.getsName() + "%");
            st.setString(3, "%" + cp.geteMail() + "%");
            st.setString(4, "%" + cp.getPhone() + "%");
            st.setDate(5, Date.valueOf(cp.getBirthDay()));
            st.setDate(6, Date.valueOf(cp.getBirthDayTo()));
            st.setString(7, (cp.getSex() == Sex.THING ? "%" : cp.getSex().name()));
            st.setString(8, "%" + cp.getCountry() + "%");
            st.setString(9, "%" + cp.getAddress() + "%");
            ResultSet rs = st.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (rs.next()) {
                clients.add(readClient(rs));
            }
            return Optional.of(clients);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Client> findEntityByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> findEntityById(long id) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_ID);) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            Client client = null;
            if (rs.next()) {
                client = readClient(rs);
            }
            return Optional.ofNullable(client);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(long id) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection()) {
            boolean result = true;
            PreparedStatement st = cn.prepareStatement(SQL_DELETE_CLIENT_BY_ID_1);
            st.setLong(1, id);
            result &= st.execute();
            st = cn.prepareStatement(SQL_DELETE_CLIENT_BY_ID_2);
            st.setLong(1, id);
            result &= st.execute();
            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean create(Client entity) {
        try {
            ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
            PreparedStatement st = cn.prepareStatement(SQL_CREATE_CLIENT);
            st.setString(1, entity.getName());
            st.setString(2, entity.getsName());
            st.setString(3, entity.geteMail());
            st.setString(4, entity.getPhone());
            st.setDate(5, Date.valueOf(entity.getBirthDay().format(DateTimeFormatter.ISO_LOCAL_DATE)));
            st.setString(6, entity.getSex().name());
            st.setInt(7, CountryService.getCountryId(entity.getCountry()));
            st.setString(8, entity.getAddress());
            st.execute();
            st = cn.prepareStatement(SQL_LAST_INSER_ID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getLong(1));
            } else {
                throw new SQLException();
            }

            st.close();
            cn.close();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean createLinkToLogin(Client entity, Login login) {
        long clientId = entity.getId();
        try {
            ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
            PreparedStatement st = cn.prepareStatement(SQL_CREATE_LINK_TO_LOGIN);
            st.setLong(1, login.getId());
            st.setLong(2, clientId);
            st.execute();
            st.close();
            cn.close();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Client> update(Client entity) {
        return Optional.empty();
    }
}
