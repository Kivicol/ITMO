package command.utility;

import java.util.Scanner;

public class InputByHand implements Reader{

    /**
     * Class for reading user's input
     */

    public Scanner customScanner = new Scanner(System.in);

    @Override
    public String nextLine() {
        return customScanner.nextLine();
    }
}
