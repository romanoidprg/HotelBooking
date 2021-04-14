package com.epam.jwd.hotel_booking.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public enum ConnectionPool {
    INSTANCE();

    private final static String DB_NAME = "jdbc:mysql://localhost:3306/hotelbooking";
    private final static String USER_NAME = "root";
    private final static String USER_PASS = "romanoid";

    private final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private final Lock lock = new ReentrantLock();

    private static final int INITIAL_CONNECTIONS_AMOUNT = 8;

    private final ArrayBlockingQueue<ProxyConnection> connections = new ArrayBlockingQueue<>(INITIAL_CONNECTIONS_AMOUNT);

    public Connection retrieveConnection() {
        lock.lock();
        try {
            if (connections.isEmpty()) {
                addConnectionToPool();
            }
            return connections.take();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            throw new IllegalStateException(e);
        } finally {
            lock.unlock();
        }

    }

    public void returnConnection(Connection connection) {
        lock.lock();
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
        } finally {
            lock.unlock();
        }
    }

    public void init() {
        registerDrivers();
        for (int i = 0; i < INITIAL_CONNECTIONS_AMOUNT; i++) {
            addConnectionToPool();
        }
        logger.info("Connection POOL have initialized with " + connections.size() + "connections");
    }

    private void addConnectionToPool() {
        try {
            final Connection realConnection = DriverManager
                    .getConnection(DB_NAME, USER_NAME, USER_PASS);
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
            DriverManager.registerDriver(DriverManager.getDriver(DB_NAME));
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
