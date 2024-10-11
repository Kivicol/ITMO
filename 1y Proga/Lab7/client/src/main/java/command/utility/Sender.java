package command.utility;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class Sender {
    private static DatagramSocket socket;
    private final InetAddress address;
    private final int port;
    private final static int timeout = 4000;

    public Sender(InetAddress address, int port) throws SocketException {
        this.address = address;
        this.port = port;
        socket = new DatagramSocket();
        socket.setSoTimeout(timeout);
    }

    public int getPort() {
        return socket.getLocalPort();
    }

    public void setBufferSize(int size) throws SocketException {
        socket.setReceiveBufferSize(size);
        socket.setSendBufferSize(size);
    }

    public void send(byte[] bytes) throws IOException {
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, this.address, port);
        socket.send(packet);
        System.out.println("");
    }

    public Response receive() throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[1024 * 1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
        return (Response)objectInputStream.readObject();
    }


}
