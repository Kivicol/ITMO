package data;

public class Position{

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
