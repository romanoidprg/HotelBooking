package com.epam.jwd.hotel_booking.command.all;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.CommandManager;
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

public enum ShowOrderDetailCommand implements Command {
    INSTANCE;

    private static final ResponseContext USER_SHOW = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.USR_SHOW_ORDER_DETAIL.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext ADMIN_SHOW = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ADM_SHOW_ORDER_DETAIL.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public ResponseContext execute(RequestContext req) {
        String lastCommand = (req.getSession().getAttribute(Vars.LAST_SHOW_ORDERS_COMMAND.var)).toString();

        if (lastCommand.equalsIgnoreCase(CommandManager.ADM_SHOW_ORDERS_RELATED_TO_CLIENT.name())) {
            req.setAttribute(Vars.RELATED_CLIENT_ID.var, req.getParametr(Vars.RELATED_CLIENT_ID.var));
        }

        long orderId = Long.parseLong(req.getParametr(Vars.ORDER_ID_TO_SHOW_DETAIL.var));
        Order orderDetail = ((List<Order>) req.getSession().getAttribute(Vars.ORDER_LIST.var))
                .stream().filter(order -> order.getId() == orderId).findFirst().get();
        req.setAttribute(Vars.ORDER_DETAIL.var, orderDetail);

        if (req.getSession().getAttribute(Vars.ROLE.var) == LoginRole.ADMIN) {
            if (orderDetail.getStatus() == OrderStatus.AWAITING) {
                setEmptyRoomsToRequestContext(req, orderDetail);
            }
            return ADMIN_SHOW;
        } else {
            return USER_SHOW;
        }
    }

    private void setEmptyRoomsToRequestContext(RequestContext req, Order orderDetail) {
        if (orderDetail.getStatus().equals(OrderStatus.AWAITING)) {
            RoomDao roomDao = new RoomDao();
            Optional<List<Room>> roomOptional = roomDao.findEmptyForPeriod(orderDetail.getDateIn(), orderDetail.getDateOut());
            roomOptional.ifPresent(rooms -> req.setAttribute(Vars.EMPTY_ROOMS.var, rooms));
        }
    }
}

