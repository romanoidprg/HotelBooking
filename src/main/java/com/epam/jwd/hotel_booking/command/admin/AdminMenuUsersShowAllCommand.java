package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.LoginDao;
import com.epam.jwd.hotel_booking.model.Login;

import java.util.List;
import java.util.Optional;

public enum AdminMenuUsersShowAllCommand implements Command {
    INSTANCE;

    private static final ResponseContext ADMIN_MENU_USERS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ADMIN_MENU_USERS_SHOWALL.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        LoginDao loginDao = new LoginDao();
        Optional<List<Login>> loginList = loginDao.findAll();
        if (loginList.isPresent()) {
        req.setAttribute(Vars.LOGIN_LIST.var,loginList.get());
        }
        return ADMIN_MENU_USERS;
    }

}
