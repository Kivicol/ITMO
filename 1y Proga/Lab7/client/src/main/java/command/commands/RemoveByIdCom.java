package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;

public class RemoveByIdCom implements BasicCommand{


    /**
     * Command 'Remove by id'
     * Removes the object by it's id
     */

    @Serial
    private final static long serialVersionUID = 11L;
    private UserData userdata;
    private int id;
    public RemoveByIdCom(int id) {
        this.id = id;
    }

    @Override
    public void setUser(UserData userdata) {

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
        return id;
    }
}
