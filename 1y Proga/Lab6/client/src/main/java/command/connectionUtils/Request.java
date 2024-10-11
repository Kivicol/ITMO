package command.connectionUtils;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;



public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 21L;
    private static DatagramSocket socket;

    private final InetAddress address;

    private final int port;

    private final static int socketTimeout = 4000;


    public Request(InetAddress address, int port) throws SocketException {
        socket = new DatagramSocket();
        socket.setSoTimeout(socketTimeout);
        this.address = address;
        this.port = port;
    }


    public int getPort(){
        return socket.getLocalPort();
    }


    public void setBufferSize(int size) throws SocketException {
        socket.setReceiveBufferSize(size);
        socket.setSendBufferSize(size);
    }


    public void send(byte[] bytes) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, this.address, port);
        socket.send(datagramPacket);
        System.out.print("");
    }


    public String receive() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        bos.write(packet.getData(), 0, packet.getLength());
        String data = bos.toString();
        bos.close();
        return data;
    }
}
