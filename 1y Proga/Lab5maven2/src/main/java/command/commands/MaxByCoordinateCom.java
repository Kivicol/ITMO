package command.commands;

import command.Receiver;

public class MaxByCoordinateCom implements BasicCommand{

    /**
     * Command 'Max_by_coordinate'
     * Returns the maximal element of the collection by "Coordinates" attribute
     */

    Receiver receiver;
    public MaxByCoordinateCom(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        if (!(receiver.getTable().isEmpty())) {
            if (args.length == 1) {
                receiver.maxByCoordinate();
            } else {
                System.out.println("You have inputted the command incorrectly");
            }
        } else {
            System.err.println("Collection is empty, can't process the command");
        }
    }

    @Override
    public String getName() {
        return "Max_by_coordinate";
    }

    @Override
    public String getDescription() {
        return "returns the maximal element of the collection by \"Coordinates\" attribute";
    }
}
