package command.utility;

import command.commands.*;
import command.exceptions.InvalidDataException;
import data.Route;

import java.util.Arrays;

public class CommandFactory {


    public static BasicCommand createCommand(TypeOfCommand type, String[] input) throws InvalidDataException {
        if (input.length == 0) {
            input = new String[]{};
        } else {
            input = Arrays.copyOfRange(input, 1, input.length);
        }
        return switch (type) {
            case ADD -> {
                Route route;
                RouteBuilder routeBuilder = new RouteBuilder();
                route = routeBuilder.create();
                yield new AddCom(route);
            }
            case CLEAR -> new ClearCom();
            case COUNT_LESS_THAN_DISTANCE -> {
                if (input.length != 1) {
                    System.out.println("Wrong amount of arguments for command " + type.name());
                    yield null;
                }
                try {
                    yield new CountLessThanDistanceCom(Float.parseFloat(input[0]));
                } catch (NumberFormatException e) {
                    System.out.println("Argument must be float");
                    yield null;
                }
            }
            case EXIT -> {
                System.out.println("Exiting...");
                System.exit(0);
                yield null;
            }
            case HELP -> new HelpCom();
            case HISTORY -> new HistoryCom();
            case INFO -> new InfoCom();
            case MAX_BY_COORDINATE -> new MaxByCoordinateCom();
            case MIN_BY_NAME -> new MinByNameCom();
            case REMOVE_BY_ID -> {
                if (input.length != 1) {
                    System.out.println("Wrong amount of arguments for command " + type.name());
                    yield null;
                }
                try {
                    yield new RemoveByIdCom(Integer.parseInt(input[0]));
                } catch (NumberFormatException e) {
                    System.out.println("Argument must be integer");
                    yield null;
                }
            }
            case SHOW -> new ShowCom();
            case SHUFFLE -> new ShuffleCom();
            case SORT -> new SortCom();
            case UPDATE_BY_ID -> {
                if (input.length != 1) {
                    System.out.println("Wrong amount of arguments for command " + type.name());
                    yield null;
                }
                try {
                    long id = Long.parseLong(input[0]);
                    Route route;
                    RouteBuilder routeBuilder = new RouteBuilder();
                    route = routeBuilder.create();
                    route.setId(id);
                    yield new UpdateByIdCom(route);
                } catch (NumberFormatException e) {
                    System.out.println("id must be Long");
                    yield null;
                } catch (InvalidDataException e) {
                    System.out.println();
                    yield null;
                }
            }
            case REGISTRATION -> {
                UserData userData;
                UserForm userForm = new UserForm(new Console());
                userData = userForm.build();
                if (userData != null) yield new RegistrationCom(userData);
                yield null;
            }
            default -> {
                System.out.println("Unknown command ");
                System.out.print("\033[32m> \033[0m");
                yield null;
            }
        };
    }
}