package command.commands;

import command.CollectionManager;
import command.Server;
import command.utility.DBManager;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class RemoveByIdCom implements BasicCommand{


    /**
     * Command 'Remove by id'
     * Removes the object by it's id
     */

    @Serial
    private final static long serialVersionUID = 11L;
    private UserData userdata;
    private int id;

    public RemoveByIdCom(int id) {
        this.id = id;
    }

    @Override
    public Response execute() {
        DBManager dbManager = new DBManager();
        if (dbManager.deleteElement(id, userdata.getLogin())) {
            return new Response(ResponseStatuses.OK, Server.collectionManager.remove(id));
        }
        return new Response(ResponseStatuses.OK, "Unable to remove object from the collection");
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "Remove_by_id {id}";
    }

    @Override
    public Integer getIntArgument() {
        return id;
    }

    @Override
    public Route getRoute() {
        return null;
    }
}
