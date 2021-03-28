package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.service.LoginService;

public enum SetLoginRoleCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        String id = req.getParametr(Vars.LOGIN_ID_FOR_SET_ROLE.var);
        LoginService.changeLoginRole(id);
        return AdminMenuUsersShowAllCommand.INSTANCE.execute(req);
    }
}
