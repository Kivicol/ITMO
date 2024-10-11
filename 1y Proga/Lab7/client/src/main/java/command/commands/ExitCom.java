package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class ExitCom implements BasicCommand{

    /**
     * Command 'exit'
     * Exits the program
     */
    @Serial
    private final static long serialVersionUID = 5L;
    private UserData userdata;
    public ExitCom() {}

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
