package command.commands;

import data.Route;

import java.io.Serial;

public class UpdateByIdCom implements BasicCommand{

    /**
     * Command 'Update by id'
     * Updates the object by it's id
     */

    @Serial
    private final static long serialVersionUID = 15L;
    Route route;
    public UpdateByIdCom(Route route) {
        this.route = route;
    }
}
