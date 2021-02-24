package com.epam.jwd.hotel_booking.command;

public interface Command {

    ResponseContext execute(RequestContext req);

    static Command of(String name) {
        return CommandManager.of(name);
    }

}
