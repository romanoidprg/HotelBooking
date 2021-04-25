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

public enum DisplayOrdersByFilterCommand implements Command {
    INSTANCE;

    private static final ResponseContext DISPLAY_ORDERS = new ResponseContext() {
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
        return DISPLAY_ORDERS;
    }
}

