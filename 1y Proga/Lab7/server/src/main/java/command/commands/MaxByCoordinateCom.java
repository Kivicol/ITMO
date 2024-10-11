package command.commands;

import command.CollectionManager;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class MaxByCoordinateCom implements BasicCommand{

    /**
     * Command 'Max_by_coordinate'
     * Returns the maximal element of the collection by "Coordinates" attribute
     */


    @Serial
    private final static long serialVersionUID = 9L;
    private UserData userdata;


    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.collectionManager.maxByCoordinate());
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "Max_by_coordinate";
    }

    @Override
    public Integer getIntArgument() {
        return null;
    }

    @Override
    public Route getRoute() {
        return null;
    }
}
