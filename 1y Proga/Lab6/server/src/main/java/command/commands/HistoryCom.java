package command.commands;


import command.utility.CommandManager;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class HistoryCom implements BasicCommand{

    /**
     * Command 'history'
     * Shows the last 10 used commands
     */

    @Serial
    private final static long serialVersionUID = 7L;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, CommandManager.history.toString());
    }

    @Override
    public String getName() {
        return "History";
    }
}
