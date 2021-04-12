package com.epam.jwd.hotel_booking.model.enums;

import com.epam.jwd.hotel_booking.connections.ConnectionPool;
import com.epam.jwd.hotel_booking.connections.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.math.BigDecimal.ROUND_DOWN;

public enum FoodPlan {
    AI(true, true, true, true),
    FB(true, true, true, false),
    HB(true, false, true, false),
    HBd(true, false, true, true),
    BB(true, false, false, false),
    BBd(true, false, false, true),
    NONE(false, false, false, false);

    private final static Logger logger = LogManager.getLogger(RoomType.class);

    static {
        String SELECT_COST = "SELECT cost_per_day FROM food_plans WHERE plan=?";
        try (ProxyConnection cn = (ProxyConnection) ConnectionPool.INSTANCE.retrieveConnection();
             PreparedStatement st = cn.prepareStatement(SELECT_COST);) {

            for (FoodPlan plan : FoodPlan.values()) {
                st.setString(1, plan.toString());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    plan.costPerDay = rs.getBigDecimal(1);
                }
            }
            logger.info("Food planes received successful.");

        } catch (SQLException e) {
            AI.costPerDay = new BigDecimal("1000000");
            FB.costPerDay = new BigDecimal("1000000");
            HB.costPerDay = new BigDecimal("1000000");
            HBd.costPerDay = new BigDecimal("1000000");
            BB.costPerDay = new BigDecimal("1000000");
            BBd.costPerDay = new BigDecimal("1000000");
            NONE.costPerDay = new BigDecimal("1000000");
            logger.error(e.getMessage());

        }
    }

    private BigDecimal costPerDay;

    private boolean isBreakfast;
    private boolean isDinner;
    private boolean isSupper;
    private boolean isDrinks;

    public boolean isBreakfast() {
        return isBreakfast;
    }

    public boolean isDinner() {
        return isDinner;
    }

    public boolean isSupper() {
        return isSupper;
    }

    public boolean isDrinks() {
        return isDrinks;
    }

    FoodPlan(boolean b, boolean d, boolean s, boolean dr) {
        isBreakfast = b;
        isDinner = d;
        isSupper = s;
        isDrinks = dr;
    }

    public static FoodPlan ofString(String plan) {
        FoodPlan result = NONE;
        for (FoodPlan value : values()) {
            if (value.toString().equals(plan)) {
                result = value;
            }
        }
        return result;
    }


    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public String getStringCostPerDay() {
        return costPerDay.divide(BigDecimal.valueOf(100)).toPlainString();
    }
}
