package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.Order;

import java.time.LocalDate;

public enum SetOrderDateOutCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        Order request = (Order) req.getSession().getAttribute(Vars.ORDER.var);
        request.setDateOut(LocalDate.parse(req.getParametr(Vars.DATE_OUT.var)));
        req.getSession().setAttribute(Vars.ORDER.var, request);
        return GoToOrderPrepareCommand.INSTANCE.execute(req);
    }
}

