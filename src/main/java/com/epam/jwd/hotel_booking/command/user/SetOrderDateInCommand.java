package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.Order;

import java.time.LocalDate;

public enum SetOrderDateInCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        Order order = (Order) req.getSession().getAttribute(Vars.ORDER.var);
        order.setDateIn(LocalDate.parse(req.getParametr(Vars.DATE_IN.var)));
        req.getSession().setAttribute(Vars.ORDER.var, order);
        return GoToOrderPrepareCommand.INSTANCE.execute(req);
    }
}

