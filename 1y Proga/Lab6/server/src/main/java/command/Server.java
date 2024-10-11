package command;

import java.io.*;

import command.utility.FileManager;
import command.utility.ServerLogger;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static command.utility.CommandManager.handlePacket;
import static command.utility.CommandManager.setChannel;

public class Server {
    /**
     * Class for reading user's input and executing commands
     */

    private static final int bufferSize = 1025;
    private final int port;
    private DatagramChannel datagramChannel;
    private Selector selector;
    public static Receiver receiver;
    private final FileManager fm = new FileManager();

    public Server(Receiver receiver, int port) {
        Server.receiver = receiver;
        this.port = port;
    }

    BufferedReader scanner = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));

    public void start() {
        try {
            Thread consoleThread = new Thread(() -> {
                while (true) {
                    try {
                        if (scanner.ready()) {
                            String line = scanner.readLine().toLowerCase();
                            if (line.equals("save")) {
                                fm.saveToJson(receiver.getTable());
                            }
                            if (line.equals("exit")) {
                                fm.saveToJson(receiver.getTable());
                                ServerLogger.getLogger().info("Exiting...");
                                System.exit(0);
                            }
                        }
                    } catch (Exception ignored) {
                    }
                }
            });
            datagramChannel = DatagramChannel.open();
            datagramChannel.socket().bind(new InetSocketAddress(port));
            datagramChannel.configureBlocking(false);
            selector = Selector.open();
            datagramChannel.register(selector, SelectionKey.OP_READ);
            ServerLogger.getLogger().info("Server started on port: " + port);
            Map<InetSocketAddress, ByteArrayOutputStream> byteStreams = new HashMap<>();
            Thread clientThread = new Thread(() -> {
                while (true) {
                    try {
                        selector.select();
                        Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                        while (keys.hasNext()) {
                            SelectionKey key = keys.next();
                            keys.remove();
                            if (!key.isValid()) {
                                continue;
                            }
                            if (key.isReadable()) {
                                DatagramChannel keyChannel = (DatagramChannel) key.channel();
                                keyChannel.configureBlocking(false);
                                setChannel(keyChannel);
                                ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
                                InetSocketAddress inetSocketAddress = (InetSocketAddress) keyChannel.receive(buffer);
                                ByteArrayOutputStream byteStream = byteStreams.get(inetSocketAddress);
                                if (byteStream == null) {
                                    byteStream = new ByteArrayOutputStream();
                                    byteStreams.put(inetSocketAddress, byteStream);
                                }
                                boolean hasNext = (buffer.array()[buffer.limit() - 1] == 1);
                                byteStream.write(buffer.array(), 0, buffer.limit() - 1);
                                if (!hasNext) {
                                    try {
                                        handlePacket(inetSocketAddress, byteStream.toByteArray());
                                    } catch (Exception e) {
                                        keyChannel.send(ByteBuffer.wrap("ERROR: Something went wrong...".getBytes()), inetSocketAddress);
                                        ServerLogger.getLogger().warning(e.toString());
                                    }
                                    byteStreams.remove(inetSocketAddress);
                                }
                                buffer.clear();
                            }
                        }
                    } catch (Exception e) {
                        ServerLogger.getLogger().warning("Error: " + e.getMessage());
                    }
                }
            });
            consoleThread.start();
            clientThread.start();
            consoleThread.join();
            clientThread.join();
        } catch (IOException | InterruptedException e) {
            ServerLogger.getLogger().warning("Error: " + e.getMessage());
        } finally {
            try {
                selector.close();
                datagramChannel.close();
            } catch (IOException e) {
                ServerLogger.getLogger().warning("Wasn't able to close channel or selector: " + e.getMessage());
            }
        }
    }
}
