package com.epam.jwd.hotel_booking.model;

import com.epam.jwd.hotel_booking.model.enums.Sex;

import java.time.LocalDate;

public class ClientSearchPattern extends Client {

    private LocalDate birthDayTo;

    public ClientSearchPattern(String name, String sName,
                               String eMail, String phone, LocalDate birthDayFrom, LocalDate birthDayTo,
                               Sex sex, String country, String address) {
        super(-1L, name, sName, eMail, phone,birthDayFrom,sex,country,address);
        this.birthDayTo = birthDayTo;
    }

    public LocalDate getBirthDayTo() {
        return birthDayTo;
    }
}
