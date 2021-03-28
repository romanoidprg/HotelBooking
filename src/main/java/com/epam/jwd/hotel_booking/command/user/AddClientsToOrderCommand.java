package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.enums.FoodPlan;

import java.util.Arrays;
import java.util.List;

public enum AddClientsToOrderCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        String[] ids = req.getParametrValues(Vars.CLIENT_IDS_TO_ADD_TO_ORDER.var);
        if (ids != null) {
            Order order = (Order) req.getSession().getAttribute(Vars.ORDER.var);
            List<String> idsList = Arrays.asList(ids);

            List<Client> clientList = (List<Client>) req.getSession().getAttribute(Vars.CLIENTS_TO_ADD_TO_ORDER.var);
            for (Client client : clientList) {
                if (idsList.contains(String.valueOf(client.getId()))) {
                    order.addUserWithFoodPlan(client, FoodPlan.NONE);
                }
            }
            req.getSession().setAttribute(Vars.ORDER.var, order);
        }
        return GoToOrderPrepareCommand.INSTANCE.execute(req);
    }
}

