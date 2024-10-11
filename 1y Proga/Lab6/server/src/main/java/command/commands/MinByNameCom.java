package command.commands;

import command.Receiver;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class MinByNameCom implements BasicCommand{

    /**
     * Command 'Min_by_name'
     * Returns the minimal element of the collection by name
     */

    @Serial
    private final static long serialVersionUID = 10L;


    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.receiver.minByName());
    }

    @Override
    public String getName() {
        return "Min_by_name";
    }
}
