package command.commands;


import command.utility.Response;
import command.utility.ResponseStatuses;
import command.utility.UserData;
import data.Route;

import java.io.Serial;
import java.util.ArrayList;

public class ExecuteScriptCom implements BasicCommand {


    /**
     * Command 'execute_script'
     * Executes the script from the provided file
     */

    @Serial
    private final static long serialVersionUID = 4L;

    private final ArrayList<BasicCommand> commandStack;
    private UserData userdata;
    public ExecuteScriptCom(ArrayList<BasicCommand> commands) {
        this.commandStack = commands;
    }

    @Override
    public Response execute() {
        if (commandStack.isEmpty()) return new Response(ResponseStatuses.ERROR, "The command queue is empty. ");
        StringBuilder output = new StringBuilder();
        commandStack.forEach(command -> output.append(command.execute().getResponse()).append("\n"));
        return new Response(ResponseStatuses.OK, output.substring(0, output.length() - 1));
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public String getName() {
        return "Execute_script";
    }

    @Override
    public Integer getIntArgument() {
        return null;
    }

    @Override
    public Route getRoute() {
        return null;
    }


}
