package command.commands;

import command.CollectionManager;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class MinByNameCom implements BasicCommand{

    /**
     * Command 'Min_by_name'
     * Returns the minimal element of the collection by name
     */

    @Serial
    private final static long serialVersionUID = 10L;
    private UserData userdata;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.collectionManager.minByName());
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "Min_by_name";
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
