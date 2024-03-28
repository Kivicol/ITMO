package src.command;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import src.command.Invoker;
import src.command.Receiver;
import src.command.Utility.FileManager;
import src.command.exceptions.NoElementException;

import java.util.Scanner;

import static src.command.Utility.FileManager.filePath;

public class Client {
    /**
     * Class for reading user's input and executing commands
     */
    public void start(InputStream input, String[] args){
        Scanner scanner = new Scanner(input);
        Invoker invoker = new Invoker();
        new Receiver();

        System.out.println(System.getenv("FILE_PATH"));
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
