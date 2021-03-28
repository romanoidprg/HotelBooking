package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;

public enum AdminMenuUsersSelectCommand implements Command {
    INSTANCE;

    private static final ResponseContext ADMIN_MENU_USERS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ADMIN_MENU_USERS.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override

    public ResponseContext execute(RequestContext req) {
        req.getSession().removeAttribute(Vars.NAME_INCLUDE.var);
        return ADMIN_MENU_USERS;
    }

}
