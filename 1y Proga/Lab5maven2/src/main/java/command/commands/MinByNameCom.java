package command.commands;

import command.Receiver;

public class MinByNameCom implements BasicCommand{

    /**
     * Command 'Min_by_name'
     * Returns the minimal element of the collection by name
     */

    Receiver receiver;
    public MinByNameCom(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        if (!(receiver.getTable().isEmpty())) {
            if (args.length == 1) {
                receiver.minByName();
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
