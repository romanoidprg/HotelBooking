package com.epam.jwd.hotel_booking.command;

public enum Pages {
    LANG_SELECT("WEB-INF/jsp/lang_select.jsp"),
    INDEX("index.jsp"),
    LOGIN("WEB-INF/jsp/login.jsp"),
    INCORRECT_LOGIN("WEB-INF/jsp/login_if_incorrect.jsp"),
    MAIN_USER("WEB-INF/jsp/user/main_user.jsp"),
    REDIRECT_TO_REGISTRATION("WEB-INF/jsp/registration.jsp"),
    REGISTRATION_SUCCESS("WEB-INF/jsp/user/registration_success.jsp"),
    REGISTRATION_NOT_SUCCESS("WEB-INF/jsp/user/registration_not_success.jsp"),
    MAIN_ADMIN("WEB-INF/jsp/admin/main_admin.jsp"),
    ADMIN_MENU_USERS("WEB-INF/jsp/admin/admin_menu_users.jsp"),
    ADMIN_MENU_USERS_SHOWALL("WEB-INF/jsp/admin/admin_menu_users_showall.jsp");

    public final String page;

    private Pages (String page){
        this.page = page;
    }
}
