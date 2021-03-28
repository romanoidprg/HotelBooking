package com.epam.jwd.hotel_booking.servlets;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.command.WrappingRequestContext;
import com.epam.jwd.hotel_booking.dao.impl.OrderDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/controller")
public class Servlet extends HttpServlet {
    private static final String COMMAND_PARAMETER_NAME = "command";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controlOrdersOutofdayStatusForToday(req);

        final String commandName = req.getParameter(COMMAND_PARAMETER_NAME);
        final Command businessCommand = Command.of(commandName);
        final ResponseContext result = businessCommand.execute(WrappingRequestContext.of(req));

        if (result.isRedirect()) {
            //todo
            resp.sendRedirect(result.getPage());

        } else {
            req.getSession().setAttribute(Vars.CURRENT_PAGE.var, result.getPage());
            final RequestDispatcher dispatcher = req.getRequestDispatcher(result.getPage());
            dispatcher.forward(req, resp);
        }
    }

    private void controlOrdersOutofdayStatusForToday(HttpServletRequest req) {
        LocalDate nowDate = (LocalDate) req.getServletContext().getAttribute(Vars.NOW_DATE.var);
        OrderDao orderDao = new OrderDao();
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

