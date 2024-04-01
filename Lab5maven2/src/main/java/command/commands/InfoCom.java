package command.commands;

import command.Receiver;
import data.Route;

public class InfoCom implements BasicCommand{

    /**
     * Command 'info'
     * Information about the collection
     */
    @Override
    public void execute(String[] args){
        if(args.length == 1){
            System.out.println("Data -- " + Route.class);
            System.out.println("Count of elements -- " + Receiver.getTable().size());
            System.out.println("Initialization date -- " + Receiver.getInitDate());
        }else{
            System.out.println("You have inputted the command incorrectly");
        }
    }

    @Override
    public String getName() {
        return "Info";
    }

    @Override
    public String getDescription() {
        return "information about the collection";
    }
}
