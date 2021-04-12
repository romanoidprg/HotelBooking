package com.epam.jwd.hotel_booking.listeners;

import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.dao.DaoFactory;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@WebListener
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        controlOrdersOutofdayStatusForToday(req);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }


    /**
     * Invoke  OrderDao.checkAndSetOutofdayAndNotClosedStatus() method if it wasn't invoked in current day.
     *
     * @param req HttpServletRequest object
     */
    private void controlOrdersOutofdayStatusForToday(HttpServletRequest req) {
        LocalDate nowDate = (LocalDate) req.getServletContext().getAttribute(Vars.NOW_DATE.var);
        DaoFactory daoFactory = new DaoFactory();
        OrderDao orderDao = daoFactory.getDao(OrderDao.class);
        if (nowDate != null) {
            if (nowDate.isBefore(LocalDate.now())) {
                orderDao.checkAndSetOutofdayAndNotClosedStatus();
            }
        } else {
            req.getServletContext().setAttribute(Vars.NOW_DATE.var, LocalDate.now());
            orderDao.checkAndSetOutofdayAndNotClosedStatus();
        }
    }
}
