package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class SortCom implements BasicCommand{

    /**
     * Command 'Sort'
     * Sorts the collection
     */

    @Serial
    private final static long serialVersionUID = 14L;
    private UserData userdata;
    public SortCom() {}

    @Override
    public void setUser(UserData userdata) {
        this.userdata = userdata;
    }

    @Override
    public UserData getUser() {
        return null;
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
