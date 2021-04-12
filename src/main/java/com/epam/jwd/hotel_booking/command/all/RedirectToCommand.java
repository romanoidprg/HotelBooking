package com.epam.jwd.hotel_booking.command.all;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.LoginRole;
import com.epam.jwd.hotel_booking.service.CountryService;
import com.epam.jwd.hotel_booking.service.LoginService;

public enum RedirectToCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        String page = req.getParametr(Vars.PAGE_TO_REDIRECT.var);
        
        return new ResponseContext() {
            @Override
            public String getPage() {
                return page;
            }

            @Override
            public boolean isRedirect() {
                return false;
            }
        };
    }
}

