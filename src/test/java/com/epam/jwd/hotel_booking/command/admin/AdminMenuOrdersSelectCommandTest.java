package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AdminMenuOrdersSelectCommandTest {

    @Test
    public void execute() {
        RequestContext req = Mockito.mock(RequestContext.class);
        ResponseContext resp = AdminMenuOrdersSelectCommand.INSTANCE.execute(req);

        assertEquals(Pages.ADMIN_MENU_ORDERS.page, resp.getPage());
    }
}