package command.commands;

public enum TypeOfCommand {
    ADD(AddCom.class, "add: add an element to the collection"),
    CLEAR(ClearCom.class, "clear: clear the collection"),
    COUNT_LESS_THAN_DISTANCE(CountLessThanDistanceCom.class, "count_less_than_distance {distance}: Counts the number of elements whose distance is less than the specified one"),
    EXECUTE_SCRIPT(ExecuteScriptCom.class, "execute_script {filename}: execute script from file"),
    EXIT(ExitCom.class, "exit: exit the program"),
    HELP(HelpCom.class, "help: shows all available commands"),
    HISTORY(HistoryCom.class, "history: shows the last 10 commands"),
    INFO(InfoCom.class, "info: shows information about collection"),
    MAX_BY_COORDINATE(MaxByCoordinateCom.class, "max_by_coordinate: returns the maximal element of the collection by \"Coordinates\" attribute"),
    MIN_BY_NAME(MinByNameCom.class, "min_by_name: returns the minimal element of the collection by \"Name\" attribute"),
    REMOVE_BY_ID(RemoveByIdCom.class, "remove_by_id {id}: removes an element by its id"),
    SHOW(ShowCom.class, "show: shows all elements of the collection"),
    SHUFFLE(ShuffleCom.class, "shuffle: shuffles the collection"),
    SORT(SortCom.class, "sort: sorts the collection"),
    UPDATE_BY_ID(UpdateByIdCom.class, "update_by_id {id} {element}: updates an element by its id"),
    REGISTRATION(RegistrationCom.class, ""),
    DEFAULT(BasicCommand.class, "");;

    private final Class<? extends BasicCommand> executableClass;
    private final String description;

    TypeOfCommand(Class<? extends BasicCommand> executableClass, String description) {
        this.executableClass = executableClass;
        this.description = description;
    }
}
