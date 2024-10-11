package command.commands;

import command.Receiver;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;
import data.Route;

import java.io.Serial;

public class InfoCom implements BasicCommand{

    /**
     * Command 'info'
     * Information about the collection
     */

    @Serial
    private final static long serialVersionUID = 8L;



    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.receiver.getInfo());
    }

    @Override
    public String getName() {
        return "Info";
    }
}
