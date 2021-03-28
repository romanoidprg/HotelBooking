package com.epam.jwd.hotel_booking.dao.impl;

import com.epam.jwd.hotel_booking.model.Login;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class LoginDaoTest {

    @Test
    public void findAll() {
//        LoginDao loginDao = new LoginDao();
//        Optional<List<Login>> logins = loginDao.findAll();
//
//        Login login1 = new Login(1,"AIvanov", "iva", false);
//        Login login2 = new Login(2,"MWonson", "won", false);
//        Login login3 = new Login(3,"AElova", "elo", false);
//        Login login4 = new Login(4,"Romanoid", "romanoid", true);
//
//        logins.ifPresent(loginList -> assertEquals(login1, loginList.get(0)));
//        logins.ifPresent(loginList -> assertEquals(login2, loginList.get(1)));
//        logins.ifPresent(loginList -> assertEquals(login3, loginList.get(2)));
//        logins.ifPresent(loginList -> assertEquals(login4, loginList.get(3)));

    }

    @Test
    public void findEntityByName() {
        LoginDao loginDao = new LoginDao();
        Optional<Login> login = loginDao.findEntityByName("Romanoid");

        Login loginExp = new Login(4,"Romanoid", "romanoid", false);

        login.ifPresent(log -> assertEquals(loginExp, log));
    }
}