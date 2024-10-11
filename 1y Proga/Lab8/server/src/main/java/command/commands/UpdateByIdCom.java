package command.commands;

import command.CollectionManager;
import command.Server;
import command.utility.DBManager;
import command.utility.UserData;
import data.Route;
import command.exceptions.InvalidDataException;
import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;

public class UpdateByIdCom implements BasicCommand{

    /**
     * Command 'Update by id'
     * Updates the object by it's id
     */

    @Serial
    private final static long serialVersionUID = 15L;
    private UserData userdata;

    Route route;
    public UpdateByIdCom(Route route) {
        this.route = route;
    }
    @Override
    public Response execute() {
        DBManager dbManager = new DBManager();
        if (dbManager.updateElement(route, userdata.getLogin())) return new Response(ResponseStatuses.OK, Server.collectionManager.updateById(route));
        return new Response(ResponseStatuses.OK, "You can not modify this element");
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "";
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
