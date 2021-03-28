package com.epam.jwd.hotel_booking.model;

import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ROUND_DOWN;

public class Room implements Comparable<Room> {
    private int id;
    private RoomSize size;
    private RoomType type;

    public Room(int id, RoomSize size, RoomType type) {
        this.id = id;
        this.size = size;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomSize getSize() {
        return size;
    }

    public void setSize(RoomSize size) {
        this.size = size;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public String getStringCost(){
        BigDecimal cost = size.getCostPerDay().multiply(type.getCostFactor())
                .stripTrailingZeros().setScale(0, ROUND_DOWN);
        if (cost != BigDecimal.ZERO) {
            String res = cost.toString();
            return res.substring(0, res.length() - 2) + "." + res.substring(res.length() - 2);
        } else {
            return "0.00";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && size == room.size && type == room.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, type);
    }

    @Override
    public int compareTo(Room o) {
        if (size.compareTo(o.getSize()) == 0) {
            return type.compareTo(o.getType());
        } else {
            return size.compareTo(o.getSize());
        }
    }
}
