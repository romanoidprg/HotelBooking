package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.ClientDao;
import com.epam.jwd.hotel_booking.dao.impl.LoginDao;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.ClientSearchPattern;
import com.epam.jwd.hotel_booking.model.enums.Sex;
import com.epam.jwd.hotel_booking.service.ClientComparator;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public enum ShowClientsRelatedToLoginCommand implements Command {
    INSTANCE;

    private static final ResponseContext SHOW_CLIENTS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.SHOW_CLIENTS_RELATED_TO_LOGIN.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {

        String relatedLogin = req.getParametr(Vars.LOGIN_FOR_SHOW_CLIENTS.var);
        if (relatedLogin!=null) {
            Optional<List<Client>> clientList = new ClientDao().findAllLinkedToLoginName(relatedLogin);
            clientList.ifPresent(clients -> req.getSession().setAttribute(Vars.CLIENT_LIST.var, clients));
            req.getSession().setAttribute(Vars.LOGIN_FOR_SHOW_CLIENTS.var, relatedLogin);
        }
        return SHOW_CLIENTS;
    }

}
