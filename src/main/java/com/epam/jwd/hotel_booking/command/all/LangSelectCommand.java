package com.epam.jwd.hotel_booking.command.all;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LangSelectCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        String loc = req.getParametr(Vars.LOCALE.var);
        ResourceBundle rb = ResourceBundle.getBundle(Vars.LANGPACKAGE.var, new Locale(loc.substring(0, 2), loc.substring(3)));
        req.getSession().setAttribute(Vars.RESOURCE_BUNDLE.var, rb);

        return new ResponseContext() {
            @Override
            public String getPage() {
                String page = (String) req.getSession().getAttribute(Vars.CURRENT_PAGE.var);
                return page.equals(Pages.LANG_SELECT.page) ? Pages.LOGIN.page : page;
            }

            @Override
            public boolean isRedirect() {
                return false;
            }
        };
    }

}
