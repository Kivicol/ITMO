package command;

import command.Utility.FileManager;
import command.Utility.IdGenerate;
import command.exceptions.InvalidDataException;
import command.exceptions.NoElementException;
import data.Route;

import java.time.ZonedDateTime;
import java.util.*;

public class Receiver {

    /**
     * Class for woking around with collection
     */
    private static LinkedList<Route> table = new LinkedList<>();
    private static ZonedDateTime date;

    public Receiver() {
        table = new LinkedList<>();
        date = ZonedDateTime.now();
        new IdGenerate();
    }
    public static ZonedDateTime getInitDate() {
        return date;
    }
    public static void add(Integer id, Route route) {
        if (table == null) {
            table = new LinkedList<>();
        }
        table.add(id, route);
        IdGenerate.add(route.getId());
    }

    public static void add(Route route) {
        if(route.validate()){
            if (table == null) {
                table = new LinkedList<>();
            }
            table.add(route);
            IdGenerate.add(route.getId());
            System.out.println("Object added successfully");
        }else{
            System.err.println("Invalid data, try again");
        }
    }
    public static void updateById(long id, Route route) throws InvalidDataException, NoSuchElementException {
        Route old = getById(id);
        table.remove(old);
        route.setId(id);
        table.add(route);
    }

    public static Route getById(long id) {
        for (Route route : table) {
            if (id == route.getId()) {
                return route;
            }
        }
        return null;
    }

    public static void remove(long id) throws NoElementException {
        if (!table.contains(getById(id))) {
            throw new NoElementException(id);
        } else {
            IdGenerate.remove((int) id);
            table.removeIf(route -> route.getId() == id);
        }
    }

    public static LinkedList<Route> getTable() {
        return table;
    }
    public static void setTable(LinkedList<Route> tb) {
        table = tb;
    }

    public static void clear() {
        table.clear();
    }

    public static void shuffle() {
        Collections.shuffle(table);
    }

    public static void sort() {
        Collections.sort(table);
    }

    public static void save() {
        FileManager fm = new FileManager();
        try {
            fm.saveToJson(table);
            System.out.println("Collection was saved successfully");
        } catch (NullPointerException e) {
            System.out.println("Something went wrong, collection wasn't saved");
        }
    }

    public static void minByName() {
        Optional<Route> minRoute = table.stream().min(Comparator.comparing(Route::getName));
        minRoute.ifPresent(System.out::println);
    }

    public static void maxByCoordinate() {
        for (int i = 0; i < table.size() - 1; i++) {
            if (table.get(i).getCoordinates().getValue() > table.get(i + 1).getCoordinates().getValue()) {
                System.out.println(table.get(i));
            }
        }
    }
    public static void countLessThanDistance(float dist) {
        int count = 0;
        for (Route route : table) {
            if (route.getDistance() < dist) {
                count++;
            }
        }
        System.out.println(count);
    }
    public static void executeScript(String filePath, Invoker invoker) {

    }
}
