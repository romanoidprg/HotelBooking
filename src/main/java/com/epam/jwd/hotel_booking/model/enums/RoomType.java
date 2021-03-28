package com.epam.jwd.hotel_booking.model.enums;

import com.epam.jwd.hotel_booking.connections.ConnectionPool;
import com.epam.jwd.hotel_booking.connections.ProxyConnection;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum RoomType {
    ECONOM(false,true,false,false),
    STANDARD(true,true,false,false),
    LUX(true, true,true,true);

    static {
        String SELECT_COST = "SELECT cost_factor FROM room_types WHERE type=?";
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SELECT_COST);) {

            for (RoomType type : RoomType.values()) {
                st.setString(1, type.toString());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    type.costFactor = rs.getBigDecimal(1);
                }
            }
        } catch (SQLException e) {
            ECONOM.costFactor = new BigDecimal("100");
            STANDARD.costFactor = new BigDecimal("200");
            LUX.costFactor = new BigDecimal("300");
//            logger.error(e.getMessage());

        }
    }


    private final boolean tv;
    private final boolean phone;
    private final boolean fridge;
    private final boolean safe;
    private BigDecimal costFactor;

    RoomType(boolean tv, boolean phone, boolean fridge, boolean safe) {
        this.tv = tv;
        this.phone = phone;
        this.fridge = fridge;
        this.safe = safe;
    }


    public boolean isTv() {
        return tv;
    }

    public boolean isPhone() {
        return phone;
    }

    public boolean isFridge() {
        return fridge;
    }

    public boolean isSafe() {
        return safe;
    }

    public BigDecimal getCostFactor() {
        return costFactor;
    }

    public void setCostFactor(BigDecimal costFactor) {
        this.costFactor = costFactor;
    }

    public static RoomType ofString(String type) {
        RoomType result = null;
        for (RoomType value : values()) {
            if (value.toString().equals(type)) {
                result = value;
            }
        }
        return result;
    }
}
