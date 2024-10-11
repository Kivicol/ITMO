package command.commands;

import command.CollectionManager;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class SortCom implements BasicCommand{

    /**
     * Command 'Sort'
     * Sorts the collection
     */

    @Serial
    private final static long serialVersionUID = 14L;
    private UserData userdata;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.collectionManager.sort());
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "Sort";
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
