package command.commands;

import command.Receiver;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class MaxByCoordinateCom implements BasicCommand{

    /**
     * Command 'Max_by_coordinate'
     * Returns the maximal element of the collection by "Coordinates" attribute
     */


    @Serial
    private final static long serialVersionUID = 9L;


    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.receiver.maxByCoordinate());
    }

    @Override
    public String getName() {
        return "Max_by_coordinate";
    }
}
