package command.commands;

import command.Receiver;
import data.Route;

public class ShowCom implements BasicCommand{

    /**
     * Command 'Show'
     * Shows all elements in the collection
     */
    @Override
    public void execute(String[] args){
        if(args.length == 1){
            for (Route rt : Receiver.getTable()) {
                System.out.println(rt);
            }
            if (Receiver.getTable().isEmpty()) {
                System.out.println("Collection is empty");
            }
        }else{
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
