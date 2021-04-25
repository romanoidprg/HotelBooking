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
            orderSearchPattern = completeOrderSearchPattern(req);
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


        req.getSession().setAttribute(Vars.LAST_SHOW_ORDERS_COMMAND.var, CommandManager.ALL_DISPLAY_ORDERS_RELATED_TO_LOGIN);
        req.getSession().setAttribute(Vars.REDIRECT_COMMAND.var, CommandManager.ALL_DISPLAY_ORDERS_RELATED_TO_LOGIN);
        return REDIRECT;
    }

    private OrderSearchPattern completeOrderSearchPattern(RequestContext req) {

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

        return OrderSearchPattern.newBuilder()
                .whereLoginInclude(relatedLogin).whereLoginStrongMatchIs(true)
                .whereDateFromIs(dateFrom)
                .whereDateBeforeIs(dateBefore)
                .build();
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

