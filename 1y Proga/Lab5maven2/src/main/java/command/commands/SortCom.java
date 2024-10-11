package command.commands;

import command.Receiver;

public class SortCom implements BasicCommand{

    /**
     * Command 'Sort'
     * Sorts the collection
     */

    Receiver receiver;
    public SortCom(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        if (!(receiver.getTable().isEmpty())) {
            if (args.length == 1) {
                receiver.sort();
                System.out.println("Collection is sorted");
            } else {
                System.out.println("You have inputted the command incorrectly");
            }
        }else {
            System.err.println("Collection is empty, you can't sort it");
        }
    }

    @Override
    public String getName() {
        return "Sort";
    }

    @Override
    public String getDescription() {
        return "sorts the collection";
    }
}
