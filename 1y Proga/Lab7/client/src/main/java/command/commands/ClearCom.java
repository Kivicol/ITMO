package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class ClearCom implements BasicCommand{

    /**
     * Command 'clear'
     * Clears the collection
     */

    @Serial
    private final static long serialVersionUID = 1L;
    private UserData userdata;

    public ClearCom() {}

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
