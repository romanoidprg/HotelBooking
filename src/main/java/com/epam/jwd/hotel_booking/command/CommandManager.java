package com.epam.jwd.hotel_booking.command;

import com.epam.jwd.hotel_booking.command.admin.AdminMenuUsersSelectCommand;
import com.epam.jwd.hotel_booking.command.admin.AdminMenuUsersShowAllCommand;
import com.epam.jwd.hotel_booking.command.admin.SetLoginRoleCommand;
import com.epam.jwd.hotel_booking.command.page.LangSelectCommand;
import com.epam.jwd.hotel_booking.command.user.EnterIfSessionExistCommand;
import com.epam.jwd.hotel_booking.command.user.LoginCommand;
import com.epam.jwd.hotel_booking.command.user.LogoutCommand;
import com.epam.jwd.hotel_booking.command.user.RedirectToRegistrationCommand;
import com.epam.jwd.hotel_booking.command.user.RegistrationCommand;

public enum CommandManager {

    LOGIN(LoginCommand.INSTANCE),
    LOGOUT(LogoutCommand.INSTANCE),
    LANG_SELECT(LangSelectCommand.INSTANCE),
    REGISTRATION(RegistrationCommand.INSTANCE),
    REDIRECT_TO_REGISTRATION(RedirectToRegistrationCommand.INSTANCE),
    DEFAULT(LangSelectCommand.INSTANCE),
    ENTER_IF_SESSION(EnterIfSessionExistCommand.INSTANCE),
    SET_LOGIN_ROLE(SetLoginRoleCommand.INSTANCE),
    ADMIN_MENU_USERS_SELECT(AdminMenuUsersSelectCommand.INSTANCE),
    ADMIN_MENU_USERS_SHOWALL(AdminMenuUsersShowAllCommand.INSTANCE);


    private final Command command;

    CommandManager(Command command) {
        this.command = command;
    }

    static Command of(String name) {
        for (CommandManager action : values()) {
            if (action.name().equalsIgnoreCase(name)) {
                return action.command;
            }
        }
        return DEFAULT.command;
    }
}
