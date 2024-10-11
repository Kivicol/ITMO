package command.utility;


import java.io.*;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import data.Route;
import org.ietf.jgss.GSSException;

public class FileManager {

    /**
     * Class used for saving and loading objects of class "Route" to/from JSON data file
     */
    public String filePath = System.getenv("FILE_PATH");
    private IdGenerate idGenerate = new IdGenerate();

    public void saveToJson(LinkedList<Route> table) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new TimeAdapter())
                .setPrettyPrinting().create().newBuilder();
        Gson gson = gsonBuilder.create();
        String data = gson.toJson(table);
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            bufferedOutputStream.write(data.getBytes());
        } catch (IOException e) {
            System.err.println("Smth went wrong while saving collection to the file");
        } catch (SecurityException e) {
            System.err.println("Not enough permissions to access file");
        }
    }


    public LinkedList<Route> loadFromJson() throws JsonSyntaxException, JsonIOException{
        LinkedList<Route> table = new LinkedList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File not found");
                return table;
            }
            Scanner scan = new Scanner(file);
            String data = scan.useDelimiter("\\A").next();
            scan.close();
            Type itemsArrayType = new TypeToken<ArrayList<Route>>() {}.getType();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ZonedDateTime.class, new TimeAdapter())
                    .create();
            ArrayList<Route> buffer = gson.fromJson(data, itemsArrayType);
            for (Route route : buffer){
                table.add(route);
                idGenerate.add(idGenerate.generateId());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, try checking if the path is correct");
        } catch (JsonSyntaxException | JsonIOException e) {
            System.out.println("Something wrong with the file or it is empty");
        } catch (SecurityException e) {
            System.err.println("Not enough permissions to access file");
        } catch (NullPointerException e) {
            System.err.println("Null path for the file, exit the program and change environmental variable");
        }
        return table;
    }
}