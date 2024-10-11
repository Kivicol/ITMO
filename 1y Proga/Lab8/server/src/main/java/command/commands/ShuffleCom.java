package command.commands;

import command.CollectionManager;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class ShuffleCom implements BasicCommand{

    /**
     * Command 'Shuffle'
     * Shuffles objects in collection
     */

    @Serial
    private final static long serialVersionUID = 13L;
    private UserData userdata;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.collectionManager.shuffle());
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "Shuffle";
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
