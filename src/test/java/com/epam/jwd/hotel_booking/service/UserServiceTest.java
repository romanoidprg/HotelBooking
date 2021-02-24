package com.epam.jwd.hotel_booking.service;

import com.epam.jwd.hotel_booking.model.LoginRole;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void checkLoginRole() {
//        assertEquals(LoginRole.UNKNOWN, UserService.checkLoginRole("jhdhjajdl","sdjkjdsfl"));
//        assertEquals(LoginRole.ADMIN, UserService.checkLoginRole("Romanoid","romanoid"));
    }

    @Test
    public void isLogAndPassCorrect() {
        assertTrue(UserService.isLogAndPassCorrect("aaa","JJjdjd8383"));
        assertTrue(UserService.isLogAndPassCorrect("R","JJjd383"));
        assertFalse(UserService.isLogAndPassCorrect("R","djd383"));
    }
}