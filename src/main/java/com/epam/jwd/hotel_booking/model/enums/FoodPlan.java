package com.epam.jwd.hotel_booking.model.enums;

import java.math.BigDecimal;

public enum FoodPlan {
    AI(new BigDecimal("1700")),
    FB(new BigDecimal("1500")),
    HB(new BigDecimal("1000")),
    HBd(new BigDecimal("1200")),
    BB(new BigDecimal("500")),
    BBd(new BigDecimal("700")),
    NONE(BigDecimal.ZERO);

    private BigDecimal costPerDay;

    private FoodPlan(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }
}
