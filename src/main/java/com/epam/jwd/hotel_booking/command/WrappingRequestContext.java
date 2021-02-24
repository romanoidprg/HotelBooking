package com.epam.jwd.hotel_booking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;

public class WrappingRequestContext implements RequestContext {

    private final HttpServletRequest request;

    private WrappingRequestContext(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setAttribute(String name, Object obj) {
        request.setAttribute(name, obj);
    }

    @Override
    public HttpSession getSession() {
        return request.getSession();
    }

    @Override
    public String getParametr(String parametrName) {
        return request.getParameter(parametrName);
    }

    public static RequestContext of(HttpServletRequest request) {
        return new WrappingRequestContext(request);
    }
}
