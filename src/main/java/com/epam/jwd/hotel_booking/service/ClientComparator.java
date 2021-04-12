package com.epam.jwd.hotel_booking.service;

import com.epam.jwd.hotel_booking.model.Client;

import java.util.Comparator;

public class ClientComparator {
    public static Comparator<Client> byId() {
        return Comparator.comparingLong(Client::getId);
    }
}
