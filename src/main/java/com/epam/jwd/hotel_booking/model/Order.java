package com.epam.jwd.hotel_booking.model;

import com.epam.jwd.hotel_booking.model.enums.FoodPlan;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;
import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_DOWN;

public class Order {
    private long id;
    private Map<User, FoodPlan> users = new HashMap<>();
    private OrderStatus status;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private List<Room> rooms = new ArrayList<>();

    public Order(long id, Map<User, FoodPlan> users, OrderStatus status,
                 LocalDate dateIn, LocalDate dateOut, LocalTime timeIn, LocalTime timeOut, List<Room> rooms) {
        this.id = id;
        this.users = users;
        this.status = status;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<User, FoodPlan> getUsers() {
        return users;
    }

    public void addUserWithFoodPlan(User user, FoodPlan foodPlan) {
        this.users.put(user, foodPlan);
    }

    public void setUserFoodPlan(User user, FoodPlan foodPlan) {
        //todo
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public BigDecimal getCost() {
        BigDecimal cost = BigDecimal.ZERO;
        BigDecimal dayCount = new BigDecimal(ChronoUnit.DAYS.between(dateIn, dateOut));

        for (FoodPlan fp : users.values()) {
            cost = cost.add(fp.getCostPerDay().multiply(dayCount));
        }
        for (Room r : rooms) {
            cost = cost.add(r.getSize().getCostPerDay().multiply(r.getType().getCostFactor()).multiply(dayCount));
        }
        return cost.stripTrailingZeros().setScale(0,ROUND_DOWN);
    }

}
