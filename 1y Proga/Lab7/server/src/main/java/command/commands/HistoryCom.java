package command.commands;


import command.utility.CommandManager;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class HistoryCom implements BasicCommand{

    /**
     * Command 'history'
     * Shows the last 10 used commands
     */

    @Serial
    private final static long serialVersionUID = 7L;
    private UserData userdata;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, CommandManager.history.toString());
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "History";
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
