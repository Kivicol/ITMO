package command.commands;

import command.Invoker;

public class HistoryCom implements BasicCommand{

    /**
     * Command 'history'
     * Shows the last 10 used commands
     */
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            String[] history = new String[10];
            int n = 0;
            for (BasicCommand command : Invoker.tenComs) {
                history[n] = command.getName();
                n++;
            }
            for (n = 9; n >= 0; n--) {
                if (!(history[n] == null)) {
                    System.out.println(history[n]);
                }
            }
        }else{
            System.out.println("You have inputted the command incorrectly");
        }
    }

    @Override
    public String getName() {
        return "History";
    }

    @Override
    public String getDescription() {
        return "shows the last 10 used commands";
    }
}
