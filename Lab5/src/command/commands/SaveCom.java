package src.command.commands;

import src.command.Receiver;
import src.command.Utility.FileManager;
import src.data.Route;

public class SaveCom implements BasicCommand{

    /**
     * Command 'save'
     * Saves the collection into a json file
     */
    @Override
    public void execute(String[] args){
        if(args.length == 1){
            Receiver.save();
        }else{
            System.out.println("You have inputted the command incorrectly");
        }
    }

    @Override
    public String getName() {
        return "Save";
    }

    @Override
    public String getDescription() {
        return "save the collection into a json file";
    }
}
