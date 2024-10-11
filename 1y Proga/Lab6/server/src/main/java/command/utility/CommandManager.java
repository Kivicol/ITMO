package command.utility;

import command.Receiver;
import command.commands.BasicCommand;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayDeque;
import java.util.logging.Level;


public class CommandManager {
    private static DatagramChannel channel;
    public static ArrayDeque<BasicCommand> history = new ArrayDeque<>();
    public static void setChannel(DatagramChannel channel) {
        CommandManager.channel = channel;
    }

    public static void handlePacket(InetSocketAddress sender, byte[] bytes) throws Exception {
        ObjectInputStream objectInputStream2 = new ObjectInputStream(new ByteArrayInputStream(bytes));
        BasicCommand command = (BasicCommand) objectInputStream2.readObject();
        if (!(history == null) && history.size() == 10) {
            history.pop();
            history.addLast(command);
        } else {
            if (history != null) {
                history.addFirst(command);
            }
        }
        ServerLogger.getLogger().log(Level.INFO, "Got command %s from %s".formatted(command.getName().toLowerCase(), sender));
        byte[] output = command.execute().getResponse().getBytes();
        ServerLogger.getLogger().log(Level.INFO, "Sending response %s".formatted(sender));
        channel.send(ByteBuffer.wrap(output), sender);
    }
}
