package command.commands;
public interface BasicCommand {
    /**
     * Interface for all commands
     */
    public void execute(String[] args);
    public String getName();
    public String getDescription();
}
