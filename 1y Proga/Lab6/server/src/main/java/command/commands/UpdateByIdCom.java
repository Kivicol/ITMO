package command.commands;

import command.Server;
import data.Route;
import command.Receiver;
import command.exceptions.InvalidDataException;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class UpdateByIdCom implements BasicCommand{

    /**
     * Command 'Update by id'
     * Updates the object by it's id
     */

    @Serial
    private final static long serialVersionUID = 15L;

    Route route;
    public UpdateByIdCom(Route route) {
        this.route = route;
    }
    @Override
    public Response execute() {
        try {
            return new Response(ResponseStatuses.OK, Server.receiver.updateById(route));
        } catch (InvalidDataException e) {
            return new Response(ResponseStatuses.ERROR, e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "";
    }
}
