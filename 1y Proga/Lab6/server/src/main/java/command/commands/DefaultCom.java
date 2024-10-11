package command.commands;

import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class DefaultCom implements BasicCommand{


    @Serial
    private final static long serialVersionUID = 3L;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, "Unknown command, type 'help' to see available commands");
    }

    @Override
    public String getName() {
        return null;
    }
}
