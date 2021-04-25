package com.epam.jwd.hotel_booking.command;

import com.epam.jwd.hotel_booking.command.admin.ActivateConfirmedOrderCommand;
import com.epam.jwd.hotel_booking.command.admin.AdminMenuClientsShowFilterCommand;
import com.epam.jwd.hotel_booking.command.admin.AdminMenuOrdersSelectCommand;
import com.epam.jwd.hotel_booking.command.admin.AdminMenuUsersSelectCommand;
import com.epam.jwd.hotel_booking.command.admin.AdminMenuUsersShowAllCommand;
import com.epam.jwd.hotel_booking.command.admin.CancelAwaitingOrderCommand;
import com.epam.jwd.hotel_booking.command.admin.ConfirmAwaitingOrderCommand;
import com.epam.jwd.hotel_booking.command.admin.DisplayOrdersByFilterCommand;
import com.epam.jwd.hotel_booking.command.admin.DisplayOrdersRelatedToClientCommand;
import com.epam.jwd.hotel_booking.command.admin.DoneActiveOrderCommand;
import com.epam.jwd.hotel_booking.command.admin.SetLoginRoleCommand;
import com.epam.jwd.hotel_booking.command.admin.ShowClientsRelatedToLoginCommand;
import com.epam.jwd.hotel_booking.command.admin.ShowOrdersByFilterCommand;
import com.epam.jwd.hotel_booking.command.admin.ShowOrdersRelatedToClientCommand;
import com.epam.jwd.hotel_booking.command.all.DisplayOrdersRelatedToLoginCommand;
import com.epam.jwd.hotel_booking.command.all.LangSelectCommand;
import com.epam.jwd.hotel_booking.command.all.RedirectToCommand;
import com.epam.jwd.hotel_booking.command.user.AddClientsToOrderCommand;
import com.epam.jwd.hotel_booking.command.user.AddRoomToOrderCommand;
import com.epam.jwd.hotel_booking.command.user.DeleteClientCommand;
import com.epam.jwd.hotel_booking.command.user.DeleteClientFromOrderCommand;
import com.epam.jwd.hotel_booking.command.user.DeleteRoomFromOrderCommand;
import com.epam.jwd.hotel_booking.command.user.EnterIfSessionExistCommand;
import com.epam.jwd.hotel_booking.command.user.GoToAddClientToOrderCommand;
import com.epam.jwd.hotel_booking.command.user.GoToOrderPrepareCommand;
import com.epam.jwd.hotel_booking.command.all.LoginCommand;
import com.epam.jwd.hotel_booking.command.all.LogoutCommand;
import com.epam.jwd.hotel_booking.command.all.MenuInfoPageCommand;
import com.epam.jwd.hotel_booking.command.user.RedirectToRegistrationCommand;
import com.epam.jwd.hotel_booking.command.user.RegistrationCommand;
import com.epam.jwd.hotel_booking.command.user.SendOrderForSubmitCommand;
import com.epam.jwd.hotel_booking.command.user.SetClientFoodplanCommand;
import com.epam.jwd.hotel_booking.command.user.SetOrderDateInCommand;
import com.epam.jwd.hotel_booking.command.user.SetOrderDateOutCommand;
import com.epam.jwd.hotel_booking.command.all.ShowOrderDetailCommand;
import com.epam.jwd.hotel_booking.command.all.ShowOrdersRelatedToLoginCommand;
import com.epam.jwd.hotel_booking.command.user.ShowInvoiceCommand;
import com.epam.jwd.hotel_booking.command.user.UserMenuClientsCommand;
import com.epam.jwd.hotel_booking.command.user.UserMenuClientsCreateCommand;
import com.epam.jwd.hotel_booking.command.user.UserMenuOrdersCommand;
import com.epam.jwd.hotel_booking.command.user.UserMenuShowClientsLinkToLoginCommand;

public enum CommandManager {

    ALL_LOGIN(LoginCommand.INSTANCE),
    ALL_LOGOUT(LogoutCommand.INSTANCE),
    ALL_LANG_SELECT(LangSelectCommand.INSTANCE),
    ALL_REGISTRATION(RegistrationCommand.INSTANCE),
    ALL_REDIRECT_TO_REGISTRATION(RedirectToRegistrationCommand.INSTANCE),
    ALL_DEFAULT(LangSelectCommand.INSTANCE),
    ALL_ENTER_IF_SESSION(EnterIfSessionExistCommand.INSTANCE),
    ALL_SHOW_ORDERS_RELATED_TO_LOGIN(ShowOrdersRelatedToLoginCommand.INSTANCE),
    ALL_DISPLAY_ORDERS_RELATED_TO_LOGIN(DisplayOrdersRelatedToLoginCommand.INSTANCE),
    ALL_SHOW_ORDER_DETAIL(ShowOrderDetailCommand.INSTANCE),
    ALL_MENU_INFO(MenuInfoPageCommand.INSTANCE),
    ALL_REDIRECT_TO(RedirectToCommand.INSTANCE),

    ADM_ADMIN_MENU_CLIENTS_SHOWFILTER(AdminMenuClientsShowFilterCommand.INSTANCE),
    ADM_SET_LOGIN_ROLE(SetLoginRoleCommand.INSTANCE),
    ADM_ADMIN_MENU_USERS_SELECT(AdminMenuUsersSelectCommand.INSTANCE),
    ADM_ADMIN_MENU_ORDERS_SELECT(AdminMenuOrdersSelectCommand.INSTANCE),
    ADM_ADMIN_MENU_USERS_SHOWALL(AdminMenuUsersShowAllCommand.INSTANCE),
    ADM_CONFIRM_AWAITING_ORDER(ConfirmAwaitingOrderCommand.INSTANCE),
    ADM_CANCEL_AWAITING_ORDER(CancelAwaitingOrderCommand.INSTANCE),
    ADM_ACTIVATE_CONFIRMED_ORDER(ActivateConfirmedOrderCommand.INSTANCE),
    ADM_SHOW_ORDERS_RELATED_TO_CLIENT(ShowOrdersRelatedToClientCommand.INSTANCE),
    ADM_DISPLAY_ORDERS_RELATED_TO_CLIENT(DisplayOrdersRelatedToClientCommand.INSTANCE),
    ADM_SHOW_CLIENTS_RELATED_TO_LOGIN(ShowClientsRelatedToLoginCommand.INSTANCE),
    ADM_SHOW_ORDERS_BY_FILTER(ShowOrdersByFilterCommand.INSTANCE),
    ADM_DISPLAY_ORDERS_BY_FILTER(DisplayOrdersByFilterCommand.INSTANCE),
    ADM_DONE_ACTIVE_ORDER(DoneActiveOrderCommand.INSTANCE),

    USR_USER_MENU_CLIENTS(UserMenuClientsCommand.INSTANCE),
    USR_USER_MENU_CLIENTS_CREATE(UserMenuClientsCreateCommand.INSTANCE),
    USR_USER_MENU_SHOW_CLIENTS_LINK_TO_LOGIN(UserMenuShowClientsLinkToLoginCommand.INSTANCE),
    USR_DELETE_CLIENT(DeleteClientCommand.INSTANCE),
    USR_USER_MENU_ORDERS(UserMenuOrdersCommand.INSTANCE),
    USR_GOTO_PREPARE_ORDER(GoToOrderPrepareCommand.INSTANCE),
    USR_SET_ORDER_DATE_IN(SetOrderDateInCommand.INSTANCE),
    USR_SET_ORDER_DATE_OUT(SetOrderDateOutCommand.INSTANCE),
    USR_SET_CLIENT_FOODPLAN(SetClientFoodplanCommand.INSTANCE),
    USR_DELETE_CLIENT_FROM_ORDER(DeleteClientFromOrderCommand.INSTANCE),
    USR_GOTO_ADD_CLIENT_TO_ORDER(GoToAddClientToOrderCommand.INSTANCE),
    USR_ADD_CLIENTS_TO_ORDER(AddClientsToOrderCommand.INSTANCE),
    USR_ADD_ROOM_TO_ORDER(AddRoomToOrderCommand.INSTANCE),
    USR_DELETE_ROOM_FROM_ORDER(DeleteRoomFromOrderCommand.INSTANCE),
    USR_SEND_ORDER_FOR_SUBMIT(SendOrderForSubmitCommand.INSTANCE),
    USR_SHOW_INVOICE(ShowInvoiceCommand.INSTANCE);


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
        return ALL_DEFAULT.command;
    }
}
