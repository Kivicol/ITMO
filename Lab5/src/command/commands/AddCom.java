package src.command.commands;

import src.command.Receiver;
import src.command.Utility.RouteBuilder;
import src.command.exceptions.InvalidDataException;
import src.data.Route;

import java.util.LinkedList;

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
