package com.epam.jwd.hotel_booking.model.enums;

import com.epam.jwd.hotel_booking.connections.ConnectionPool;
import com.epam.jwd.hotel_booking.connections.ProxyConnection;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.math.BigDecimal.ROUND_DOWN;

public enum RoomSize {
    ONE_BED,
    TWO_BED,
    THREE_BED,
    FAMILY;

    static {
        String SELECT_COST = "SELECT cost_per_day, place_col FROM room_sizes WHERE size=?";
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SELECT_COST);) {

            for (RoomSize size : RoomSize.values()) {
                st.setString(1, size.toString());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    size.costPerDay = rs.getBigDecimal(1);
                    size.placeCount = rs.getInt(2);
                }
            }
        } catch (SQLException e) {
            ONE_BED.costPerDay = new BigDecimal("10000000");
            TWO_BED.costPerDay = new BigDecimal("20000000");
            THREE_BED.costPerDay = new BigDecimal("30000000");
            FAMILY.costPerDay = new BigDecimal("40000000");
//            logger.error(e.getMessage());

        }
    }

    private BigDecimal costPerDay;
    private int placeCount;

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public String getStringCostPerDay() {
        BigDecimal cost = costPerDay.stripTrailingZeros().setScale(0, ROUND_DOWN);
        String res = cost.toString();
        return res.substring(0, res.length() - 2) + "." + res.substring(res.length() - 2);
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public static RoomSize ofString(String size) {
        RoomSize result = null;
        for (RoomSize value : values()) {
            if (value.toString().equals(size)) {
                result = value;
                break;
            }
        }
        return result;
    }
}
