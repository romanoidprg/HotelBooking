package com.epam.jwd.hotel_booking.exceptions;

public class NoEntityFoundException extends Exception {
    Object entity;

    public NoEntityFoundException(Object entity) {
        this.entity = entity;
    }

    @Override
    public String getMessage() {
        return "Entity " + entity.toString() + " is not found";
    }
}
