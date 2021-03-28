package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.Room;
import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;

public enum AddRoomToOrderCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        Order order = (Order) req.getSession().getAttribute(Vars.ORDER.var);
        RoomType roomType = RoomType.valueOf(req.getParametr(Vars.ROOM_TYPE.var));
        RoomSize roomSize = RoomSize.valueOf(req.getParametr(Vars.ROOM_SIZE.var));
        order.addRoom(new Room(-1, roomSize, roomType));
        req.getSession().setAttribute(Vars.ORDER.var, order);
        return GoToOrderPrepareCommand.INSTANCE.execute(req);
    }

}
