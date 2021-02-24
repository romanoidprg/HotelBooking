package com.epam.jwd.hotel_booking.dao.impl;

import com.epam.jwd.hotel_booking.connections.ConnectionPool;
import com.epam.jwd.hotel_booking.connections.ProxyConnection;
import com.epam.jwd.hotel_booking.dao.CommonDao;
import com.epam.jwd.hotel_booking.model.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginDao extends CommonDao<Login> {

    private static final String SQL_SELECT_ALL
            = "SELECT logins.*, admins.id AS adm_id FROM logins LEFT JOIN admins ON logins.id=admins.login_id";
    private static final String SQL_SELECT_BY_NAME
            = SQL_SELECT_ALL + " WHERE login=?";
    private static final String SQL_SELECT_BY_ID
            = SQL_SELECT_ALL + " WHERE logins.id=?";
    private static final String SQL_UPDATE_LOGINS_BY_ID
            = "UPDATE logins SET login=?, password=? WHERE logins.id=?";
    private static final String SQL_ADD_ADMIN_BY_LOGIN_ID
            = "INSERT INTO admins (login_id) VALUES (?)";
    private static final String SQL_DEL_ADMIN_BY_LOGIN_ID
            = "DELETE FROM admins WHERE login_id=?";

    private static final String ID_COLUMN_NAME = "id";
    private static final String LOGIN_COLUMN_NAME = "login";
    private static final String PASSWORD_COLUMN_NAME = "password";
    private static final String ADM_ID_COLUMN_NAME = "adm_id";

    private final Logger logger = LoggerFactory.getLogger(LoginDao.class);


    private Login readLogin(ResultSet rs) throws SQLException {
        return new Login(rs.getLong(ID_COLUMN_NAME),
                rs.getString(LOGIN_COLUMN_NAME),
                rs.getString(PASSWORD_COLUMN_NAME),
                rs.getBoolean(ADM_ID_COLUMN_NAME));

    }

    @Override
    public Optional<List<Login>> findAll() {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(SQL_SELECT_ALL)) {
            List<Login> logins = new ArrayList<>();
            while (rs.next()) {
                logins.add(readLogin(rs));
            }
            return Optional.of(logins);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Login> findEntityByName(String name) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_NAME);
        ) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            Login login = rs.next() ? readLogin(rs) : null;
            rs.close();
            return Optional.ofNullable(login);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Login> findEntityById(long id) {
        try {
            ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
            PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_ID);
            st.setString(1, String.valueOf(id));
            ResultSet rs = st.executeQuery();
            rs.next();
            Login login = readLogin(rs);

            rs.close();
            st.close();
            cn.close();
            return Optional.ofNullable(login);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Login entity) {
        return false;
    }

    @Override
    public boolean create(Login entity) {
        return false;
    }

    @Override
    public Optional<Login> update(Login entity) {
        try {
            ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
            PreparedStatement st = cn.prepareStatement(SQL_UPDATE_LOGINS_BY_ID);
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
            st.setLong(3, entity.getId());
            st.executeUpdate();

            st = entity.isAdmin() ? cn.prepareStatement(SQL_ADD_ADMIN_BY_LOGIN_ID)
                    : cn.prepareStatement(SQL_DEL_ADMIN_BY_LOGIN_ID);
            st.setLong(1, entity.getId());
            st.executeUpdate();
            st.close();
            cn.close();
            return Optional.of(entity);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }
}
