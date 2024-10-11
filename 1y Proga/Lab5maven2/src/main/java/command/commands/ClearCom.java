package command.commands;

import command.Receiver;

public class ClearCom implements BasicCommand{

    /**
     * Command 'clear'
     * Clears the collection
     */
    Receiver receiver;
    public ClearCom(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            if (!(receiver.getTable().isEmpty())){
                System.out.println("Clearing collection...");
                receiver.clear();
                System.out.println("Collection cleared");
            } else {
                System.err.println("Collection is empty, why would you clear it from non-existent objects");
            }
        }else{
            System.out.println("You have inputted the command incorrectly");
        }
    }

    @Override
    public String getName() {
        return "Clear";
    }

    @Override
    public String getDescription() {
        return "clear the collection";
    }
}
