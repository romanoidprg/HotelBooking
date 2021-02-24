package com.epam.jwd.hotel_booking.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

    public void connect() {
        try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelbooking", "root", "romanoid");
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from countries");
        ) {
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
