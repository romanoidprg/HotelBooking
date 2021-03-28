package com.epam.jwd.hotel_booking.dao.impl;

import com.epam.jwd.hotel_booking.connections.ConnectionPool;
import com.epam.jwd.hotel_booking.connections.ProxyConnection;
import com.epam.jwd.hotel_booking.dao.CommonDao;
import com.epam.jwd.hotel_booking.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryDao extends CommonDao<Country> {

    private static final String SQL_SELECT_ALL
            = "SELECT * FROM countries";
    private static final String SQL_SELECT_BY_NAME
            = "SELECT * FROM countries WHERE name=?";

    private static final String ID_COLUMN_NAME = "id";
    private static final String NAME_COLUMN_NAME = "name";

    private final Logger logger = LoggerFactory.getLogger(CountryDao.class);


    private Country readCountry(ResultSet rs) throws SQLException {
        return new Country(rs.getInt(ID_COLUMN_NAME), rs.getString(NAME_COLUMN_NAME));
    }

    @Override
    public Optional<List<Country>> findAll() {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(SQL_SELECT_ALL)) {
            List<Country> countries = new ArrayList<>();
            while (rs.next()) {
                countries.add(readCountry(rs));
            }
            return Optional.of(countries);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }


    @Override
    public Optional<List<Country>> findByPattern(String strIncl) {
//        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
//             PreparedStatement st = cn.prepareStatement(SQL_SELECT_PATTERN);) {
//            st.setString(1, "%" + strIncl + "%");
//            ResultSet rs = st.executeQuery();
//            List<Login> logins = new ArrayList<>();
//            while (rs.next()) {
//                logins.add(readLogin(rs));
//            }
//            return Optional.of(logins);
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//            return Optional.empty();
//        }
        return Optional.empty();
    }

    @Override
    public Optional<Country> findEntityByName(String name) {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_NAME);
        ) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            Country country = rs.next() ? readCountry(rs) : null;
            rs.close();
            return Optional.ofNullable(country);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Country> findEntityById(long id) {
//        try {
//            ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
//            PreparedStatement st = cn.prepareStatement(SQL_SELECT_BY_ID);
//            st.setString(1, String.valueOf(id));
//            ResultSet rs = st.executeQuery();
//            rs.next();
//            Login login = readLogin(rs);
//
//            rs.close();
//            st.close();
//            cn.close();
//            return Optional.ofNullable(login);
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//            return Optional.empty();
//        }
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Country entity) {
        return false;
    }

    @Override
    public boolean create(Country entity) {
//        try {
//            ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
//            PreparedStatement st = cn.prepareStatement(SQL_CREATE_LOGIN);
//            st.setString(1, entity.getLogin());
//            st.setString(2, entity.getPassword());
//            st.executeUpdate();
//            st.close();
//            cn.close();
//            return true;
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//            return false;
//        }
        return false;
    }

    @Override
    public Optional<Country> update(Country entity) {
//        try {
//            ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
//            PreparedStatement st = cn.prepareStatement(SQL_UPDATE_LOGINS_BY_ID);
//            st.setString(1, entity.getLogin());
//            st.setString(2, entity.getPassword());
//            st.setLong(3, entity.getId());
//            st.executeUpdate();
//
//            st = entity.isAdmin() ? cn.prepareStatement(SQL_ADD_ADMIN_BY_LOGIN_ID)
//                    : cn.prepareStatement(SQL_DEL_ADMIN_BY_LOGIN_ID);
//            st.setLong(1, entity.getId());
//            st.executeUpdate();
//            st.close();
//            cn.close();
//            return Optional.of(entity);
//        } catch (SQLException e) {
//            logger.error(e.getMessage());
//            return Optional.empty();
//        }
        return Optional.empty();
    }
}
