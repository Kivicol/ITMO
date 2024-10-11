package command.commands;

import command.Receiver;
import command.Server;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class RemoveByIdCom implements BasicCommand{


    /**
     * Command 'Remove by id'
     * Removes the object by it's id
     */

    @Serial
    private final static long serialVersionUID = 11L;

    private int id;

    public RemoveByIdCom(int id) {
        this.id = id;
    }

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Server.receiver.remove(id));
    }

    @Override
    public String getName() {
        return "Remove {id}";
    }
}
