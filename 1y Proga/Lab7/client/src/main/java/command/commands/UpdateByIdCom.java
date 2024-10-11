package command.commands;

import command.utility.UserData;
import data.Route;

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
}
