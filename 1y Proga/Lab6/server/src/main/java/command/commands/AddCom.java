package command.commands;

import command.Receiver;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;
import data.Route;

import java.io.Serial;

public class AddCom implements BasicCommand {

    /**
     * Command 'add'
     * Adds an element to the collection
     */

    @Serial
    private final static long serialVersionUID = 0L;
    private Route route;

    public AddCom(Route route){
        this.route = route;
    }

    @Override
    public Response execute() {
        Server.receiver.add(route);
        return new Response(ResponseStatuses.OK, "Object was added to the collection");
    }

    @Override
    public String getName() {
        return "Add";
    }
}
