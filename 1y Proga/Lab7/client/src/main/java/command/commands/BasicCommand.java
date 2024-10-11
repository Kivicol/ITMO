package command.commands;

import command.utility.UserData;
import data.Route;

import java.io.Serial;
import java.io.Serializable;

public interface BasicCommand extends Serializable {
    /**
     * Interface for all commands
     */
    @Serial
    long serialVersionUID = 12345L;
    void setUser(UserData userdata);
    UserData getUser();
    Route getRoute();
    Integer getInt();
}
