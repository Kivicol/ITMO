package command.commands;

import data.Route;

import java.io.Serial;

public class AddCom implements BasicCommand {

    /**
     * Command 'add'
     * Adds an element to the collection
     */

    @Serial
    private final static long serialVersionUID = 0L;

    private Route route;

    public AddCom(Route route) {
        this.route = route;
    }

}
