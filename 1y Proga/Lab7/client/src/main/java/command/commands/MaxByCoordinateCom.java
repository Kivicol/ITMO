package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class MaxByCoordinateCom implements BasicCommand{

    /**
     * Command 'Max_by_coordinate'
     * Returns the maximal element of the collection by "Coordinates" attribute
     */

    @Serial
    private final static long serialVersionUID = 9L;
    private UserData userdata;
    public MaxByCoordinateCom() {}

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
