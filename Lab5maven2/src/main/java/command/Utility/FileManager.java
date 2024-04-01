package command.Utility;


import java.io.*;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import data.Route;

public class FileManager {

    /**
     * Class used for saving and loading objects of class "Route" to/from JSON data file
     */
    public static String filePath = System.getenv("FILE_PATH") + "collection_data.json";

    public void saveToJson(LinkedList<Route> table) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new TimeAdapter())
                .setPrettyPrinting().create().newBuilder();
        Gson gson = gsonBuilder.create();
        String data = gson.toJson(table);
        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            bufferedOutputStream.write(data.getBytes());
        }catch (IOException e){
            System.out.println("Smth went wrong, data was not saved");
        }
    }


    public static LinkedList<Route> loadFromJson() throws JsonSyntaxException, JsonIOException{
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
                IdGenerate.add(IdGenerate.generateId());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (JsonSyntaxException | JsonIOException e) {
            System.out.println("Something wrong with the file or it is empty.");
        }
        return table;
    }
}