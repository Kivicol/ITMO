import command.Client;
import command.exceptions.InvalidDataException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;

public class Main {

    /**
     * Simply the main class
     * @author Kivicol
     */


    private static final int serverPort = 1658;


    public static void main(String[] args) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), serverPort);
            try{
                client.enter();
                System.out.println("Welcome, to see available commands type \"help\"");
                client.start();
            } catch (InvalidDataException e) {
                System.err.println();
            } catch (NoSuchElementException e) {
                System.out.println("Exiting...");
                System.exit(0);
            }
        }catch (UnknownHostException e){
            System.err.println("Unknown host");
        }

    }

}