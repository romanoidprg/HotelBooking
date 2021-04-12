package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.DaoFactory;
import com.epam.jwd.hotel_booking.model.LoginRole;
import com.epam.jwd.hotel_booking.service.LoginService;
import sun.rmi.runtime.Log;

public enum RegistrationCommand implements Command {
    INSTANCE;
    private final LoginService loginService = new LoginService(new DaoFactory());

    private static final ResponseContext REGISTRATION_SUCCESS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.REGISTRATION_SUCCESS.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext REGISTRATION_NOT_SUCCESS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.REGISTRATION_NOT_SUCCESS.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp;
        final String login = req.getParametr(Vars.LOGIN.var);
        final String password = req.getParametr(Vars.PASSWORD.var);
        if (LoginService.isLogAndPassCorrect(login, password)) {
            LoginRole loginRole = loginService.checkLoginRole(login, password);

            if ((loginRole == LoginRole.UNKNOWN) && (loginService.registerLogin(login, password))) {
                resp = REGISTRATION_SUCCESS;
            } else {
                resp = REGISTRATION_NOT_SUCCESS;
            }
        } else {
            resp = REGISTRATION_NOT_SUCCESS;
        }
        return resp;
    }
}

