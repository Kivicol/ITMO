package command.utility;


import command.commands.AddCom;
import command.commands.BasicCommand;
import command.commands.TypeOfCommand;
import command.commands.UpdateByIdCom;
import command.connectionUtils.CommandFactory;
import command.connectionUtils.CommandUtils;
import command.exceptions.InvalidDataException;
import data.Route;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class ScriptExecutor {

    private final File scriptFile;
    private final ArrayList<BasicCommand> commandQueue = new ArrayList<>();

    public ScriptExecutor(File scriptFile) {
        this.scriptFile = scriptFile;
    }

    public ArrayList<BasicCommand> getCommandList() {
        return commandQueue;
    }

    private ScriptExecutor readScript(File scriptFile) {
        List<String> lines;
        try {
            lines = Files.readAllLines(scriptFile.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Unable to read script file:" + ((!scriptFile.exists()) ? " file not found" : " no permissions to read file"));
            System.out.print("\033[32m> \033[0m");
            return this;
        }
        try {
            FileUtil.setFileMode(true);
            ScriptUtil.pushFile(scriptFile.getName());

            for (int index = 0; index < lines.size(); index++) {
                String line = lines.get(index);
                TypeOfCommand type = CommandUtils.getCommandType(line.split(" ")[0]);
                String[] args = Arrays.copyOfRange(line.split(" "), 1, line.split(" ").length);
                BasicCommand command = null;
                if (type == TypeOfCommand.EXECUTE_SCRIPT) {
                    if (ScriptUtil.fileReapeting(new File(args[0]).getPath())) {
                        System.err.println("Found recursion in script, breaking");
                        break;
                    }
                    if (new File(args[0]).exists()) {
                        this.readScript(new File(args[0]));
                        continue;
                    }
                }
                if (type == TypeOfCommand.UPDATE_BY_ID) {
                    if (args.length != 1 || index + 10 >= lines.size()) {
                        System.out.println("Wrong amount of arguments for command " + type.name() + ", line skipped");
                        continue;
                    }
                    String[] newArgs = lines.subList(index + 1, index + 11).toArray(new String[0]);
                    args = new String[newArgs.length + 1];
                    args[0] = line.split(" ")[1];
                    System.arraycopy(newArgs, 0, args, 1, newArgs.length);
                    index += 10;
                    Route route = new RouteBuilder().create(args);
                    if (route != null) {
                        try{
                            route.setId(Long.parseLong(args[0]));
                        }catch (NumberFormatException e){
                            System.out.println("Wrong argument for command " + type.name() + ", line skipped");
                            continue;
                        }
                        command = new UpdateByIdCom(route);
                    }
                } else if (Objects.equals(TypeOfCommand.ADD, type)) {
                    if (args.length != 0 || index + 10 >= lines.size()) {
                        System.out.println("Wrong amount of arguments for command " + type + ", line skipped");
                        continue;
                    }
                    String[] rtArgs = lines.subList(index + 1, index + 11).toArray(new String[0]);
                    index += 10;
                    Route route = new RouteBuilder().create(rtArgs);
                    if (route != null) {
                        command = new AddCom(route);
                    } else {
                        System.out.println("Wrong arguments for " + type + ", line skipped");
                    }
                }else {
                    command = CommandFactory.createCommand(type, line.split(" "));
                }
                if (command != null) {
                    commandQueue.add(command);
                }
            ScriptUtil.popFile();
            return this;
        }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("No such file exists");
        } catch (IOException e) {
            System.err.println("Input output error");
        } catch (NoSuchElementException ignored) {
        }
        FileUtil.setFileMode(false);
        return this;
    }

    public ScriptExecutor readScript() {
        return this.readScript(scriptFile);
    }


}
