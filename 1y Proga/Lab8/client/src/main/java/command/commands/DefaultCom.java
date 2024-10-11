package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class DefaultCom implements BasicCommand {
    @Serial
    private final static long serialVersionUID = 3L;
    private UserData userdata;

    public DefaultCom() {}

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
