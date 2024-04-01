package command.commands;

import command.Receiver;
import command.exceptions.NoElementException;

public class RemoveByIdCom implements BasicCommand{


    /**
     * Command 'Remove by id'
     * Removes the object by it's id
     */
    @Override
    public void execute(String[] args) {
        try {
            long id = Long.parseLong(args[1]);
            Receiver.remove(id);
            System.out.println("Object successfully removed");
        } catch (NoElementException e) {
            System.err.println("There's no object with this id");;
        }
    }

    @Override
    public String getName() {
        return "Remove (id)";
    }

    @Override
    public String getDescription() {
        return "remove the object by it's id";
    }
}
