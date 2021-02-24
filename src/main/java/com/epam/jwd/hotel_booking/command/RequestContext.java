package com.epam.jwd.hotel_booking.command;

import javax.servlet.http.HttpSession;

public interface RequestContext {

    void setAttribute(String name, Object obj);

    HttpSession getSession();

    String getParametr(String parametrName);



}
