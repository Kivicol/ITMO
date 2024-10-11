package command.commands;

import java.io.Serial;
import java.io.Serializable;

public interface BasicCommand extends Serializable {
    /**
     * Interface for all commands
     */
    @Serial
    long serialVersionUID = 12345L;
}
