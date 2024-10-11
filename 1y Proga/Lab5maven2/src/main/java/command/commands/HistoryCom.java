package command.commands;

import command.Invoker;
import command.Receiver;

public class HistoryCom implements BasicCommand{

    /**
     * Command 'history'
     * Shows the last 10 used commands
     */

    Invoker invoker;
    public HistoryCom(Invoker invoker) {
        this.invoker = invoker;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            String[] history = new String[10];
            int n = 0;
            for (BasicCommand command : invoker.history) {
                history[n] = command.getName();
                n++;
            }
            for (n = 9; n >= 0; n--) {
                if (!(history[n] == null)) {
                    System.out.println(history[n]);
                } else {
                    System.out.println("No history at this point, try using some commands beforehand");
                    break;
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
