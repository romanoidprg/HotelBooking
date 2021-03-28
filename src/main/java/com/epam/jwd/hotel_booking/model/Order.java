package com.epam.jwd.hotel_booking.model;

import com.epam.jwd.hotel_booking.model.enums.FoodPlan;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_DOWN;

public class Order {
    private long id;
    private String relatedLogin;
    private Map<Client, FoodPlan> clients;
    private OrderStatus status;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private List<Room> rooms;

    public Order() {

        id = -1L;
        clients = new HashMap<>();
        status = OrderStatus.AWAITING;
        dateIn = LocalDate.now();
        dateOut = LocalDate.now().plusDays(1L);
        rooms = new ArrayList<>();
    }

    public Order(long id, Map<Client, FoodPlan> clients, OrderStatus status,
                 LocalDate dateIn, LocalDate dateOut, List<Room> rooms) {
        this.id = id;
        this.clients = clients;
        this.status = status;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRelatedLogin() {
        return relatedLogin;
    }

    public void setRelatedLogin(String login) {
        this.relatedLogin = login;
    }

    public long getDays(){
        return dateOut.toEpochDay()-dateIn.toEpochDay();
    }

    public Map<Client, FoodPlan> getClients() {
        return clients;
    }

    public int getClientsAmount() {
        return clients.size();
    }

    public int getRoomsAmount() {
        return rooms.size();
    }

    public void addUserWithFoodPlan(Client client, FoodPlan foodPlan) {
        this.clients.put(client, foodPlan);
    }

    public void deleteClient(long clientId) {
        Client client = clients.entrySet().stream().filter(e -> e.getKey().getId() == clientId).findAny().get().getKey();
        clients.remove(client);
    }

    public void setClientFoodPlan(long clientId, FoodPlan foodPlan) {
        clients.entrySet().stream().filter(e -> e.getKey().getId() == clientId).forEach(e -> e.setValue(foodPlan));
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
        rooms.sort(Room::compareTo);
    }

    public void deleteRoom(Room roomForDelete) {
        rooms.remove(roomForDelete);
    }

    public BigDecimal getCostPerDay(){
        BigDecimal cost = BigDecimal.ZERO;
        if (!clients.isEmpty()) {
            for (FoodPlan fp : clients.values()) {
                cost = cost.add(fp.getCostPerDay());
            }
        }
        if (!rooms.isEmpty()) {
            for (Room r : rooms) {
                cost = cost.add(r.getSize().getCostPerDay().multiply(r.getType().getCostFactor()));
            }
        }
        return cost.stripTrailingZeros().setScale(0, ROUND_DOWN);
    }

    public BigDecimal getCost() {
        BigDecimal dayCount = new BigDecimal(ChronoUnit.DAYS.between(dateIn, dateOut));
        return getCostPerDay().multiply(dayCount).stripTrailingZeros().setScale(0, ROUND_DOWN);
    }

    public String getStringCost() {
        if (getCost() != BigDecimal.ZERO) {
            String res = getCost().toString();
            return res.substring(0, res.length() - 2) + "." + res.substring(res.length() - 2);
        } else {
            return "0.00";
        }
    }
    public String getStringCostPerDay() {
        if (getCostPerDay() != BigDecimal.ZERO) {
            String res = getCostPerDay().toString();
            return res.substring(0, res.length() - 2) + "." + res.substring(res.length() - 2);
        } else {
            return "0.00";
        }
    }

}
