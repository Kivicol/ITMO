package data;

import java.io.Serial;
import java.io.Serializable;

public class Position implements Serializable {

    @Serial
    private static final long serialVersionUID = 71L;

    private int x;
    private float y;
    private float z;

    public Position(int x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return x + ";" + y + ";" + z;
    }

}
