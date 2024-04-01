package command.commands;

import command.Receiver;

public class SortCom implements BasicCommand{

    /**
     * Command 'Sort'
     * Sorts the collection
     */
    @Override
    public void execute(String[] args) {
        if (!(Receiver.getTable().isEmpty())) {
            if (args.length == 1) {
                Receiver.sort();
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
