package command.commands;

import command.CollectionManager;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class InfoCom implements BasicCommand{

    /**
     * Command 'info'
     * Information about the collection
     */

    @Serial
    private final static long serialVersionUID = 8L;
    private UserData userdata;


    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.collectionManager.getInfo());
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "Info";
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
