package com.epam.jwd.hotel_booking.dao;

import com.epam.jwd.hotel_booking.connections.ConnectionPool;
import com.epam.jwd.hotel_booking.connections.ProxyConnection;
import com.epam.jwd.hotel_booking.model.enums.FoodPlan;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;
import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class EnumDaoTest {

    @Test
    public void getOrderStatusIdByStatusWithConnection() {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection()) {
            assertEquals(1, EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.ACTIVE, cn));
            assertEquals(2, EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.CANCELED, cn));
            assertEquals(3, EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.DONE, cn));
            assertEquals(4, EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.AWAITING, cn));
            assertEquals(5, EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.OUT_OF_DATE, cn));
            assertEquals(11, EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.CONFIRMED, cn));
            assertEquals(12, EnumDao.getOrderStatusIdByStatusWithConnection(OrderStatus.NOT_CLOSED, cn));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void getFoodplanIdByFoodplanWithConnection() {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection()) {
            assertEquals(1, EnumDao.getFoodplanIdByFoodplanWithConnection(FoodPlan.AI, cn));
            assertEquals(2, EnumDao.getFoodplanIdByFoodplanWithConnection(FoodPlan.FB, cn));
            assertEquals(3, EnumDao.getFoodplanIdByFoodplanWithConnection(FoodPlan.HB, cn));
            assertEquals(4, EnumDao.getFoodplanIdByFoodplanWithConnection(FoodPlan.HBd, cn));
            assertEquals(5, EnumDao.getFoodplanIdByFoodplanWithConnection(FoodPlan.BB, cn));
            assertEquals(6, EnumDao.getFoodplanIdByFoodplanWithConnection(FoodPlan.BBd, cn));
            assertEquals(7, EnumDao.getFoodplanIdByFoodplanWithConnection(FoodPlan.NONE, cn));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void getRoomSizeIdByRoomSizeWithConnection() {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection()) {
            assertEquals(1, EnumDao.getRoomSizeIdByRoomSizeWithConnection(RoomSize.ONE_BED, cn));
            assertEquals(2, EnumDao.getRoomSizeIdByRoomSizeWithConnection(RoomSize.TWO_BED, cn));
            assertEquals(3, EnumDao.getRoomSizeIdByRoomSizeWithConnection(RoomSize.THREE_BED, cn));
            assertEquals(4, EnumDao.getRoomSizeIdByRoomSizeWithConnection(RoomSize.FAMILY, cn));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void getRoomTypeIdByRoomTypeWithConnection() {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection()) {
            assertEquals(1, EnumDao.getRoomTypeIdByRoomTypeWithConnection(RoomType.ECONOM, cn));
            assertEquals(2, EnumDao.getRoomTypeIdByRoomTypeWithConnection(RoomType.STANDARD, cn));
            assertEquals(3, EnumDao.getRoomTypeIdByRoomTypeWithConnection(RoomType.LUX, cn));
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    public void getSubZeroRoomIdByRoomSizeIdAndTypeIdWithConnection() {
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection()) {
            assertEquals(-12, EnumDao.getSubZeroRoomIdByRoomSizeIdAndTypeIdWithConnection(4, 3, cn));
            assertEquals(-11, EnumDao.getSubZeroRoomIdByRoomSizeIdAndTypeIdWithConnection(4, 2, cn));
            assertEquals(-10, EnumDao.getSubZeroRoomIdByRoomSizeIdAndTypeIdWithConnection(4, 1, cn));
            assertEquals(-9, EnumDao.getSubZeroRoomIdByRoomSizeIdAndTypeIdWithConnection(3, 3, cn));
        } catch (SQLException e) {
            fail();
        }
    }
}