package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.DaoFactory;
import com.epam.jwd.hotel_booking.dao.impl.ClientDao;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;
import com.epam.jwd.hotel_booking.model.Client;
import com.epam.jwd.hotel_booking.model.ClientSearchPattern;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpSession;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class AdminMenuClientsShowFilterCommandTest {

    @Test
    public void execute() {
        ClientDao clientDao = Mockito.mock(ClientDao.class);
        DaoFactory daoFactory = Mockito.mock(DaoFactory.class);
        ClientSearchPattern clientSearchPattern = Mockito.mock(ClientSearchPattern.class);
        Mockito.when(daoFactory.getDao(ClientDao.class)).thenReturn(clientDao);
        Mockito.when(clientDao.findByClientPattern(clientSearchPattern))
                .thenReturn(Optional.empty());
        HttpSession session = Mockito.mock(HttpSession.class);

        RequestContext req = Mockito.mock(RequestContext.class);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(req.getParametr(Vars.BDAY_FROM.var)).thenReturn("1900-01-01");
        Mockito.when(req.getParametr(Vars.BDAY_TO.var)).thenReturn("2900-01-01");
        Mockito.when(req.getParametr(Vars.SEX.var)).thenReturn("MALE");

        AdminMenuClientsShowFilterCommand.INSTANCE.setDaoFactory(daoFactory);
        ResponseContext resp = AdminMenuClientsShowFilterCommand.INSTANCE.execute(req);


        assertEquals(Pages.ADMIN_MENU_USERS_SHOW_FILTER.page, resp.getPage());
    }
}