package com.epam.jwd.hotel_booking.filters;

import com.epam.jwd.hotel_booking.command.Pages;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.LoginRole;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(urlPatterns = {"/controller"}, servletNames = {"Servlet"})
public class ControllerAccessFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        LoginRole role = (LoginRole) session.getAttribute(Vars.ROLE.var);
        String commandPrefix = req.getParameter("command").substring(0, 4);
        if (((role != LoginRole.ADMIN) && (commandPrefix.equalsIgnoreCase("ADM_")))
        || ((role != LoginRole.USER) && (commandPrefix.equalsIgnoreCase("USR_")))
        || ((role == null) && (!commandPrefix.equalsIgnoreCase("ALL_")))) {

            req.getSession().invalidate();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
