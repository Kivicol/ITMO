package command.commands;

import command.Receiver;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class SortCom implements BasicCommand{

    /**
     * Command 'Sort'
     * Sorts the collection
     */

    @Serial
    private final static long serialVersionUID = 14L;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.receiver.sort());
    }

    @Override
    public String getName() {
        return "Sort";
    }
}
