package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class ShowCom implements BasicCommand {

    /**
     * Command 'Show'
     * Shows all elements in the collection
     */

    @Serial
    private final static long serialVersionUID = 12L;
    private UserData userdata;
    public ShowCom() {}

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
