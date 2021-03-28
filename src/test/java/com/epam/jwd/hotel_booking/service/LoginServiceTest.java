package com.epam.jwd.hotel_booking.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginServiceTest {

    @Test
    public void checkLoginRole() {
//        assertEquals(LoginRole.UNKNOWN, LoginService.checkLoginRole("jhdhjajdl","sdjkjdsfl"));
//        assertEquals(LoginRole.ADMIN, LoginService.checkLoginRole("Romanoid","romanoid"));
    }

    @Test
    public void isLogAndPassCorrect() {
        assertTrue(LoginService.isLogAndPassCorrect("aaa","JJjdjd8383"));
        assertTrue(LoginService.isLogAndPassCorrect("R","JJjd383"));
        assertFalse(LoginService.isLogAndPassCorrect("R","djd383"));
    }
}