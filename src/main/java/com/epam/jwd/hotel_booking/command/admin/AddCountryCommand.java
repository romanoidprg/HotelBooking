package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;

public enum AddCountryCommand implements Command {
    INSTANCE;

    private static final ResponseContext LOGIN = new ResponseContext() {
        @Override
        public String getPage() {
            return "WEB-INF/jsp/login.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        return LOGIN;
    }

}
