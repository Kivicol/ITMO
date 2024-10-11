package command.commands;

import java.io.Serial;

public class RemoveByIdCom implements BasicCommand{


    /**
     * Command 'Remove by id'
     * Removes the object by it's id
     */

    @Serial
    private final static long serialVersionUID = 11L;
    private int id;
    public RemoveByIdCom(int id) {
        this.id = id;
    }
}
