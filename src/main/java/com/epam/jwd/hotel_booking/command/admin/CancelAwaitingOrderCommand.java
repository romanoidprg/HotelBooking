package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;
import com.epam.jwd.hotel_booking.dao.impl.RoomDao;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.Room;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public enum CancelAwaitingOrderCommand implements Command {
    INSTANCE;

    private static final ResponseContext CANCEL_ORDER = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.CANCEL_ORDER.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {

        long orderId = Long.parseLong(req.getParametr(Vars.ORDER_ID.var));

        OrderDao orderDao = new OrderDao();

        if (orderDao.setStatus(orderId, OrderStatus.CANCELED)) {
            List<Order> orderList = (List<Order>) req.getSession().getAttribute(Vars.ORDER_LIST.var);

            orderList.stream().filter(o -> o.getId() == orderId).findFirst().get().setStatus(OrderStatus.CANCELED);

            req.getSession().setAttribute(Vars.ORDER_LIST.var, orderList);
            req.setAttribute(Vars.SUCCESS.var, true);
        } else {
            req.setAttribute(Vars.SUCCESS.var, false);
        }

        return CANCEL_ORDER;
    }

}
