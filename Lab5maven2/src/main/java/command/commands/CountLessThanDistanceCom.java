package command.commands;

import command.Receiver;

public class CountLessThanDistanceCom implements BasicCommand{

    /**
     * Command 'Count_less_than_distance'
     * Counts the number of elements whose distance is less than the specified one
     */
    @Override
    public void execute(String[] args) {
        if (!(Receiver.getTable().isEmpty())) {
            float dist = Float.parseFloat(args[1]);
            Receiver.countLessThanDistance(dist);
        } else {
            System.err.println("Collection is empty, can't process the command");
        }
    }

    @Override
    public String getName() {
        return "Count_less_than_distance";
    }

    @Override
    public String getDescription() {
        return "Counts the number of elements whose distance is less than the specified one";
    }
}
