package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.LoginRole;
import com.epam.jwd.hotel_booking.service.UserService;

public enum LoginCommand implements Command {
    INSTANCE;

    private static final ResponseContext LOGIN_ADMIN = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.MAIN_ADMIN.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext LOGIN_USER = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.MAIN_USER.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext LOGIN_NOT_SUCSESS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.INCORRECT_LOGIN.page;
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
        LoginRole loginRole = UserService.checkLoginRole(login, password);
        if (loginRole == LoginRole.UNKNOWN) {
            resp = LOGIN_NOT_SUCSESS;
        } else {
            if (loginRole == LoginRole.USER) {
                resp = LOGIN_USER;
            } else {
                resp = LOGIN_ADMIN;
            }
            req.getSession().setAttribute(Vars.ROLE.var, loginRole);
            req.getSession().setAttribute(Vars.LOGIN.var, login);
        }
        return resp;
    }
}

