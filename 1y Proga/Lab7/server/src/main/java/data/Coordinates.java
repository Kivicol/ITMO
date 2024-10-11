package data;

import java.io.Serial;
import java.io.Serializable;

public class Coordinates implements Validation, Serializable {

    @Serial
    private static final long serialVersionUID = 69L;

    private int x;
    private Long y; //Поле не может быть null

    public Coordinates(int x, Long y) {
        this.x = x;
        this.y = y;
    }

    public long getValue() {
        return (this.y + this.x);
    }

    public int getX() {
        return x;
    }

    public Long getY() {
        return y;
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
