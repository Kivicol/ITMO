package command.commands;


import java.io.Serial;
import java.util.ArrayList;

public class ExecuteScriptCom implements BasicCommand {


    /**
     * Command 'execute_script'
     * Executes the script from the provided file
     */

    @Serial
    private final static long serialVersionUID = 4L;
    private ArrayList<BasicCommand> commands;

    public ExecuteScriptCom(ArrayList<BasicCommand> commands) {
        this.commands = commands;
    }
}
