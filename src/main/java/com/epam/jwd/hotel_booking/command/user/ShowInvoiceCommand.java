package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.RoomDao;
import com.epam.jwd.hotel_booking.model.LoginRole;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.Room;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public enum ShowInvoiceCommand implements Command {
    INSTANCE;

    private static final ResponseContext USER_SHOW = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.USR_SHOW_INVOICE.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        long orderId = Long.parseLong(req.getParametr(Vars.ORDER_ID.var));
        Order orderDetail = ((List<Order>) req.getSession().getAttribute(Vars.ORDER_LIST.var))
                .stream().filter(order -> order.getId() == orderId).findFirst().get();
        req.setAttribute(Vars.ORDER_DETAIL.var, orderDetail);

        return USER_SHOW;
    }

}

