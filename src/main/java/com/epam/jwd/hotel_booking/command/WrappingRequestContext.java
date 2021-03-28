package com.epam.jwd.hotel_booking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class WrappingRequestContext implements RequestContext {

    private final HttpServletRequest request;

    private WrappingRequestContext(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return request.getParameterMap();
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

    @Override
    public String[] getParametrValues(String parametrName) {
        return request.getParameterValues(parametrName);
    }

    public static RequestContext of(HttpServletRequest request) {
        return new WrappingRequestContext(request);
    }
}
