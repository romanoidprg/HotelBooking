package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.CommandManager;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.OrderSearchPattern;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public enum ShowOrdersByFilterCommand implements Command {
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
        long listPage;
        OrderDao orderDao = new OrderDao();
        OrderSearchPattern orderSearchPattern;

        if (req.getParametr(Vars.NEW_LIST.var).equals("true")) {
            orderSearchPattern = OrderSearchPattern.newBuilder()
                    .whereDateFromIs(LocalDate.parse(req.getParametr(Vars.DATE_FROM_TO_SHOW_ORDERS.var)))
                    .whereDateBeforeIs(LocalDate.parse(req.getParametr(Vars.DATE_BEFORE_TO_SHOW_ORDERS.var)))
                    .whereStatusIs(req.getParametr(Vars.ORDER_STATUS.var))
                    .whereLoginInclude(req.getParametr(Vars.LOGIN_INCLUDE.var))
                    .whereClientNameInclude(req.getParametr(Vars.NAME_INCLUDE.var))
                    .whereClientSNameInclude(req.getParametr(Vars.SNAME_INCLUDE.var))
                    .build();
            req.getSession().setAttribute(Vars.ORDER_SEARCH_PATTERN.var, orderSearchPattern);
            listPage = 1;
            req.getSession().setAttribute(Vars.LIST_PAGE.var, listPage);
        } else {
            orderSearchPattern = (OrderSearchPattern) req.getSession().getAttribute(Vars.ORDER_SEARCH_PATTERN.var);
            listPage = (long) req.getSession().getAttribute(Vars.LIST_PAGE.var);
        }

        setListPageToOrderDaoAndSessionAttribute(req, listPage, orderDao);

        Optional<List<Order>> orderList = orderDao.findByOrderPattern(orderSearchPattern);
        orderList.ifPresent(orders -> req.getSession().setAttribute(Vars.ORDER_LIST.var, orders));

        req.getSession().setAttribute(Vars.LAST_SHOW_ORDERS_COMMAND.var, CommandManager.ADM_DISPLAY_ORDERS_BY_FILTER);
        req.getSession().setAttribute(Vars.REDIRECT_COMMAND.var, CommandManager.ADM_DISPLAY_ORDERS_BY_FILTER);
        return REDIRECT;
    }

    private void setListPageToOrderDaoAndSessionAttribute(RequestContext req, long listPage, OrderDao orderDao) {
        int rowsPerPage = orderDao.getRowsPerPage();

        if (req.getParametr(Vars.NEXT_PAGE_DIRECTION.var) != null) {
            if (req.getParametr(Vars.NEXT_PAGE_DIRECTION.var).equals(Vars.FORWARD.var)
                    && ((List<Order>) req.getSession().getAttribute(Vars.ORDER_LIST.var)).size() == rowsPerPage) {
                listPage++;
                req.getSession().setAttribute(Vars.LIST_PAGE.var, listPage);
            } else if (listPage > 1 && req.getParametr(Vars.NEXT_PAGE_DIRECTION.var).equals(Vars.BACKWARD.var)) {
                listPage--;
                req.getSession().setAttribute(Vars.LIST_PAGE.var, listPage);
            }
        }

        orderDao.setListPage(listPage);
    }
}

