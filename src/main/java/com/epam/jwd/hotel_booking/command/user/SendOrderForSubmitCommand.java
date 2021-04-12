package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.CommandManager;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;
import com.epam.jwd.hotel_booking.model.Order;

public enum SendOrderForSubmitCommand implements Command {
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
        order.setRelatedLogin((String) req.getSession().getAttribute(Vars.LOGIN.var));
        OrderDao orderDao = new OrderDao();

        req.getSession().setAttribute(Vars.REDIRECT_COMMAND.var, CommandManager.ALL_REDIRECT_TO);
        if (orderDao.create(order)) {
            req.getSession().removeAttribute(Vars.ORDER.var);
            req.getSession().setAttribute(Vars.PAGE_TO_REDIRECT.var, Pages.ORDER_CREATE_SUCCESS.page);
        } else {
            req.getSession().setAttribute(Vars.PAGE_TO_REDIRECT.var, Pages.ORDER_CREATE_NOT_SUCCESS.page);
        }
        return REDIRECT;

    }
}

