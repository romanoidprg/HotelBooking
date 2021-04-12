package com.epam.jwd.hotel_booking.service;

import com.epam.jwd.hotel_booking.dao.DaoFactory;
import com.epam.jwd.hotel_booking.dao.impl.LoginDao;
import com.epam.jwd.hotel_booking.model.Login;
import com.epam.jwd.hotel_booking.model.LoginRole;

import java.util.Optional;

public class LoginService {
    private final DaoFactory daoFactory;

    public LoginService(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    public LoginRole checkLoginRole(String tryLogin, String tryPassword) {
        LoginDao loginDao = daoFactory.getDao(LoginDao.class);
        Optional<Login> login = loginDao.findEntityByName(tryLogin);
        if (login.isPresent() && login.get().hasLoginAndPassword(tryLogin, tryPassword)) {
            if (login.get().isAdmin()) {
                return LoginRole.ADMIN;
            } else {
                return LoginRole.USER;
            }
        } else {
            return LoginRole.UNKNOWN;
        }
    }

    public static boolean isLogAndPassCorrect(String tryLogin, String tryPassword) {
        return tryLogin.matches("\\p{Graph}+")
                && tryPassword.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{4,}");
    }

    public boolean changeLoginRole(String id) {
        LoginDao loginDao = daoFactory.getDao(LoginDao.class);
        Optional<Login> optionalLogin = loginDao.findEntityById(Long.parseLong(id));
        if (optionalLogin.isPresent()) {
            Login login = optionalLogin.get();
            login.setAdmin(!login.isAdmin());
            return loginDao.update(login).isPresent();
        } else {
            return false;
        }

    }

    public  boolean registerLogin(String login, String password){
        Login loginEntity = new Login(0L, login, password,false);
        LoginDao loginDao = daoFactory.getDao(LoginDao.class);
        return loginDao.create(loginEntity);
    }
}
