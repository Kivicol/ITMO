package command.commands;


import command.utility.UserData;
import data.Route;

import java.io.Serial;
import java.io.Serializable;

public class HelpCom implements Serializable, BasicCommand {

    /**
     * Command 'help'
     * Shows all available commands
     */
    @Serial
    private final static long serialVersionUID = 6L;
    private UserData userdata;

    public HelpCom() {}

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
