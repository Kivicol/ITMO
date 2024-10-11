package command.utility;

import command.commands.BasicCommand;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.logging.Level;


public class CommandManager {
    public static ArrayDeque<BasicCommand> history = new ArrayDeque<>();
    private static DatagramSocket socket;

    public static void setSocket(DatagramSocket channel) {
        CommandManager.socket = channel;
    }

    public static void handlePacket(InetSocketAddress sender, byte[] bytes) throws Exception {
        ObjectInputStream objectInputStream2 = new ObjectInputStream(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        Request request = (Request) objectInputStream2.readObject();
        BasicCommand command = request.getCommand();
        ServerLogger.getLogger().log(Level.INFO, "Получена команда %s от %s".formatted((command).getName().toUpperCase(), sender));
        oos.writeObject(command.execute());
        byte[] output = bos.toByteArray();
        ServerLogger.getLogger().log(Level.INFO, "Отправляется результат на %s".formatted(sender));
        DatagramPacket packet = new DatagramPacket(output, output.length, sender.getAddress(), sender.getPort());
        socket.send(packet);
        oos.close();
    }
}
