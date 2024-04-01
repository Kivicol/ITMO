package command.commands;

import command.Invoker;
import command.commands.BasicCommand;
import java.util.LinkedHashMap;

public class HelpCom implements BasicCommand{

    /**
     * Command 'help'
     * Shows all available commands
     */
    @Override
    public void execute(String[] args){
        if(args.length == 1) {
            Invoker invoker = new Invoker();
            LinkedHashMap<String, BasicCommand> commandList = Invoker.getCommandList();
            for (String name : commandList.keySet()) {
                BasicCommand command = commandList.get(name);
                System.out.println(command.getName() + " -- " + command.getDescription());
            }
        }else{
            System.out.println("You have inputted the command incorrectly");
        }
    }

    @Override
    public String getName() {
        return "Help";
    }

    @Override
    public String getDescription() {
        return "shows all available commands";
    }
}
