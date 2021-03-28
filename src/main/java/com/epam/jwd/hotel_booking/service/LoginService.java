package com.epam.jwd.hotel_booking.service;

import com.epam.jwd.hotel_booking.dao.impl.LoginDao;
import com.epam.jwd.hotel_booking.model.Login;
import com.epam.jwd.hotel_booking.model.LoginRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public static LoginRole checkLoginRole(String tryLogin, String tryPassword) {
        LoginDao loginDao = new LoginDao();
        Optional<Login> login = loginDao.findEntityByName(tryLogin);
        if (login.isPresent() && login.get().getLogin().equals(tryLogin) && login.get().getPassword().equals(tryPassword)) {
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

    public static boolean changeLoginRole(String id) {
        LoginDao loginDao = new LoginDao();
        Optional<Login> optionalLogin = loginDao.findEntityById(Long.parseLong(id));
        if (optionalLogin.isPresent()) {
            Login login = optionalLogin.get();
            login.setAdmin(!login.isAdmin());
            return loginDao.update(login).isPresent();
        } else {
            return false;
        }

    }

    public static boolean registerLogin(String login, String password){
        Login loginEntity = new Login(0L, login, password,false);
        LoginDao loginDao = new LoginDao();
        return loginDao.create(loginEntity);
    }
}
