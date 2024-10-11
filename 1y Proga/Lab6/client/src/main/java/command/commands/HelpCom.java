package command.commands;


import java.io.Serial;
import java.io.Serializable;

public class HelpCom implements Serializable, BasicCommand {

    /**
     * Command 'help'
     * Shows all available commands
     */
    @Serial
    private final static long serialVersionUID = 6L;

    public HelpCom() {}
}
