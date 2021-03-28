package com.epam.jwd.hotel_booking.command;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface RequestContext {

    void setAttribute(String name, Object obj);

    HttpSession getSession();

    String getParametr(String parametrName);

    String[] getParametrValues(String parametrName);

    Map<String, String[]> getParameterMap();



}
