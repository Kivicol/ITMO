package command;

import command.commands.*;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;

public class Invoker {

    /**
     * Class for checking commands inputted by user and executing them
     */

    private static LinkedHashMap<String, BasicCommand> commandList;
    public static ArrayDeque<BasicCommand> tenComs = new ArrayDeque<>();

    public Invoker() {
        commandList = new LinkedHashMap<>();
        commandList.put("add", new AddCom());
        commandList.put("clear", new ClearCom());
        commandList.put("count_less_than_distance", new CountLessThanDistanceCom());
        commandList.put("execute_script", new ExecuteScriptCom());
        commandList.put("exit", new ExitCom());
        commandList.put("help", new HelpCom());
        commandList.put("history", new HistoryCom());
        commandList.put("info", new InfoCom());
        commandList.put("max_by_coordinate", new MaxByCoordinateCom());
        commandList.put("min_by_name", new MinByNameCom());
        commandList.put("remove", new RemoveByIdCom());
        commandList.put("save", new SaveCom());
        commandList.put("show", new ShowCom());
        commandList.put("shuffle", new ShuffleCom());
        commandList.put("update", new UpdateByIdCom());
    }


    public static void startExecuting(String line) {
        String commandName = line.split(" ")[0];
        BasicCommand command = commandList.get(commandName);
        command.execute(line.split(" "));
        if (!(tenComs == null) && tenComs.size() == 10) {
            tenComs.pop();
            tenComs.addLast(command);
        } else {
            assert tenComs != null;
            tenComs.addFirst(command);
        }
    }

    public static LinkedHashMap<String, BasicCommand> getCommandList() {
        return commandList;
    }

}
