package command.commands;

import command.Receiver;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class ShowCom implements BasicCommand {

    /**
     * Command 'Show'
     * Shows all elements in the collection
     */

    @Serial
    private final static long serialVersionUID = 12L;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.receiver.show());
    }

    @Override
    public String getName() {
        return "Show";
    }
}
