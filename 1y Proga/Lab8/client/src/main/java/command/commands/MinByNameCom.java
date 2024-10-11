package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class MinByNameCom implements BasicCommand{

    /**
     * Command 'Min_by_name'
     * Returns the minimal element of the collection by name
     */

    @Serial
    private final static long serialVersionUID = 10L;
    private UserData userdata;

    public MinByNameCom() {}

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
        return null;
    }

    @Override
    public Integer getInt() {
        return null;
    }
}
