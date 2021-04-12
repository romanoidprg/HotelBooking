package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.CommandManager;
import com.epam.jwd.hotel_booking.command.Pages;
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

    private static final ResponseContext REDIRECT = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.REDIRECT.page;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

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

        req.getSession().setAttribute(Vars.REDIRECT_COMMAND.var, CommandManager.USR_GOTO_PREPARE_ORDER);
        return REDIRECT;
    }
}

