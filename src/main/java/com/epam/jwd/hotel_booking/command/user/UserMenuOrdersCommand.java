package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;

public enum UserMenuOrdersCommand implements Command {
    INSTANCE;

    private static final ResponseContext USER_MENU_ORDERS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.USER_MENU_ORDERS.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        return USER_MENU_ORDERS;
    }

}
