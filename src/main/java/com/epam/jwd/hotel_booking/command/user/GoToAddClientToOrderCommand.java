package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.ClientDao;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum GoToAddClientToOrderCommand implements Command {
    INSTANCE;

    private static final ResponseContext ADD_CLIENT = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ADD_CLIENT_TO_ORDER.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public ResponseContext execute(RequestContext req) {
        ClientDao clientDao = new ClientDao();
        List<Client> clientsToAddToOrder = clientDao.findAllLinkedToLoginName(
                (String) req.getSession().getAttribute(Vars.LOGIN.var)).orElse(new ArrayList<>());
        Order order = (Order) req.getSession().getAttribute(Vars.ORDER.var);
        clientsToAddToOrder.removeIf(clientOfLogin -> order.getClients().containsKey(clientOfLogin));
        req.getSession().setAttribute(Vars.CLIENTS_TO_ADD_TO_ORDER.var, clientsToAddToOrder);
        return ADD_CLIENT;
    }
}

