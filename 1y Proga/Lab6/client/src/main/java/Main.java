import command.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    /**
     * Simply the main class
     * @author Kivicol
     */


    private static final int serverPort = 1658;


    public static void main(String[] args) {
        try{
            Client client = new Client(InetAddress.getByName("helios.cs.ifmo.ru"), serverPort);
            client.start(System.in, args);
        }catch (UnknownHostException e){
            System.out.println("Unknown host");
        }
    }

}