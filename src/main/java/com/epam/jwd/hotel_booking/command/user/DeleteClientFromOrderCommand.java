package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.CommandManager;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.Order;

public enum DeleteClientFromOrderCommand implements Command {
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
        Order order = (Order) req.getSession().getAttribute(Vars.ORDER.var);
        long client_id = Long.parseLong(req.getParametr(Vars.CLIENT_ID.var));
        order.deleteClient(client_id);
        req.getSession().setAttribute(Vars.ORDER.var, order);

        req.getSession().setAttribute(Vars.REDIRECT_COMMAND.var, CommandManager.USR_GOTO_PREPARE_ORDER);

        return REDIRECT;
    }
}

