package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.DaoFactory;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;

import java.util.List;

public enum ActivateConfirmedOrderCommand implements Command {
    INSTANCE;

    DaoFactory daoFactory = new DaoFactory();

    private static final ResponseContext ACTIVATE_ORDER = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ACTIVATE_ORDER.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        OrderDao orderDao = daoFactory.getDao(OrderDao.class);

        long orderId = Long.parseLong(req.getParametr(Vars.ORDER_ID.var));
        if ((orderDao != null) && orderDao.setStatus(orderId, OrderStatus.ACTIVE)) {
            List<Order> orderList = (List<Order>) req.getSession().getAttribute(Vars.ORDER_LIST.var);

            orderList.stream().filter(o -> o.getId() == orderId).findFirst().get().setStatus(OrderStatus.ACTIVE);

            req.getSession().setAttribute(Vars.ORDER_LIST.var, orderList);
            req.setAttribute(Vars.SUCCESS.var, true);
        } else {
            req.setAttribute(Vars.SUCCESS.var, false);
        }
        return ACTIVATE_ORDER;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
