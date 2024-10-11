package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class ShuffleCom implements BasicCommand{

    /**
     * Command 'Shuffle'
     * Shuffles objects in collection
     */

    @Serial
    private final static long serialVersionUID = 13L;
    private UserData userdata;
    public ShuffleCom() {}

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
