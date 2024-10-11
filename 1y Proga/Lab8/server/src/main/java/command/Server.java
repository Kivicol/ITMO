package command;

import java.io.*;

import command.utility.*;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.SocketException;

public class Server {
    /**
     * Class for reading user's input and executing commands
     */

    public static final int bufferSize = 1025;
    private final int port;
    private DatagramSocket socket;
    private InetAddress address = InetAddress.getByName("localhost");
    public static CollectionManager collectionManager = new CollectionManager();



    public Server(int port) throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket(port);
        this.port = port;
    }

    BufferedReader scanner = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));

    public void start() throws InterruptedException {
        DBParser parser = new DBParser();
        try {
            Thread consoleThread = new Thread(() -> {
                while (true) {
                    try {
                        if (scanner.ready()) {
                            String command = scanner.readLine();
                            if (command.equals("save")) {
                                parser.save();
                            } else if (command.equals("exit")) {
                                parser.save();
                                ServerLogger.getLogger().info("Exiting server program...");
                                System.exit(0);
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }
            });
            ServerLogger.getLogger().info("Server started on port " + port);
            CommandManager.setSocket(socket);
            Thread clientThread = new Thread(() -> {
                while (true) {
                    try {
                        Sender sender = new Sender(socket);
                        Interpreter interpreter = new Interpreter(sender, socket);
                        Receiver receiver = new Receiver(socket, interpreter);
                        receiver.setDaemon(true);
                        receiver.start();
                    } catch (Exception e) {
                        ServerLogger.getLogger().warning("Ошибка: " + e.getMessage());
                    }
                }
            });
            consoleThread.start();
            clientThread.start();
            consoleThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            ServerLogger.getLogger().warning("Ошибка: " + e.getMessage());
        } finally {
            socket.close();
        }
    }
}
