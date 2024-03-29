package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.CommandManager;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.service.ClientService;

public enum UserMenuClientsCreateCommand implements Command {
    INSTANCE;

    private static final ResponseContext REDIRECT = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.REDIRECT.page;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };


    @Override
    public ResponseContext execute(RequestContext req) {

        ClientService.createClientWithLinkToLoginAndPutToDb(ClientService.getClientFromOrder(req),
                (String) req.getSession().getAttribute(Vars.LOGIN.var));

        req.getSession().setAttribute(Vars.REDIRECT_COMMAND.var, CommandManager.USR_DISPLAY_CLIENTS_CREATE_SUCCESS);
        return REDIRECT;
    }
}

