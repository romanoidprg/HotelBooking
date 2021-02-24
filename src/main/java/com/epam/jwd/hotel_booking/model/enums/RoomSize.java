package com.epam.jwd.hotel_booking.model.enums;

import java.math.BigDecimal;

public enum RoomSize {
    FAMILY(new BigDecimal("4000")),
    THREE_BED(new BigDecimal("3000")),
    TWO_BED(new BigDecimal("2000")),
    ONE_BED(new BigDecimal("1000"));

    private BigDecimal costPerDay;

    private RoomSize(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }
}
