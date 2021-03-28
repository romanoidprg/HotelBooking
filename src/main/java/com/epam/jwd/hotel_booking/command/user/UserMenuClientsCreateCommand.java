package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.service.ClientService;

public enum UserMenuClientsCreateCommand implements Command {
    INSTANCE;

    private static final ResponseContext CLIENT_CREATE = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.CLIENT_CREATE_SUCCESS.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public ResponseContext execute(RequestContext req) {

        ClientService.createClientWithLinkToLoginAndPutToDb(ClientService.getClientFromOrder(req),
                (String) req.getSession().getAttribute(Vars.LOGIN.var));

        return CLIENT_CREATE;
    }
}

