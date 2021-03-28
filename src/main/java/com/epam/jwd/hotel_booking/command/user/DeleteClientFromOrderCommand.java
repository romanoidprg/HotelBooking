package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.Order;

public enum DeleteClientFromOrderCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        Order order = (Order) req.getSession().getAttribute(Vars.ORDER.var);
        long client_id = Long.parseLong(req.getParametr(Vars.CLIENT_ID.var));
        order.deleteClient(client_id);
        req.getSession().setAttribute(Vars.ORDER.var, order);
        return GoToOrderPrepareCommand.INSTANCE.execute(req);
    }
}

