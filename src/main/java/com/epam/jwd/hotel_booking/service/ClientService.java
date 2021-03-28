package com.epam.jwd.hotel_booking.service;

import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.ClientDao;
import com.epam.jwd.hotel_booking.dao.impl.LoginDao;
import com.epam.jwd.hotel_booking.exceptions.NoClientFoundException;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.Login;
import com.epam.jwd.hotel_booking.model.enums.Sex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService {

    public static Client getClientFromOrder(RequestContext req) {
        final String name = req.getParametr(Vars.NAME.var);
        final String sName = req.getParametr(Vars.SNAME.var);
        final String email = req.getParametr(Vars.EMAIL.var);
        final String phone = req.getParametr(Vars.PHONE.var);
        final LocalDate bDay = LocalDate.parse(req.getParametr(Vars.BDAY.var));
        final String sex = req.getParametr(Vars.SEX.var);
        final String country = req.getParametr(Vars.COUNTRY.var);
        final String address = req.getParametr(Vars.ADDRESS.var);

        return new Client(0L, name, sName, email, phone, bDay, Sex.fromString(sex), country, address);
    }

    public static boolean assignClientToLogin(Login login, Client client) {
//        ClientDao clientDao = new ClientDao();
//        clientDao
        return false;
    }

    public static void createClientWithLinkToLoginAndPutToDb(Client client, String loginName) {
        ClientDao clientDao = new ClientDao();
        LoginDao loginDao = new LoginDao();
        Optional<Login> optionalLogin = loginDao.findEntityByName(loginName);
        Login login = optionalLogin.isPresent() ? optionalLogin.get() : new Login(0, "", "", false);
        clientDao.create(client);
        clientDao.createLinkToLogin(client, login);
    }

}
