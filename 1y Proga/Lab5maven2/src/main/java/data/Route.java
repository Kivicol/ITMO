package data;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Route extends OverallElement implements Validation, Serializable {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле может быть null
    private float distance; //Значение поля должно быть больше 1

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
