package com.epam.jwd.hotel_booking.command;

public enum Pages {
    ACTIVATE_ORDER("WEB-INF/jsp/admin/activate_order.jsp"),
    ADD_CLIENT_TO_ORDER("WEB-INF/jsp/user/add_client_to_order.jsp"),
    ADMIN_MENU_INFO_PAGE("WEB-INF/jsp/admin/admin_menu_info.jsp"),
    ADMIN_MENU_ORDERS("WEB-INF/jsp/admin/admin_menu_orders.jsp"),
    ADMIN_MENU_USERS("WEB-INF/jsp/admin/admin_menu_users.jsp"),
    ADMIN_MENU_USERS_SHOWALL("WEB-INF/jsp/admin/admin_menu_users_showall.jsp"),
    ADMIN_MENU_USERS_SHOW_FILTER("WEB-INF/jsp/admin/admin_menu_clients_show_filter.jsp"),
    ADM_SHOW_ORDERS_BY_FILTER("WEB-INF/jsp/admin/adm_show_orders_by_filter.jsp"),
    ADM_SHOW_ORDERS_RELATED_TO_CLIENT("WEB-INF/jsp/admin/adm_show_orders_related_to_client.jsp"),
    ADM_SHOW_ORDERS_RELATED_TO_LOGIN("WEB-INF/jsp/admin/adm_show_orders_related_to_login.jsp"),
    ADM_SHOW_ORDER_DETAIL("WEB-INF/jsp/admin/adm_show_order_detail.jsp"),
    CANCEL_ORDER("WEB-INF/jsp/admin/cancel_order.jsp"),
    CLIENT_CREATE_SUCCESS("WEB-INF/jsp/user/client_create_success.jsp"),
    CONFIRM_ORDER("WEB-INF/jsp/admin/confirm_order.jsp"),
    DONE_ORDER("WEB-INF/jsp/admin/done_order.jsp"),
    INCORRECT_LOGIN("WEB-INF/jsp/login_if_incorrect.jsp"),
    INDEX("/index.jsp"),
    LANG_SELECT("WEB-INF/jsp/lang_select.jsp"),
    LOGIN("WEB-INF/jsp/login.jsp"),
    MAIN_ADMIN("WEB-INF/jsp/admin/main_admin.jsp"),
    MAIN_USER("WEB-INF/jsp/user/main_user.jsp"),
    ORDER_CREATE_NOT_SUCCESS("WEB-INF/jsp/user/order_create_not_success.jsp"),
    ORDER_CREATE_SUCCESS("WEB-INF/jsp/user/order_create_success.jsp"),
    ORDER_PREPARE("WEB-INF/jsp/user/order_prepare.jsp"),
    REDIRECT("redirect.jsp"),
    REDIRECT_TO_REGISTRATION("WEB-INF/jsp/registration.jsp"),
    REGISTRATION_NOT_SUCCESS("WEB-INF/jsp/user/registration_not_success.jsp"),
    REGISTRATION_SUCCESS("WEB-INF/jsp/user/registration_success.jsp"),
    SHOW_CLIENTS_LINK_TO_LOGIN("WEB-INF/jsp/user/user_menu_show_clients_link_to_login.jsp"),
    SHOW_CLIENTS_RELATED_TO_LOGIN("WEB-INF/jsp/admin/show_clients_related_to_login.jsp"),
    USER_MENU_CLIENTS("WEB-INF/jsp/user/user_menu_clients.jsp"),
    USER_MENU_INFO_PAGE("WEB-INF/jsp/user/user_menu_info.jsp"),
    USER_MENU_ORDERS("WEB-INF/jsp/user/user_menu_orders.jsp"),
    USR_SHOW_INVOICE("WEB-INF/jsp/user/invoice.jsp"),
    USR_SHOW_ORDERS_RELATED_TO_LOGIN("WEB-INF/jsp/user/user_menu_show_orders_related_to_login.jsp"),
    USR_SHOW_ORDER_DETAIL("WEB-INF/jsp/user/usr_show_order_detail.jsp");

    public final String page;

    private Pages (String page){
        this.page = page;
    }
}
