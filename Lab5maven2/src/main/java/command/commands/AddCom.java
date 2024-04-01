package command.commands;

import command.Receiver;
import command.commands.BasicCommand;
import command.Utility.RouteBuilder;
import command.exceptions.InvalidDataException;

public class AddCom implements BasicCommand{

    /**
     * Command 'add'
     * Adds an element to the collection
     */
    @Override
    public void execute(String[] args){
        if (args.length == 1){
            try {
                System.out.println("Adding object to Route");
                Receiver.add(new RouteBuilder().create());
            } catch (InvalidDataException e) {
                System.err.println("There is a mistake in the provided data, try again");;
            }
        } else {
            System.out.println("You have inputted the command incorrectly");
        }
    }

    @Override
    public String getName() {
        return "Add";
    }

    @Override
    public String getDescription() {
        return "adds an element to the collection";
    }
}
