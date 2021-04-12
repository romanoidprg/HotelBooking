package com.epam.jwd.hotel_booking.command;

import com.epam.jwd.hotel_booking.command.user.ShowInvoiceCommand;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandManagerTest {

    @Test
    public void of() {
        assertEquals(ShowInvoiceCommand.INSTANCE, CommandManager.of("usr_show_invoice"));
    }
}