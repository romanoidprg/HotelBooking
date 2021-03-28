package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;
import com.epam.jwd.hotel_booking.model.Order;

public enum SendOrderForSubmitCommand implements Command {
    INSTANCE;

    private static final ResponseContext ORDER_CREATE_SUCCESS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ORDER_CREATE_SUCCESS.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static final ResponseContext ORDER_CREATE_NOT_SUCCESS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ORDER_CREATE_NOT_SUCCESS.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public ResponseContext execute(RequestContext req) {

        Order order = (Order) req.getSession().getAttribute(Vars.ORDER.var);
        order.setRelatedLogin((String) req.getSession().getAttribute(Vars.LOGIN.var));
        OrderDao orderDao = new OrderDao();
        if (orderDao.create(order)) {
            req.getSession().removeAttribute(Vars.ORDER.var);
            return ORDER_CREATE_SUCCESS;
        } else {
            return ORDER_CREATE_NOT_SUCCESS;
        }

    }
}

