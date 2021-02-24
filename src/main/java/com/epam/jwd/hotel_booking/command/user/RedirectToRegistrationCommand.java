package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.LoginRole;
import com.epam.jwd.hotel_booking.service.UserService;

public enum RedirectToRegistrationCommand implements Command {
    INSTANCE;

    private static final ResponseContext REDIRECT_TO_REGISTRATION= new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.REDIRECT_TO_REGISTRATION.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public ResponseContext execute(RequestContext req) {
        return REDIRECT_TO_REGISTRATION;
    }
}

