package command.commands;

import command.Receiver;

public class MinByNameCom implements BasicCommand{

    /**
     * Command 'Min_by_name'
     * Returns the minimal element of the collection by name
     */
    @Override
    public void execute(String[] args) {

        if (!(Receiver.getTable().isEmpty())) {
            if (args.length == 1) {
                Receiver.minByName();
            } else {
                System.out.println("You have inputted the command incorrectly");
            }
        } else {
            System.err.println("Collection is empty, can't process the command");
        }
    }

    @Override
    public String getName() {
        return "Min_by_name";
    }

    @Override
    public String getDescription() {
        return "returns the minimal element of the collection by name";
    }
}
