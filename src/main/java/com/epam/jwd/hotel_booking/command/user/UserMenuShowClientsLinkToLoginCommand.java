package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.ClientDao;
import com.epam.jwd.hotel_booking.dao.impl.LoginDao;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.Login;
import com.epam.jwd.hotel_booking.service.ClientService;

import java.util.List;
import java.util.Optional;

public enum UserMenuShowClientsLinkToLoginCommand implements Command {
    INSTANCE;

    private static final ResponseContext CLIENT_SHOW = new ResponseContext() {
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
            Optional<List<Client>> clientList = clientDao.findAllLinkedToLoginName(
                    (String) req.getSession().getAttribute(Vars.LOGIN.var));
            clientList.ifPresent(clients -> req.getSession().setAttribute(Vars.CLIENT_LIST.var, clients));
        return CLIENT_SHOW;
    }
}

