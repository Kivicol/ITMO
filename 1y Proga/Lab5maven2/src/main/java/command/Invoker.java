package command;

import command.commands.*;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;

public class Invoker {

    /**
     * Class for checking commands inputted by user and executing them
     */

    private final LinkedHashMap<String, BasicCommand> commandList;
    public ArrayDeque<BasicCommand> history = new ArrayDeque<>();

    public Invoker(Receiver receiver) {
        commandList = new LinkedHashMap<>();
        commandList.put("add", new AddCom(receiver));
        commandList.put("clear", new ClearCom(receiver));
        commandList.put("count_less_than_distance", new CountLessThanDistanceCom(receiver));
        commandList.put("execute_script", new ExecuteScriptCom(this));
        commandList.put("exit", new ExitCom());
        commandList.put("help", new HelpCom());
        commandList.put("history", new HistoryCom(this));
        commandList.put("info", new InfoCom(receiver));
        commandList.put("max_by_coordinate", new MaxByCoordinateCom(receiver));
        commandList.put("min_by_name", new MinByNameCom(receiver));
        commandList.put("remove", new RemoveByIdCom(receiver));
        commandList.put("save", new SaveCom(receiver));
        commandList.put("show", new ShowCom(receiver));
        commandList.put("shuffle", new ShuffleCom(receiver));
        commandList.put("sort", new SortCom(receiver));
        commandList.put("update", new UpdateByIdCom(receiver));
    }


    public void startExecuting(String line) {
        String commandName = line.split(" ")[0];
        BasicCommand command = commandList.get(commandName);
        command.execute(line.split(" "));
        if (!(history == null) && history.size() == 10) {
            history.pop();
            history.addLast(command);
        } else {
            if (history != null) {
                history.addFirst(command);
            }
        }
    }

    public LinkedHashMap<String, BasicCommand> getCommandList() {
        return commandList;
    }

}
