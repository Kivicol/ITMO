package data;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

public class Route extends OverallElement implements Validation, Serializable {

    @Serial
    private static final long serialVersionUID = 74L;

    private long id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Location from;
    private Location to;
    private float distance;

    public Route(String name, Coordinates coordinates, ZonedDateTime creationDate, Location from, Location to, float distance){
        this.id = GenerationID();
        this.name =  name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Route(String name, Coordinates coordinates, Location from, Location to, float distance){
        this(name, coordinates, ZonedDateTime.now(), to, from, distance);
    }


    public long GenerationID(){
        long id;
        id = (long) (Math.random()*10000);
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean validate() {
        if (id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null || !coordinates.validate()) return false;
        if (creationDate == null) return false;
        if (from == null) return false;
        if (to == null) return false;
        if (distance <= 1) return false;
        return true;
    }


    @Override
    public String toString() {
        return "Route{\"id\": " + id + ", " +
                "\"name\": \"" + name + "\", " +
                "\"coordinates\": \"" + coordinates + "\", " +
                "\"creationDate\": \"" + creationDate + "\", " +
                "\"from\": " + from + "\", " +
                "\"to\": " + to + "\", " +
                "\"distance\": " + distance + "}";
    }

    @Override
    public int getId() {
        return (int) id;
    }



    @Override
    public int compareTo(OverallElement overallElement) {
        return (int)(this.id - overallElement.getId());
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public float getDistance() {
        return distance;
    }
}
