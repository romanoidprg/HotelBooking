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
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public enum ShowOrdersByFilterCommand implements Command {
    INSTANCE;

    private static final ResponseContext ADM_ORDERS_SHOW = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ADM_SHOW_ORDERS_BY_FILTER.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        if (req.getParametr(Vars.NEW_LIST.var).equals("true")) {

            OrderSearchPattern orderSearchPattern = OrderSearchPattern.newBuilder()
                    .whereDateFromIs(LocalDate.parse(req.getParametr(Vars.DATE_FROM_TO_SHOW_ORDERS.var)))
                    .whereDateBeforeIs(LocalDate.parse(req.getParametr(Vars.DATE_BEFORE_TO_SHOW_ORDERS.var)))
                    .whereStatusIs(req.getParametr(Vars.ORDER_STATUS.var))
                    .whereLoginInclude(req.getParametr(Vars.LOGIN_INCLUDE.var))
                    .whereClientNameInclude(req.getParametr(Vars.NAME_INCLUDE.var))
                    .whereClientSNameInclude(req.getParametr(Vars.SNAME_INCLUDE.var))
                    .build();

            OrderDao orderDao = new OrderDao();
            req.getSession().removeAttribute(Vars.ORDER_LIST.var);
            Optional<List<Order>> orderList = orderDao.findByOrderPattern(orderSearchPattern);
            orderList.ifPresent(orders -> req.getSession().setAttribute(Vars.ORDER_LIST.var, orders));
        }
        req.getSession().setAttribute(Vars.LAST_SHOW_ORDERS_COMMAND.var, CommandManager.ADM_SHOW_ORDERS_BY_FILTER);
        return ADM_ORDERS_SHOW;
    }
}

