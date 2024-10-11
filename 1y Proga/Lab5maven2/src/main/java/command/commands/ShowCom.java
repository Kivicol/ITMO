package command.commands;

import command.Receiver;
import data.Route;

public class ShowCom implements BasicCommand {

    /**
     * Command 'Show'
     * Shows all elements in the collection
     */

    Receiver receiver;

    public ShowCom(Receiver rc) {
        this.receiver = rc;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            receiver.show();
        } else {
            System.out.println("You have inputted the command incorrectly");
        }
    }

    @Override
    public String getName() {
        return "Show";
    }

    @Override
    public String getDescription() {
        return "shows all elements in the collection";
    }
}
