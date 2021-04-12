package com.epam.jwd.hotel_booking.service;

import com.epam.jwd.hotel_booking.dao.DaoFactory;
import com.epam.jwd.hotel_booking.dao.impl.LoginDao;
import com.epam.jwd.hotel_booking.model.Login;
import com.epam.jwd.hotel_booking.model.LoginRole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;

public class LoginServiceTest {
    private DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
    private LoginDao loginDao = Mockito.mock(LoginDao.class);
    private LoginService loginService = new LoginService(daoFactory);
    private Login loginAdmin = new Login(1, "admin", "admin", true);
    private Login loginUser = new Login(2, "user", "user", false);
    private Login loginUnkwn = new Login(3, "", "", false);
    private Login loginNew = new Login(0L, "user", "user", false);

    @Before
    public void setUp() throws Exception {
        Mockito.when(daoFactory.getDao(LoginDao.class)).thenReturn(loginDao);

    }

    @Test
    public void testCheckLoginRole() {

        Mockito.when(loginDao.findEntityByName("admin")).thenReturn(Optional.of(loginAdmin));
        Mockito.when(loginDao.findEntityByName("user")).thenReturn(Optional.of(loginUser));
        Mockito.when(loginDao.findEntityByName("unkwn")).thenReturn(Optional.of(loginUnkwn));

        assertEquals(LoginRole.ADMIN, loginService.checkLoginRole("admin", "admin"));
        assertEquals(LoginRole.USER, loginService.checkLoginRole("user", "user"));
        assertEquals(LoginRole.UNKNOWN, loginService.checkLoginRole("unkwn", "unkwn"));

    }

    @Test
    public void testIsLogAndPassCorrect() {
        assertTrue(LoginService.isLogAndPassCorrect("1", "J779s"));
        assertFalse(LoginService.isLogAndPassCorrect("Skkjsd", "J9s"));
        assertFalse(LoginService.isLogAndPassCorrect("", "J779s"));
        assertFalse(LoginService.isLogAndPassCorrect("1", "j779s"));
        assertFalse(LoginService.isLogAndPassCorrect("1", "Jsdvxs"));
    }

    @Test
    public void changeLoginRole() {
        assertFalse(loginUser.isAdmin());
        Mockito.when(loginDao.findEntityById(Long.parseLong("2"))).thenReturn(Optional.of(loginUser));
        Mockito.when(loginDao.findEntityById(Long.parseLong("3"))).thenReturn(Optional.empty());
        Mockito.when(loginDao.update(loginUser)).thenReturn(Optional.of(loginUser));
        assertTrue(loginService.changeLoginRole("2"));
        assertTrue(loginUser.isAdmin());
        assertFalse(loginService.changeLoginRole("3"));

    }

    @Test
    public void registerLogin() {
    }
}