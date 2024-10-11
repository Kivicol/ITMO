package command.commands;

import command.utility.Response;
import command.utility.ResponseStatuses;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HelpCom implements Serializable, BasicCommand {

    /**
     * Command 'help'
     * Shows all available commands
     */
    @Serial
    private final static long serialVersionUID = 6L;

    @Override
    public Response execute() {
        return new Response(ResponseStatuses.OK, Arrays.stream(TypeOfCommand.values()).
                map(TypeOfCommand::getDescription).
                filter(description -> !description.isEmpty()).
                collect(Collectors.joining("\n")));
    }

    @Override
    public String getName() {
        return "Help";
    }

}
