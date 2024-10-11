package command.utility;

import command.exceptions.FileModeException;
import data.Route;

public class ReadManager implements Readable {

    private final ReaderWriter console;
    private final Reader scanner;
    public ReadManager(ReaderWriter console){
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ScriptUtil()
                : new InputByHand();

    }
    @Override
    public String readName() {
        String name;
        while (true) {
            console.write("Imput name: ");
            name = console.readLine();
            if (name.isBlank() || name.isEmpty()) {
                console.printError("Name can't be empty");
                if (Console.isFileMode()) throw new FileModeException();
            }
            else {
                return name;
            }
        }
    }

    @Override
    public Integer readCoordinateX() {
        while (true) {
            console.write("Input coordinate X: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                console.printError("The specified number must be \"long\"");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    @Override
    public Long readCoordinateY() {
        while (true) {
            console.write("Input coordinate Y: ");
            String input = scanner.nextLine();
            try {
                return Long.parseLong(input);
            }
            catch (NumberFormatException e) {
                console.printError("The specified number must be \"int\"");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    @Override
    public Long readLocationFromY() {
        while (true) {
            console.write("Input location from Y: ");
            String input = scanner.nextLine();
            try {
                return Long.parseLong(input);
            }
            catch (NumberFormatException e) {
                console.printError("The specified number must be \"int\"");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    @Override
    public Integer readLocationFromX() {
        while (true) {
            console.write("Input location from X: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                console.printError("The specified number must be \"int\"");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    @Override
    public String readLocationFromName() {
        while (true) {
            console.write("Input location from name: ");
            String input = scanner.nextLine();
            if (input.isBlank() || input.isEmpty()) {
                console.printError("Name can't be empty");
                if (Console.isFileMode()) throw new FileModeException();
            }
            else {
                return input;
            }
        }
    }

    @Override
    public Long readLocationToY() {
        while (true) {
            console.write("Input location to Y: ");
            String input = scanner.nextLine();
            try {
                return Long.parseLong(input);
            }
            catch (NumberFormatException e) {
                console.printError("The specified number must be \"int\"");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    @Override
    public Integer readLocationToX() {
        while (true) {
            console.write("Input location to X: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                console.printError("The specified number must be \"int\"");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }

    @Override
    public String readLocationToName() {
        while (true) {
            console.write("Input location to name: ");
            String input = scanner.nextLine();
            if (input.isBlank() || input.isEmpty()) {
                console.printError("Name can't be empty");
                if (Console.isFileMode()) throw new FileModeException();
            }
            else {
                return input;
            }
        }
    }

    @Override
    public Float readDistance() {
        while (true) {
            console.write("Input distance: ");
            String input = scanner.nextLine();
            try {
                return Float.parseFloat(input);
            }
            catch (NumberFormatException e) {
                console.printError("The specified number must be \"float\"");
                if (Console.isFileMode()) throw new FileModeException();
            }
        }
    }


    @Override
    public String readLogin() {
        while (true) {
            console.write("Input login: ");
            String input = scanner.nextLine();
            if (input.isBlank() || input.isEmpty()) {
                console.printError("Login can't be empty");
            }
            else {
                return input;
            }
        }
    }

    @Override
    public String readPassword() {
        while (true) {
            console.write("Input password: ");
            String input = scanner.nextLine();
            if (input.isBlank() || input.isEmpty()) {
                console.printError("Password can't be empty");
            }
            else {
                return input;
            }
        }
    }
}
