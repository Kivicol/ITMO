package command;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import command.commands.*;
import command.commands.TypeOfCommand;
import command.connectionUtils.CommandFactory;
import command.connectionUtils.CommandSerializer;
import command.connectionUtils.CommandUtils;
import command.exceptions.InvalidDataException;
import command.connectionUtils.Request;
import command.utility.ScriptExecutor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    /**
     * Class for reading user's input and executing commands
     */

    private final Request request;

    public Client(InetAddress address, int port) {
        try {
            request = new Request(address, port);
            request.setBufferSize(8192 * 8192);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void start(InputStream input, String[] args) {
        Scanner scanner = new Scanner(input);
        String[] userInput;

        System.out.println("Welcome to the program!\nTo see available commands, write \"help\"");
        System.out.print("\033[32m> \033[0m");
        while (scanner.hasNextLine()) {
            userInput = scanner.nextLine().split(" ");
            if (!userInput[0].equals("execute_script")) {
                try {
                    TypeOfCommand commandType = CommandUtils.getCommandType(userInput[0]);
                    BasicCommand command = CommandFactory.createCommand(commandType, userInput);
                    if (command == null) continue;
                    try {
                        System.out.println(CommandUtils.getCommandType(userInput[0]));
                        request.send(CommandSerializer.serialize(command));
                    } catch (IOException e) {
                        System.out.println("Unable to send command: " + e.getMessage());
                        continue;
                    }
                    try {
                        String response = request.receive();
                        if (!response.isEmpty()) System.out.println(response);
                    } catch (IOException e) {
                        System.out.println("Unable to receive response: " + e.getMessage());
                    }
                    System.out.print("\033[32m> \033[0m");
                } catch (NullPointerException e) {
                    System.out.println("No command provided, try again");
                    System.out.print("\033[32m> \033[0m");
                } catch (InvalidDataException e) {
                    System.out.println("Invalid data provided, try again");
                    System.out.print("\033[32m> \033[0m");
                }
            } else if (userInput.length == 2) {
                try {
                    ScriptExecutor se = new ScriptExecutor(new File(userInput[1])).readScript();
                    ArrayList<BasicCommand> commands = se.getCommandList();
                    commands.forEach(command -> {
                        try {
                            request.send(CommandSerializer.serialize(command));
                        } catch (IOException e) {
                            System.out.println("Unable to send command: " + e.getMessage());
                        }
                        try{
                            String response = request.receive();
                            if (!response.isEmpty()) System.out.println(response);
                        } catch (IOException e) {
                            System.out.println("Unable to send command: " + e.getMessage());
                        }
                    });
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Wrong amount of arguments for command execute_script");
            }
        }
    }
}
