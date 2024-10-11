package command.commands;

import command.Receiver;

public class SaveCom implements BasicCommand{

    /**
     * Command 'save'
     * Saves the collection into a json file
     */

    Receiver receiver;
    public SaveCom(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String[] args){
        if(args.length == 1){
            receiver.save();
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
