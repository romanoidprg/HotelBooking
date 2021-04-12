package com.epam.jwd.hotel_booking.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;

public enum ConnectionPool {
    INSTANCE();

    private final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static final int INITIAL_CONNECTIONS_AMOUNT = 8;

    private final ArrayBlockingQueue<ProxyConnection> connections = new ArrayBlockingQueue<>(INITIAL_CONNECTIONS_AMOUNT);

    public Connection retrieveConnection() {
        try {
            if (connections.isEmpty()) {
                addConnectionToPool();
            }
            return connections.take();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    public void returnConnection(Connection connection) {
        try {
            if (connections.size() >= INITIAL_CONNECTIONS_AMOUNT) {
                ((ProxyConnection) connection).closeConnection();
                logger.info("Close connection");
            } else {
                connections.put((ProxyConnection) connection);
            }
        } catch (SQLException | InterruptedException e) {
            logger.error(e.getMessage());
            throw new IllegalStateException();
        }
    }

    public void init() {
        registerDrivers();
        for (int i = 0; i < INITIAL_CONNECTIONS_AMOUNT; i++) {
            addConnectionToPool();
        }
        logger.info("Connection POOL have initialized with " + String.valueOf(connections.size()) + "connections");
    }

    private void addConnectionToPool() {
        try {
            final Connection realConnection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/hotelbooking", "root", "romanoid");
            final ProxyConnection proxyConnection = new ProxyConnection(realConnection);
            try {
                connections.put(proxyConnection);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void destroy() {
        connections.forEach(proxyConnection -> {
            try {
                proxyConnection.closeConnection();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        });
        logger.info("Connection POOL have destroyed");
        deregisterDrivers();
    }


    private void registerDrivers() {
        logger.info("registering another drivers");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(DriverManager.getDriver("jdbc:mysql://localhost:3306/HotelBooking"));
            logger.info("registration successful");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("registration unsuccessful: " + e.getMessage());
        }
    }


    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
                logger.info("deregistration successful");
            } catch (SQLException e) {
                logger.error("deregistration unsuccessful");
                logger.error(e.getMessage());
            }
        }
    }
}
