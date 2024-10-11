package command.commands;

import command.Receiver;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class ClearCom implements BasicCommand {

    /**
     * Command 'clear'
     * Clears the collection
     */

    @Serial
    private final static long serialVersionUID = 1L;


    @Override
    public Response execute() {
        Server.receiver.clear();
        return new Response(ResponseStatuses.OK, "Collection was cleared");
    }

    @Override
    public String getName() {
        return "Clear";
    }

}
