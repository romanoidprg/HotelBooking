package com.epam.jwd.hotel_booking.command;

public enum Vars {
    LOGIN("login"),
    PASSWORD("password"),
    LOCALE("locale"),
    RESOURCE_BUNDLE("rb"),
    ROLE("role"),
    CURRENT_PAGE("current_page"),
    LOGIN_LIST("login_list"),
    LOGIN_ID_FOR_SET_ROLE("login_id_for_set_role"),
    LANGPACKAGE("langpackage");

    public final String var;

    private Vars(String var) {
        this.var = var;
    }
}
