package command.commands;

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

    @Override
    public void setUser(UserData userdata) {
        this.userdata = userdata;
    }
    @Override
    public UserData getUser() {
        return userdata;
    }
    @Override
    public Route getRoute() {
        return route;
    }

    @Override
    public Integer getInt() {
        return null;
    }

    public AddCom(Route route) {
        this.route = route;
    }

}
