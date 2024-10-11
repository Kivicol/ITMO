package command.commands;

import command.Receiver;

public class ExitCom implements BasicCommand{

    /**
     * Command 'exit'
     * Exits the program
     */
    public ExitCom() {}
    @Override
    public void execute(String[] args){
        if (args.length == 1) {
            System.exit(1);
        }else{
            System.out.println("You have inputted the command incorrectly");
        }
    }

    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public String getDescription() {
        return "exit the program";
    }
}
