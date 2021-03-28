package com.epam.jwd.hotel_booking.exceptions;

import com.epam.jwd.hotel_booking.dao.impl.ClientDao;
import com.epam.jwd.hotel_booking.model.Client;

public class NoClientFoundException extends Exception {
    Client client;

    public NoClientFoundException(Client client) {
        this.client = client;
    }

    @Override
    public String getMessage() {
        return "Client:(Name - " + client.getName() + " sName - "
                + client.getsName() + " Bday - " + client.getBirthDay()
                + " Country - " + client.getCountry() + " Address - "
                + client.getAddress() + ") is not found";
    }
}
