package com.epam.jwd.hotel_booking.model.enums;

import java.util.Locale;

public enum Sex {
    MALE,
    FEMALE,
    THING;

    public static Sex fromString(String sexStr){
        if (sexStr.equalsIgnoreCase("male")) {
            return MALE;
        } else if (sexStr.equalsIgnoreCase("female")) {
            return FEMALE;
        } else {
            return THING;
        }
    }
}
