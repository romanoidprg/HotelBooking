package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;

public enum LogoutCommand implements Command {
    INSTANCE;
    private static final ResponseContext LOGOUT = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.INDEX.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public ResponseContext execute(RequestContext req) {
        req.getSession().invalidate();
        return LOGOUT;
    }
}
