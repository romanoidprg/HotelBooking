package com.epam.jwd.hotel_booking.service;

import com.epam.jwd.hotel_booking.model.Client;

import java.util.Comparator;

public class ClientComparator {
    public static Comparator<Client> byId() {
        return new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return Long.compare(o1.getId(), o2.getId());
            }
        };
    }
}
