package com.epam.jwd.hotel_booking.model;

import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;

import java.util.Objects;

public class Room {
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
}
