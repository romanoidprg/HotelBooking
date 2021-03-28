package com.epam.jwd.hotel_booking.command.user;

import com.epam.jwd.hotel_booking.command.Command;
import com.epam.jwd.hotel_booking.command.RequestContext;
import com.epam.jwd.hotel_booking.command.ResponseContext;
import com.epam.jwd.hotel_booking.command.Vars;
import com.epam.jwd.hotel_booking.model.Order;
import com.epam.jwd.hotel_booking.model.Room;
import com.epam.jwd.hotel_booking.model.enums.RoomSize;
import com.epam.jwd.hotel_booking.model.enums.RoomType;

public enum DeleteRoomFromOrderCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        Order order = (Order) req.getSession().getAttribute(Vars.ORDER.var);
        int id = Integer.parseInt(req.getParametr(Vars.ROOM_ID.var));
        RoomSize size = RoomSize.valueOf(req.getParametr(Vars.ROOM_SIZE.var));
        RoomType type = RoomType.valueOf(req.getParametr(Vars.ROOM_TYPE.var));
        Room room_for_delete = new Room(id, size, type);
        order.deleteRoom(room_for_delete);
        req.getSession().setAttribute(Vars.ORDER.var, order);
        return GoToOrderPrepareCommand.INSTANCE.execute(req);
    }

}
