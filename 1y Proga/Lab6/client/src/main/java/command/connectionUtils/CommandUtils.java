package command.connectionUtils;

import command.commands.TypeOfCommand;

public class CommandUtils {
    public static TypeOfCommand getCommandType(String message){
        try {
            return TypeOfCommand.valueOf(message.toUpperCase());
        } catch (IllegalArgumentException e){
            return TypeOfCommand.DEFAULT;
        }
    }
}
