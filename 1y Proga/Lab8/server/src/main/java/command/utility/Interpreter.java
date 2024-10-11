package command.utility;

import command.commands.BasicCommand;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;

public class Interpreter extends Thread{
    private Request request;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private Sender sender;
    private boolean hasNextPacket = false;
    public Interpreter(Sender sender, DatagramSocket socket){
        this.sender = sender;
        this.socket = socket;
    }
    public void putRequest(Request request, DatagramPacket packet){
        this.request = request;
        this.packet = packet;
        hasNextPacket = true;
        this.start();
    }
    @Override
    public void run(){
        while (!isInterrupted()){
            if (hasNextPacket){
                interpret(request, packet);
            }
        }
    }
    public void interpret(Request request, DatagramPacket packet){
        hasNextPacket = false;
        BasicCommand command = request.getCommand();
        ServerLogger.getLogger().log(Level.INFO, "Got command %s from %s".formatted((command).getName().toUpperCase(), packet.getAddress()));
        Response response = command.execute();
        sender.putResponse(response, packet);
        this.interrupt();
    }
}