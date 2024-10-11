package command.commands;

public enum TypeOfCommand {
    ADD(AddCom.class, "Add: add an element to the collection"),
    CLEAR(ClearCom.class, "Clear: clear the collection"),
    COUNT_LESS_THAN_DISTANCE(CountLessThanDistanceCom.class, "Count_less_than_distance {distance}: Counts the number of elements whose distance is less than the specified one"),
    EXECUTE_SCRIPT(ExecuteScriptCom.class, "Execute_script {filename}: execute script from file"),
    EXIT(null, "Exit: exit the program"),
    HELP(HelpCom.class, "Help: shows all available commands"),
    HISTORY(HistoryCom.class, "History: shows the last 10 commands"),
    INFO(InfoCom.class, "Info: shows information about collection"),
    MAX_BY_COORDINATE(MaxByCoordinateCom.class, "Max_by_coordinate: returns the maximal element of the collection by \"Coordinates\" attribute"),
    MIN_BY_NAME(MinByNameCom.class, "Min_by_name: returns the minimal element of the collection by \"Name\" attribute"),
    REMOVE_BY_ID(RemoveByIdCom.class, "Remove_by_id {id}: removes an element by its id"),
    SHOW(ShowCom.class, "Show: shows all elements of the collection"),
    SHUFFLE(ShuffleCom.class, "Shuffle: shuffles the collection"),
    SORT(SortCom.class, "Sort: sorts the collection"),
    UPDATE_BY_ID(UpdateByIdCom.class, "Update_by_id {id} {element}: updates an element by its id"),
    DEFAULT(BasicCommand.class, "");;

    private final Class<? extends BasicCommand> executableClass;
    private final String description;

    TypeOfCommand(Class<? extends BasicCommand> executableClass, String description) {
        this.executableClass = executableClass;
        this.description = description;
    }
    public Class<? extends BasicCommand> getExecutableClass() {
        return executableClass;
    }
    public String getDescription(){
        return description;
    }
}
