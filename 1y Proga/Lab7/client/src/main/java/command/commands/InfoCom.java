package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class InfoCom implements BasicCommand{

    /**
     * Command 'info'
     * Information about the collection
     */

    @Serial
    private final static long serialVersionUID = 8L;
    private UserData userdata;
    public InfoCom() {}

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
