package com.epam.jwd.hotel_booking.model;

import com.epam.jwd.hotel_booking.model.enums.FoodPlan;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;
import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;
import com.epam.jwd.hotel_booking.model.enums.Sex;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void getCost() {
        List<String> phones = new ArrayList<>();
        phones.add("28734234");
        phones.add("83497459");
        phones.add("89999993");

        User user1 = new User(1L, "aaaa", "11111", false, "andrew", "sharov",
                "ash@aa.com", phones, LocalDate.of(1981, 03, 12), Sex.MALE, "Russia", "Rostov, weu 76");
        User user2 = new User(2L, "bbbb", "222222", false, "mike", "petrov",
                "werh@aa.com", phones, LocalDate.of(1998, 04, 11), Sex.MALE, "Russia", "Rostov, weu 76");

        Room r1 = new Room(1, RoomSize.FAMILY, RoomType.ECONOM);
        Room r2 = new Room(2, RoomSize.FAMILY, RoomType.LUX);
        Room r3 = new Room(3, RoomSize.ONE_BED, RoomType.STANDART);
        Room r4 = new Room(4, RoomSize.THREE_BED, RoomType.ECONOM);
        Room r5 = new Room(5, RoomSize.TWO_BED, RoomType.LUX);
        List<Room> rooms = new ArrayList<>();
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
        rooms.add(r4);
        rooms.add(r5);

        Map<User, FoodPlan> users = new HashMap<>();
        users.put(user1, FoodPlan.AI);
        users.put(user2, FoodPlan.BBd);

        Order order = new Order(1l, users, OrderStatus.ACTIVE,
                LocalDate.of(2021, 02, 18), LocalDate.of(2021, 02, 20),
                LocalTime.of(10, 30), LocalTime.of(18, 00), rooms);

        assertEquals(new BigDecimal("42800"), order.getCost());
    }
}