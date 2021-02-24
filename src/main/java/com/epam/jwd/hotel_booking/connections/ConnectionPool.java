package com.epam.jwd.hotel_booking.connections;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;

public enum ConnectionPool {
    INSTANCE();

    Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private static final int INITIAL_CONNECTIONS_AMOUNT = 8;

    private final ArrayBlockingQueue<ProxyConnection> connections = new ArrayBlockingQueue<>(INITIAL_CONNECTIONS_AMOUNT);

    private ConnectionPool(){
        try {
            init();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public Connection retrieveConnection() {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    public void returnConnection(Connection connection) {
        //todo: check connection on fake
        try {
            connections.put((ProxyConnection) connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public void init() throws SQLException, InterruptedException {
        registerDrivers();
        for (int i = 0; i < INITIAL_CONNECTIONS_AMOUNT; i++) {
            final Connection realConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelbooking", "root", "romanoid");
            final ProxyConnection proxyConnection = new ProxyConnection(realConnection);
            connections.put(proxyConnection);
//            logger.info(String.valueOf(connections.size()));
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
        deregisterDrivers();
    }


    private static void registerDrivers() {
        System.out.println("registering another drivers");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(DriverManager.getDriver("jdbc:mysql://localhost:3306/HotelBooking"));
            System.out.println("registration successful");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("deregistering successful");
            e.printStackTrace();
        }
    }


    private static void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                System.out.println("deregistration unsuccessful");
                e.printStackTrace();
            }
        }
    }
}
