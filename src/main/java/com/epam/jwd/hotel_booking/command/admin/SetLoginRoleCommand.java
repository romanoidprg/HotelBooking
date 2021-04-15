package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.CommandManager;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.DaoFactory;
import com.epam.jwd.hotel_booking.service.LoginService;

public enum SetLoginRoleCommand implements Command {
    INSTANCE;
    private final LoginService loginService = new LoginService(new DaoFactory());

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
        String id = req.getParametr(Vars.LOGIN_ID_FOR_SET_ROLE.var);
        loginService.changeLoginRole(id);
        req.getSession().setAttribute(Vars.REDIRECT_COMMAND.var, CommandManager.ADM_ADMIN_MENU_USERS_SHOWALL);
        return REDIRECT;
//        return AdminMenuUsersShowAllCommand.INSTANCE.execute(req);
    }
}
