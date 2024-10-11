package data;

public class Location implements Validation{
    private Integer x; //Поле не может быть null
    private long y;
    private String name; //Поле не может быть null

    public Location(Integer x, long y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location \"" + name + "\", at (" + x + ";" + y + ")";
    }

    @Override
    public boolean validate() {
        if (x == null || name == null) return false;
        return true;
    }
}
