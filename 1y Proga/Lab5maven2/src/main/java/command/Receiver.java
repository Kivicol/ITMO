package command;

import command.utility.FileManager;
import command.utility.IdGenerate;
import command.exceptions.InvalidDataException;
import command.exceptions.NoElementException;
import data.Route;

import java.time.ZonedDateTime;
import java.util.*;

public class Receiver {

    /**
     * Class for woking around with collection
     */
    public LinkedList<Route> table;
    private final ZonedDateTime date;
    private final IdGenerate idGenerate = new IdGenerate();

    public Receiver() {
        date = ZonedDateTime.now();
    }

    public ZonedDateTime getInitDate() {
        return date;
    }

    public void add(Integer id, Route route) {
        if (table == null) {
            table = new LinkedList<>();
        }
        table.add(id, route);
        idGenerate.add(route.getId());
    }

    public void add(Route route) {
        if (route.validate()) {
            if (table == null) {
                table = new LinkedList<>();
            }
            table.add(route);
            idGenerate.add(route.getId());
            System.out.println("Object added successfully");
        } else {
            System.err.println("You must have set provided variables incorrectly:\n" +
                    "--Name must not be null\n" +
                    "--Coordinates must not be null\n" +
                    "--Locations \"from\" and \"to\" must not be null\n" +
                    "--Distance must be greater than 1");
        }
    }

    public void updateById(long id, Route route) throws InvalidDataException, NoSuchElementException {
        Route old = getById(id);
        table.remove(old);
        route.setId(id);
        table.add(route);
    }

    public Route getById(long id) {
        for (Route route : table) {
            if (id == route.getId()) {
                return route;
            }
        }
        return null;
    }

    public void remove(long id) throws NoElementException {
        if (!table.contains(getById(id))) {
            throw new NoElementException(id);
        } else {
            idGenerate.remove((int) id);
            table.removeIf(route -> route.getId() == id);
        }
    }

    public LinkedList<Route> getTable() {
        return this.table;
    }

    public void setTable(LinkedList<Route> tb) {
        table = tb;
    }

    public void clear() {
        table.clear();
    }

    public void shuffle() {
        Collections.shuffle(table);
    }

    public void sort() {
        Collections.sort(table);
    }

    public void show() {
        if (!table.isEmpty()) {
            for (Route route : table) {
                System.out.println(route.toString().replace("Route{", "\033[32m[\033[0m")
                        .replace("}", "\033[32m]\033[0m").replace("\"", "")
                        .replace(", ", "\n"));
            }
        } else {
            System.out.println("Collection is empty");
        }
    }

    public void save() {
        FileManager fm = new FileManager();
        try {
            fm.saveToJson(table);
            System.out.println("Collection was saved successfully");
        } catch (NullPointerException e) {
            System.out.println("Something went wrong, collection wasn't saved");
        }
    }

    public void minByName() {
        Optional<Route> minRoute = table.stream().min(Comparator.comparing(Route::getName));
        minRoute.ifPresent(System.out::println);
    }

    public void maxByCoordinate() {
        for (int i = 0; i < table.size() - 1; i++) {
            if (table.get(i).getCoordinates().getValue() > table.get(i + 1).getCoordinates().getValue()) {
                System.out.println(table.get(i));
            }
        }
    }

    public void countLessThanDistance(float dist) {
        int count = 0;
        for (Route route : table) {
            if (route.getDistance() < dist) {
                count++;
            }
        }
        System.out.println(count);
    }
}
