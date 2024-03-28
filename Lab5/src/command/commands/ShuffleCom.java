package src.command.commands;

import src.command.Receiver;

public class ShuffleCom implements BasicCommand{

    /**
     * Command 'Shuffle'
     * Shuffles objects in collection
     */
    @Override
    public void execute(String[] args) {
        if (!(Receiver.getTable().isEmpty())) {
            if (args.length == 1) {
                Receiver.shuffle();
                System.out.println("Collection is shuffled");
            } else {
                System.out.println("You have inputted the command incorrectly");
            }
        }else {
            System.err.println("Collection is empty, you can't shuffle it");
        }
    }

    @Override
    public String getName() {
        return "Shuffle";
    }

    @Override
    public String getDescription() {
        return "shuffles objects in collection";
    }
}
