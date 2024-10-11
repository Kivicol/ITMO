package data;

public class Coordinates implements Validation {
    private int x;
    private Long y; //Поле не может быть null

    public Coordinates(int x, Long y) {
        this.x = x;
        this.y = y;
    }

    public long getValue() {
        return (this.y + this.x);
    }


    @Override
    public String toString() {
        return x + ";" + y;
    }

    @Override
    public boolean validate() {
        if (x >= 222 || y <= -471) {
            return false;
        } else {
            return true;
        }

    }

}
