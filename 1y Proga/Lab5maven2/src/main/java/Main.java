import command.Client;

public class Main {

    /**
     * Simply the main class
     * @author Kivicol
     */
    public static void main(String[] args) {
        Client console = new Client();
        console.start(System.in, args);
    }
}