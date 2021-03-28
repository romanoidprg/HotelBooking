package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.CommandManager;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.ClientDao;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.OrderSearchPattern;

import java.util.List;
import java.util.Optional;

public enum ShowOrdersRelatedToClientCommand implements Command {
    INSTANCE;

    private static final ResponseContext ADM_ORDERS_SHOW = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ADM_SHOW_ORDERS_RELATED_TO_CLIENT.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        if (req.getParametr(Vars.NEW_LIST.var).equals("true")) {
            long relatedClientId = Long.parseLong(req.getParametr(Vars.RELATED_CLIENT_ID.var));
            ClientDao clientDao = new ClientDao();
            Client client = clientDao.findEntityById(relatedClientId).orElse(new Client());
            OrderSearchPattern orderSearchPattern = OrderSearchPattern.newBuilder()
                    .whereClientIdInclude(relatedClientId)
                    .whereClientIdStrongMatchIs(true)
                    .build();
            req.getSession().setAttribute(Vars.CLIENT_FOR_SHOW_RELATED_ORDERS.var, client);
            OrderDao orderDao = new OrderDao();
            req.getSession().removeAttribute(Vars.ORDER_LIST.var);
            Optional<List<Order>> orderList = orderDao.findByOrderPattern(orderSearchPattern);
            orderList.ifPresent(orders -> req.getSession().setAttribute(Vars.ORDER_LIST.var, orders));
        req.getSession().setAttribute(Vars.LAST_COMMAND_BEFORE_ORDERS_SHOW.var, req.getParametr(Vars.LAST_COMMAND_BEFORE_ORDERS_SHOW.var));
        req.getSession().setAttribute(Vars.LAST_SHOW_ORDERS_COMMAND.var, CommandManager.ADM_SHOW_ORDERS_RELATED_TO_CLIENT);
        }
        return ADM_ORDERS_SHOW;
    }
}

