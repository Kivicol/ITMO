package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class HistoryCom implements BasicCommand{

    /**
     * Command 'history'
     * Shows the last 10 used commands
     */

    @Serial
    private final static long serialVersionUID = 7L;
    private UserData userdata;
    public HistoryCom() {}

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
