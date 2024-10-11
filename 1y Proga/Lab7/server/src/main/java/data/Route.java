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
    private String userlogin;

    public Route(String name, Coordinates coordinates, ZonedDateTime creationDate, Location from, Location to, float distance, String userlogin) {
        this.id = GenerationID();
        this.name =  name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.userlogin = userlogin;
    }

    public Route(long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Location from, Location to, float distance, String userlogin) {
        this.id = id;
        this.name =  name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.userlogin = userlogin;
    }



    public long GenerationID(){
        long id;
        id = (long) (Math.random()*10000);
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public void setLocationFrom(Location from) {
        this.from = from;
    }
    public void setLocationTo(Location to) {
        this.to = to;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }
    public Object getCreationDate() {
        return this.creationDate;
    }
    public Location getLocationFrom() {
        return this.from;
    }
    public Location getLocationTo() {
        return this.to;
    }

    public String getName() {
        return name;
    }

    public int getCoordinateX() {
        return coordinates.getX();
    }

    public Long getCoordinateY() {
        return coordinates.getY();
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }

    public String getUserlogin() {
        return userlogin;
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
