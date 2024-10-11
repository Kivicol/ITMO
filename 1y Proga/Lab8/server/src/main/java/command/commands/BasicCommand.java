package command.commands;

import command.exceptions.InvalidDataException;
import command.utility.Response;
import command.utility.UserData;
import data.Route;

import java.io.Serial;
import java.io.Serializable;

public interface BasicCommand extends Serializable {
    /**
     * Interface for all commands
     */
    @Serial
    static final long serialVersionUID = 12345L;
    Response execute();
    UserData getUser();
    String getName();
    Integer getIntArgument();
    Route getRoute();
}
