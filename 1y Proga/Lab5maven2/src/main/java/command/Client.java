package command;

import java.io.InputStream;

import command.utility.FileManager;

import java.util.Scanner;

public class Client {
    /**
     * Class for reading user's input and executing commands
     */
    public void start(InputStream input, String[] args){
        Scanner scanner = new Scanner(input);
        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker(receiver);
        FileManager fm = new FileManager();

        System.out.println("\033[33mYour current FILE_PATH environmental variable is: \"" + System.getenv("FILE_PATH") + "\", the collection is stored in this directory \033[0m");
        receiver.setTable(fm.loadFromJson());
        receiver.sort();
        System.out.println("Welcome to the program!\nTo see available commands, write \"help\"");
        System.out.print("\033[32m> \033[0m");
        while (scanner.hasNextLine()) {
            try{
            String command = scanner.nextLine().toLowerCase();
            invoker.startExecuting(command);
            System.out.print("\033[32m> \033[0m");
            } catch (NullPointerException e) {
                System.out.println("No command provided, try again");
                System.out.print("\033[32m> \033[0m");
            }
        }
    }
}
