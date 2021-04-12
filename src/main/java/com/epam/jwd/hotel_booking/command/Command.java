package com.epam.jwd.hotel_booking.command;

import com.epam.jwd.hotel_booking.dao.DaoFactory;

public interface Command {

    ResponseContext execute(RequestContext req);

    static Command of(String name) {
        return CommandManager.of(name);
    }


}
