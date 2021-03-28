package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.enums.FoodPlan;


public enum SetClientFoodplanCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        Order request = (Order) req.getSession().getAttribute(Vars.ORDER.var);
        long client_id = Long.parseLong(req.getParametr(Vars.CLIENT_ID.var));
        String foodPlan = req.getParametr(Vars.FOODPLAN.var);
        request.setClientFoodPlan(client_id, FoodPlan.ofString(foodPlan));
        req.getSession().setAttribute(Vars.ORDER.var, request);
        return GoToOrderPrepareCommand.INSTANCE.execute(req);
    }
}

