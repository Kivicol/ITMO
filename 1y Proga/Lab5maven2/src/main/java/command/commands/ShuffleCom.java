package command.commands;

import command.Receiver;

public class ShuffleCom implements BasicCommand{

    /**
     * Command 'Shuffle'
     * Shuffles objects in collection
     */

    Receiver receiver;
    public ShuffleCom(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        if (!(receiver.getTable().isEmpty())) {
            if (args.length == 1) {
                receiver.shuffle();
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
