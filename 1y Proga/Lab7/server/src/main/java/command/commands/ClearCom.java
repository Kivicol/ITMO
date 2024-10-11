package command.commands;

import command.CollectionManager;
import command.Server;
import command.utility.DBManager;
import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;
import java.util.HashSet;

public class ClearCom implements BasicCommand {

    /**
     * Command 'clear'
     * Clears the collection
     */

    @Serial
    private final static long serialVersionUID = 1L;
    private UserData userdata;


    @Override
    public Response execute() {
        DBManager dbManager = new DBManager();
        HashSet<Integer> ids = dbManager.clear(userdata.getLogin());
        if (ids.isEmpty()){
            return new Response(ResponseStatuses.OK, "Collection doesn't contain any elements");
        }
        for (Integer id : ids) {
            Server.collectionManager.remove(id);
        }
        return new Response(ResponseStatuses.OK, "Collection was cleared");
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "Clear";
    }

    @Override
    public Integer getIntArgument() {
        return null;
    }

    @Override
    public Route getRoute() {
        return null;
    }

}
