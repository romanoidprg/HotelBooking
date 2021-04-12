package com.epam.jwd.hotel_booking.servlets;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.command.WrappingRequestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Servlet extends HttpServlet {
    private static final String COMMAND_NAME = "command";
    private final Logger logger = LogManager.getLogger(Servlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String commandName = req.getParameter(COMMAND_NAME);
        final Command businessCommand = Command.of(commandName);
        final ResponseContext result = businessCommand.execute(WrappingRequestContext.of(req));
        logger.info("COMMAND======" + commandName + "======");

        req.getSession().setAttribute(Vars.CURRENT_PAGE.var, result.getPage());

        if (result.isRedirect()) {
            String page = req.getContextPath() + "/" + result.getPage();
            resp.sendRedirect(page);
        } else {
            final RequestDispatcher dispatcher = req.getRequestDispatcher(result.getPage());
            dispatcher.forward(req, resp);
        }
    }

}

