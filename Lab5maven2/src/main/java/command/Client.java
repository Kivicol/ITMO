package command;

import java.io.InputStream;

import command.Utility.FileManager;

import java.util.Scanner;

public class Client {
    /**
     * Class for reading user's input and executing commands
     */
    public void start(InputStream input, String[] args){
        Scanner scanner = new Scanner(input);
        Invoker invoker = new Invoker();
        new Receiver();

        System.out.println("\033[33mYour current FILE_PATH environmental variable is: \"" + System.getenv("FILE_PATH") + "\", all files (collection and scripts) are stored in this directory \033[0m");
        Receiver.setTable(FileManager.loadFromJson());
        System.out.println("Welcome to the program!\nTo see available commands, write \"help\"");
        while (scanner.hasNextLine()) {
            try{
            String command = scanner.nextLine();
            invoker.startExecuting(command);
            } catch (NullPointerException e) {
                System.out.println("No command provided, try again");
            }
        }
    }
}
