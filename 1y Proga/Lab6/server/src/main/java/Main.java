import com.google.gson.JsonSyntaxException;
import com.google.gson.JsonIOException;
import command.Receiver;
import command.Server;
import command.utility.FileManager;
import data.Route;

import java.util.LinkedList;

public class Main {

    /**
     * Simply the main class
     *
     * @author Kivicol
     */

    private final static Integer serverPort = 1658;

    public static void main(String[] args) {

        Receiver receiver = new Receiver();
        FileManager fm = new FileManager();
        try{
            receiver.setTable(fm.loadFromJson());
            receiver.sort();
            receiver.save();
        } catch (JsonSyntaxException | JsonIOException e) {
            System.out.println("Collection wasn't found");
            System.out.println("Creating new collection...");
            receiver.setTable(new LinkedList<Route>());
        }

        Server server = new Server(receiver, serverPort);
        server.start();
    }
}