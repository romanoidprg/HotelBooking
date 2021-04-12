package com.epam.jwd.hotel_booking.command.admin;

import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.DaoFactory;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.enums.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpSession;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ActivateConfirmedOrderCommandTest {
    private RequestContext reqM;
    private ResponseContext respM;
    private final List<Order> orderList = new LinkedList<>();

    @Before
    public void setUp() throws Exception {
        Order order = new Order();
        orderList.add(order);

        reqM = Mockito.mock(RequestContext.class);
        Mockito.when(reqM.getParametr(Vars.ORDER_ID.var)).thenReturn("-1");

        HttpSession httpSessionM = Mockito.mock(HttpSession.class);

        respM = Mockito.mock(ResponseContext.class);
        Mockito.when(reqM.getSession()).thenReturn(httpSessionM);
        Mockito.when(reqM.getSession().getAttribute(Vars.ORDER_LIST.var)).thenReturn(orderList);

        OrderDao orderDaoM = Mockito.mock(OrderDao.class);
        Mockito.when(orderDaoM.setStatus(-1L, OrderStatus.ACTIVE)).thenReturn(true);

        DaoFactory daoFactoryM = Mockito.mock(DaoFactory.class);
        Mockito.when(daoFactoryM.getDao(OrderDao.class)).thenReturn(orderDaoM);

        ActivateConfirmedOrderCommand.INSTANCE.setDaoFactory(daoFactoryM);
    }

    @Test
    public void execute() {
        respM = ActivateConfirmedOrderCommand.INSTANCE.execute(reqM);
        assertEquals(Pages.ACTIVATE_ORDER.page, respM.getPage());
        assertEquals(OrderStatus.ACTIVE, orderList.get(0).getStatus());
    }
}