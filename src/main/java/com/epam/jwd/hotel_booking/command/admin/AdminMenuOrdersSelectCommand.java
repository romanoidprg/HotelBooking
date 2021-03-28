package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;

public enum AdminMenuOrdersSelectCommand implements Command {
    INSTANCE;

    private static final ResponseContext ADMIN_MENU_ORDERS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ADMIN_MENU_ORDERS.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override

    public ResponseContext execute(RequestContext req) {
        return ADMIN_MENU_ORDERS;
    }

}
