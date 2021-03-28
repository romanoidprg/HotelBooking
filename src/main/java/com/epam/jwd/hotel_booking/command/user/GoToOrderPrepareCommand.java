package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.Order;

public enum GoToOrderPrepareCommand implements Command {
    INSTANCE;

    private static final ResponseContext ORDER_PREPARE = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ORDER_PREPARE.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        if (req.getSession().getAttribute(Vars.ORDER.var) == null) {
            req.getSession().setAttribute(Vars.ORDER.var, new Order());
        }
        return ORDER_PREPARE;
    }

}
