package com.epam.jwd.hotel_booking.command.all;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.CommandManager;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;
import com.epam.jwd.hotel_booking.model.LoginRole;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.OrderSearchPattern;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public enum ShowOrdersRelatedToLoginCommand implements Command {
    INSTANCE;

    private static final ResponseContext ADM_ORDERS_SHOW = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ADM_SHOW_ORDERS_RELATED_TO_LOGIN.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    private static final ResponseContext USR_ORDERS_SHOW = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.USR_SHOW_ORDERS_RELATED_TO_LOGIN.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public ResponseContext execute(RequestContext req) {
        if (req.getParametr(Vars.NEW_LIST.var).equals("true")) {

            LocalDate dateFrom;
            LocalDate dateBefore;

            if (req.getParameterMap().containsKey(Vars.DATE_FROM_TO_SHOW_ORDERS.var)
                    && req.getParameterMap().containsKey(Vars.DATE_BEFORE_TO_SHOW_ORDERS.var)) {
                dateFrom = LocalDate.parse(req.getParametr(Vars.DATE_FROM_TO_SHOW_ORDERS.var));
                dateBefore = LocalDate.parse(req.getParametr(Vars.DATE_BEFORE_TO_SHOW_ORDERS.var));
            } else {
                dateFrom = LocalDate.MIN;
                dateBefore = LocalDate.MAX;
            }
            String relatedLogin = req.getParametr(Vars.LOGIN_FOR_SHOW_ORDERS.var);
            req.getSession().setAttribute(Vars.LOGIN_FOR_SHOW_ORDERS.var, relatedLogin);

            OrderSearchPattern orderSearchPattern = OrderSearchPattern.newBuilder()
                    .whereLoginInclude(relatedLogin).whereLoginStrongMatchIs(true)
                    .whereDateFromIs(dateFrom)
                    .whereDateBeforeIs(dateBefore)
                    .build();

            OrderDao orderDao = new OrderDao();
            req.getSession().removeAttribute(Vars.ORDER_LIST.var);
            Optional<List<Order>> orderList = orderDao.findByOrderPattern(orderSearchPattern);
            orderList.ifPresent(orders -> req.getSession().setAttribute(Vars.ORDER_LIST.var, orders));
        }

        req.getSession().setAttribute(Vars.LAST_SHOW_ORDERS_COMMAND.var, CommandManager.ALL_SHOW_ORDERS_RELATED_TO_LOGIN);

        if (LoginRole.ADMIN.equals(req.getSession().getAttribute(Vars.ROLE.var))) {
            return ADM_ORDERS_SHOW;
        } else {
            return USR_ORDERS_SHOW;
        }
    }
}

