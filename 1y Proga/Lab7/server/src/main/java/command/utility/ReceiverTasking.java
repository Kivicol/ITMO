package command.utility;

import command.Server;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReceiverTasking implements Runnable{
    private DatagramSocket socket;
    private Interpreter interpreter;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    public ReceiverTasking(DatagramSocket socket, Interpreter interpreter){
        this.socket = socket;
        this.interpreter = interpreter;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            lock.readLock().lock();
            try{
                byte[] bytes = new byte[Server.bufferSize];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                socket.receive(packet);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Request request = (Request) objectInputStream.readObject();
                ServerLogger.getLogger().info("Got message from " + packet.getAddress() + " : " + packet.getPort() + " - " + request.getCommand().getName());
                interpreter.putRequest(request, packet);
                byteArrayInputStream.close();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e){
                ServerLogger.getLogger().warning("Unable to get message");
            } finally {
                lock.readLock().unlock();
            }
        }
    }
}
