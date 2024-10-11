package command.commands;

import command.Receiver;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class ShuffleCom implements BasicCommand{

    /**
     * Command 'Shuffle'
     * Shuffles objects in collection
     */

    @Serial
    private final static long serialVersionUID = 13L;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.receiver.shuffle());
    }

    @Override
    public String getName() {
        return "Shuffle";
    }
}
