package com.epam.jwd.hotel_booking.model.enums;

import java.math.BigDecimal;

public enum RoomType {
    LUX(true, true,true,true,new BigDecimal("1.80")),
    STANDART(true,true,false,false,new BigDecimal("1.20")),
    ECONOM(false,true,false,false,new BigDecimal("1.00"));

    private boolean tv;
    private boolean phone;
    private boolean fridge;
    private boolean safe;
    private BigDecimal costFactor;

    private RoomType(boolean tv, boolean phone, boolean fridge, boolean safe,BigDecimal costFactor) {
        this.tv = tv;
        this.phone = phone;
        this.fridge = fridge;
        this.safe = safe;
        this.costFactor = costFactor;
    }

    public boolean isTv() {
        return tv;
    }

    public boolean isPhone() {
        return phone;
    }

    public boolean isFridge() {
        return fridge;
    }

    public boolean isSafe() {
        return safe;
    }

    public BigDecimal getCostFactor() {
        return costFactor;
    }

    public void setCostFactor(BigDecimal costFactor) {
        this.costFactor = costFactor;
    }
}
