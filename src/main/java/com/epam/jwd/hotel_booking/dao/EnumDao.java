package com.epam.jwd.hotel_booking.dao;

import com.epam.jwd.hotel_booking.connections.ProxyConnection;
import com.epam.jwd.hotel_booking.model.enums.FoodPlan;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;
import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnumDao {
    private static final String SQL_GET_STATUS_ID
            = "SELECT id FROM statuses WHERE status=?";
    private static final String SQL_GET_FOODPLAN_ID = "SELECT id FROM food_plans WHERE plan=?";
    private static final String SQL_GET_ROOM_SIZE_ID = "SELECT id FROM room_sizes WHERE size=?";
    private static final String SQL_GET_ROOM_TYPE_ID = "SELECT id FROM room_types WHERE type=?";
    private static final String SQL_GET_ROOM_ID_SUBZERO = "SELECT id FROM rooms WHERE size_id=? AND type_id=? AND id<0";



    public static int getOrderStatusIdByStatusWithConnection(OrderStatus status, ProxyConnection cn) throws SQLException {
        PreparedStatement st = cn.prepareStatement(SQL_GET_STATUS_ID);
        st.setString(1, status.toString());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException();
        }
    }

    public static int getFoodplanIdByFoodplanWithConnection(FoodPlan foodPlan, ProxyConnection cn) throws SQLException {
        PreparedStatement st = cn.prepareStatement(SQL_GET_FOODPLAN_ID);
        st.setString(1, foodPlan.toString());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException();
        }
    }

    public static int getRoomSizeIdByRoomSizeWithConnection(RoomSize roomSize, ProxyConnection cn) throws SQLException {
        PreparedStatement st = cn.prepareStatement(SQL_GET_ROOM_SIZE_ID);
        st.setString(1, roomSize.toString());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException();
        }
    }

    public static int getRoomTypeIdByRoomTypeWithConnection(RoomType roomType, ProxyConnection cn) throws SQLException {
        PreparedStatement st = cn.prepareStatement(SQL_GET_ROOM_TYPE_ID);
        st.setString(1, roomType    .toString());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException();
        }
    }

    public static int getSubZeroRoomIdByRoomSizeIdAndTypeIdWithConnection(int roomSizeId, int roomTypeId, ProxyConnection cn) throws SQLException {
        PreparedStatement st = cn.prepareStatement(SQL_GET_ROOM_ID_SUBZERO);
        st.setInt(1, roomSizeId);
        st.setInt(2, roomTypeId);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException();
        }
    }
}
