package com.epam.jwd.hotel_booking.service;

import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.impl.ClientDao;
import com.epam.jwd.hotel_booking.dao.impl.LoginDao;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.Login;
import com.epam.jwd.hotel_booking.model.enums.Sex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class ClientService {
    private final static Logger logger = LogManager.getLogger(ClientService.class);

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

    public static void createClientWithLinkToLoginAndPutToDb(Client client, String loginName) {
        try {
            ClientDao clientDao = new ClientDao();
            LoginDao loginDao = new LoginDao();
            Optional<Login> optionalLogin = loginDao.findEntityByName(loginName);
            if (optionalLogin.isPresent()) {
                clientDao.create(client);
                clientDao.createLinkToLogin(client, optionalLogin.get());
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

}
