package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.LoginRole;

public enum EnterIfSessionExistCommand implements Command {
    INSTANCE;

    private static final ResponseContext ENTER_ADMIN = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.MAIN_ADMIN.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext ENTER_USER = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.MAIN_USER.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext LOGIN = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.LANG_SELECT.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        LoginRole currentRole = (LoginRole) req.getSession().getAttribute(Vars.ROLE.var);
        ResponseContext resp;
        if (currentRole == null) {
            resp = LOGIN;
        } else {
            if (currentRole == LoginRole.USER) {
                resp = ENTER_USER;
            } else {
                resp = ENTER_ADMIN;
            }
        }
        return resp;
    }
}

