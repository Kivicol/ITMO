package command.commands;


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
    private UserData userdata;
    private ArrayList<BasicCommand> commands;

    public ExecuteScriptCom(ArrayList<BasicCommand> commands) {
        this.commands = commands;
    }

    @Override
    public void setUser(UserData userdata) {
        this.userdata = userdata;
    }

    @Override
    public UserData getUser() {
        return userdata;
    }

    @Override
    public Route getRoute() {
        return null;
    }

    @Override
    public Integer getInt() {
        return null;
    }
}
