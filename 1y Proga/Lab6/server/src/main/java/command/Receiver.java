package command;

import command.utility.FileManager;
import command.utility.IdGenerate;
import command.exceptions.InvalidDataException;
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

    public String updateById(Route route) throws InvalidDataException, NoSuchElementException {
        boolean flag = table.stream()
                .filter(rt -> rt.getId() == route.getId())
                .findFirst()
                .map(rt -> {
                    rt.setName(route.getName());
                    rt.setCoordinates(route.getCoordinates());
                    rt.setLocationFrom(route.getLocationFrom());
                    rt.setLocationTo(route.getLocationTo());
                    rt.setDistance(route.getDistance());
                    return rt;
                })
                .isPresent();
        return !flag ? "No object with such id." : "Object updated.";
    }

    public Route getById(long id) {
        for (Route route : table) {
            if (id == route.getId()) {
                return route;
            }
        }
        return null;
    }

    public String remove(long id) {
        try {
            if (!(getById(id) == null)) {
                idGenerate.remove((int) id);
                table.removeIf(route -> route.getId() == id);
                return "Object removed successfully";
            }
            else {
                throw new NoSuchElementException("No such element in collection");
            }
        } catch (NoSuchElementException e) {
            return "No such element in collection";
        }
    }

    public LinkedList<Route> getTable() {
        return table;
    }

    public void setTable(LinkedList<Route> tb) {
        table = tb;
    }

    public void clear() {
        table.clear();
    }

    public String shuffle() {
        Collections.shuffle(table);
        return "Collection was shuffled successfully";
    }

    public String sort() {
        Collections.sort(table);
        return "Collection was sorted successfully";
    }

    public String show() {
        StringBuilder res = new StringBuilder();
        if (!table.isEmpty()) {
            table.forEach(route -> res.append(route.toString()).append("\n"));
            return res.toString();
        } else {
            return "Collection is empty";
        }
    }
    public String getInfo() {
        return "Data -- " + Route.class + "\n" + "Count of elements -- " + getTable().size() + "\n" + "Initialization date -- " + getInitDate();
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

    public String minByName() {
        Optional<Route> minRoute = table.stream().min(Comparator.comparing(Route::getName));
        return minRoute.toString();
    }

    public String maxByCoordinate() {
        String result = null;
        for (int i = 0; i < table.size() - 1; i++) {
            if (table.get(i).getCoordinates().getValue() > table.get(i + 1).getCoordinates().getValue()) {
                result = String.valueOf(table.get(i));
            }
        }
        return result;
    }

    public float countLessThanDistance(float dist) {
        int count = 0;
        for (Route route : table) {
            if (route.getDistance() < dist) {
                count++;
            }
        }
        return count;
    }
}
