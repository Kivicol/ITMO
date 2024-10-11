package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class RegistrationCom implements BasicCommand {
    @Serial
    public static final long serialVersionUID = 16L;
    private UserData userdata;
    public RegistrationCom(UserData userdata) {
        this.userdata = userdata;
    }
    @Override
    public void setUser(UserData userdata) {}

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
