package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class CountLessThanDistanceCom implements BasicCommand{

    /**
     * Command 'Count_less_than_distance'
     * Counts the number of elements whose distance is less than the specified one
     */

    @Serial
    private final static long serialVersionUID = 2L;
    private UserData userdata;

    private float distance;

    public CountLessThanDistanceCom(float dist) {
        this.distance = dist;
    }

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
