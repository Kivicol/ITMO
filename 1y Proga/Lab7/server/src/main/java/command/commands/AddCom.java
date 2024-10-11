package command.commands;

import command.CollectionManager;
import command.Server;
import command.utility.DBManager;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
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
    private UserData userdata;

    public AddCom(Route route){
        this.route = route;
    }

    @Override
    public Response execute() {
        DBManager dbManager = new DBManager();
        long id = dbManager.addElement(route);
        if (id != 0) {
            route.setId(id);
            Server.collectionManager.add(route);
            return new Response(ResponseStatuses.OK, "Object was added to the collection");
        }
        return new Response(ResponseStatuses.OK, "Unable to add object to the collection");
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "Add";
    }

    @Override
    public Integer getIntArgument() {
        return null;
    }

    @Override
    public Route getRoute() {
        return route;
    }
}
