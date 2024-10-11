package command;

import java.io.File;
import java.io.IOException;

import command.commands.*;
import command.commands.TypeOfCommand;
import command.exceptions.InvalidDataException;
import command.utility.*;
import data.Route;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    /**
     * Class for reading user's input and executing commands
     */


    private static Sender sender;
    public static UserData userData;
    private static String currentLanguage = "Русский";
    private static LinkedList<Route> collection = new LinkedList<>();

    public static UserData getUser() {
        return userData;
    }

    public static void setUser(UserData userData) {
        Client.userData = userData;
    }
    public static void setCollection(LinkedList<Route> collection){
        Client.collection = collection;
    }
    public static LinkedList<Route> getCollection(){
        return collection;
    }

    public static String getCurrentLanguage() {
        return currentLanguage;
    }

    public static void setCurrentLanguage(String currentLanguage) {
        Client.currentLanguage = currentLanguage;
    }

    public static void setSender(Sender sender) {
        Client.sender = sender;
    }


    public void start() throws InvalidDataException {
        Scanner scanner = new Scanner(System.in);
        String[] userInput;
        while (true) {
            System.out.println("Input command: ");
            userInput = scanner.nextLine().split(" ");
            if (userInput.length < 1) {
                System.out.println("Input command: ");
                continue;
            }
            if (!userInput[0].equals("execute_script") & !userInput[0].equals("registration")) {
                TypeOfCommand commandType = CommandUtils.getCommandType(userInput[0]);
                BasicCommand command = CommandFactory.createCommand(commandType, userInput);
                if (command == null) continue;
                try {
                    command.setUser(userData);
                    Request request = new Request(command);
                    sender.send(Serializer.serialize(request));
                } catch (IOException e) {
                    System.out.println("Unable to send request to server: " + e.getMessage());
                    continue;
                }
                try {
                    Response response = sender.receive();
                    if (response.getResponse() != null) System.out.println(response.getResponse());
                } catch (IOException | ClassNotFoundException e){
                    System.out.println("Unable to get response from server: " + e.getMessage());
                }
            } else if (userInput.length == 2 & !userInput[0].equals("registration")){
                try {
                    ScriptExecutor se = new ScriptExecutor(new File(userInput[1])).readScript();
                    ArrayList<BasicCommand> commands = se.getCommandList();
                    commands.forEach(command -> {
                        try {
                            command.setUser(userData);
                            Request request = new Request(command);
                            sender.send(Serializer.serialize(request));
                        } catch (IOException e) {
                            System.out.println("Unable to send request to server: " + e.getMessage());
                        }
                        try {
                            Response response = sender.receive();
                            if (response.getResponse() != null) System.out.println(response.getResponse());
                        } catch (IOException | ClassNotFoundException e){
                            System.out.println("Unable to get response from server after script: " + e.getMessage());
                        }
                    });
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            } else if (userInput[0].equals("execute_script")){
                System.out.println("Wrong amount of attributes for command execute_script");
            } else {
                System.out.println("Unknown command: " + userInput[0]);
            }
        }
    }
    public void enter() throws InvalidDataException {
        while (userData == null) {
            TypeOfCommand commandType = CommandUtils.getCommandType("registration");
            BasicCommand command = CommandFactory.createCommand(commandType, new String[]{"registration"});
            try {
                Request request = new Request(command);
                sender.send(Serializer.serialize(request));
            } catch (IOException e) {
                System.out.println("Unable to send request to server: " + e.getMessage());
            }
            try {
                Response response = sender.receive();
                if (response.getResponse() != null)System.out.println(response.getResponse());
                if (response.getStatus().equals(ResponseStatuses.OK)){
                    userData = command.getUser();
                    break;
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Unable to get response from server: " + e.getMessage());
            } catch (NullPointerException | NoSuchElementException e){
                System.exit(0);
            }
        }
    }
}
