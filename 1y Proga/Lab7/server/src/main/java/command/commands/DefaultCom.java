package command.commands;

import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class DefaultCom implements BasicCommand{


    @Serial
    private final static long serialVersionUID = 3L;
    private UserData userdata;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, "Unknown command, type 'help' to see available commands");
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return null;
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
