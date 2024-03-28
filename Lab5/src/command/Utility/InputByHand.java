package src.command.Utility;

import java.util.Scanner;

public class InputByHand implements Reader{

    /**
     * Class for reading user's input
     */
    private static final Scanner userScanner = CustomScanner.getCustomScanner();

    @Override
    public String nextline() {
        return userScanner.nextLine();
    }
}
