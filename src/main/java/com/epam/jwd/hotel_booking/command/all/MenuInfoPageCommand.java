package com.epam.jwd.hotel_booking.command.all;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.LoginRole;
import com.epam.jwd.hotel_booking.model.enums.FoodPlan;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;
import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MenuInfoPageCommand implements Command {
    INSTANCE;

    private static final ResponseContext USER_MENU_INFO_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.USER_MENU_INFO_PAGE.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext ADMIN_MENU_INFO_PAGE = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ADMIN_MENU_INFO_PAGE.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {
        if (req.getSession().getAttribute(Vars.ROOM_SIZES.var) == null) {
            req.getSession().setAttribute(Vars.ROOM_SIZES.var, RoomSize.values());
        }
        if (req.getSession().getAttribute(Vars.ROOM_TYPES.var) == null) {
            req.getSession().setAttribute(Vars.ROOM_TYPES.var, RoomType.values());
        }
        if (req.getSession().getAttribute(Vars.FOOD_PLANS.var) == null) {
            req.getSession().setAttribute(Vars.FOOD_PLANS.var, FoodPlan.values());
        }
        if (req.getSession().getAttribute(Vars.STATUSES.var) == null) {
            req.getSession().setAttribute(Vars.STATUSES.var, OrderStatus.values());
        }

        if (LoginRole.ADMIN.equals(req.getSession().getAttribute(Vars.ROLE.var))) {
            return ADMIN_MENU_INFO_PAGE;
        } else {
            return USER_MENU_INFO_PAGE;
        }
    }

}
