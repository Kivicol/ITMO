import command.CollectionManager;
import command.Server;
import command.utility.DBParser;
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
        DBParser dbParser = new DBParser();
        LinkedList<Route> collection = dbParser.load();
        try {
            CollectionManager collectionManager = new CollectionManager();
            collectionManager.setTable(collection);
            Server server = new Server(serverPort);
            server.start();
        } catch (Exception e) {
            System.err.println("Something went wrong: " + e.getMessage());
        }

    }
}