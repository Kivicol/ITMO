package command.commands;

import command.Invoker;
import command.Utility.FileUtil;
import command.Utility.ScriptUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class ExecuteScriptCom implements BasicCommand{

    /**
     * Command 'execute_script'
     * Executes the script from the provided file
     */
    @Override
    public void execute(String[] args) {
        String path = System.getenv("FILE_PATH") + args[args.length - 1];
        try {
            FileUtil.setFileMode(true);
            ScriptUtil.pushFile(path);
            for (String line = ScriptUtil.readfile(); line != null; line = ScriptUtil.readfile()) {
                try{
                    String[] cmd = (line).split(" ", 2);
                    if (cmd[0].isBlank()) return;
                    if (cmd[0].equals("execute_script")) {
                        if (ScriptUtil.fileReapeting(cmd[1])){
                            System.err.println("Found recursion in " + new File(cmd[1]).getAbsolutePath() + ", exiting");

                            continue;
                        }

                    }
                    System.out.println("\033[35m" + "Executing command " + cmd[0] + "\033[0m");
                    Invoker.startExecuting(String.join(" ", cmd));
                    if (cmd[0].equals("execute_script")){
                        ScriptUtil.popfile();
                    }
                } catch (NoSuchElementException ignored) {}
            }
            ScriptUtil.popfile();
        }
        catch (FileNotFoundException fileNotFoundException){
            System.err.println("No such file exists");
        } catch (IOException e) {
            System.err.println("Input output error");
        }catch (NoSuchElementException ignored) {
        }
        FileUtil.setFileMode(false);
    }

    @Override
    public String getName() {
        return "Execute_script";
    }

    @Override
    public String getDescription() {
        return "execute the script from the provided file";
    }
}
