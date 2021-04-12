package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.DaoFactory;
import com.epam.jwd.hotel_booking.dao.impl.ClientDao;
import com.epam.jwd.hotel_booking.dao.impl.LoginDao;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.ClientSearchPattern;
import com.epam.jwd.hotel_booking.model.Login;
import com.epam.jwd.hotel_booking.model.enums.Sex;
import com.epam.jwd.hotel_booking.service.ClientComparator;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public enum AdminMenuClientsShowFilterCommand implements Command {
    INSTANCE;

    private  DaoFactory daoFactory = new DaoFactory();

    private static final ResponseContext ADMIN_MENU_CLIENTS = new ResponseContext() {
        @Override
        public String getPage() {
            return Pages.ADMIN_MENU_USERS_SHOW_FILTER.page;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {

        ClientSearchPattern clientSearchPattern
                = (ClientSearchPattern) req.getSession().getAttribute(Vars.CLIENT_SEARCH_PATTERN.var);

        if ((clientSearchPattern == null) || ("true".equals(req.getParametr(Vars.NEW_SEARCH_CLIENT.var)))) {
            String name = req.getParametr(Vars.NAME_INCLUDE.var);
            String sName = req.getParametr(Vars.SNAME_INCLUDE.var);
            String eMail = req.getParametr(Vars.EMAIL_INCLUDE.var);
            String phone = req.getParametr(Vars.PHONE_INCLUDE.var);
            LocalDate birthDayFrom = LocalDate.parse(req.getParametr(Vars.BDAY_FROM.var));
            LocalDate birthDayTo = LocalDate.parse(req.getParametr(Vars.BDAY_TO.var));
            Sex sex = Sex.fromString(req.getParametr(Vars.SEX.var));
            String country = req.getParametr(Vars.COUNTRY.var);
            String address = req.getParametr(Vars.ADDRESS_INCLUDE.var);

            clientSearchPattern
                    = new ClientSearchPattern(name, sName, eMail, phone, birthDayFrom, birthDayTo, sex, country, address);

            req.getSession().setAttribute(Vars.CLIENT_SEARCH_PATTERN.var, clientSearchPattern);


            Optional<List<Client>> clientList = daoFactory.getDao(ClientDao.class).findByClientPattern(clientSearchPattern);

            if (clientList.isPresent()) {
                clientList.get().sort(ClientComparator.byId());

                LinkedHashMap<Client, String> clientMap = new LinkedHashMap<>();
                String loginName;
                LoginDao loginDao = daoFactory.getDao(LoginDao.class);
                for (Client client : clientList.get()) {
                    loginName = loginDao.findEntityByClientId(client.getId()).get().getLogin();
                    clientMap.put(client, loginName);
                }
                req.getSession().setAttribute(Vars.CLIENT_LIST.var, clientMap);
            }
        }
        return ADMIN_MENU_CLIENTS;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

}
