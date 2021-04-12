package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.ClientDao;

public enum DeleteClientCommand implements Command {
    INSTANCE;

    private static final ResponseContext CLIENT_DELETE = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.SHOW_CLIENTS_LINK_TO_LOGIN.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public ResponseContext execute(RequestContext req) {
        ClientDao clientDao = new ClientDao();
        String idstr = req.getParametr(Vars.ID_CLIENT_TO_DELETE.var);
        long id = Long.parseLong(idstr);
        clientDao.delete(id);
        return UserMenuShowClientsLinkToLoginCommand.INSTANCE.execute(req);
    }
}

