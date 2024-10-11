import java.util.logging.Logger;

public class Checker {



    static boolean validate(int x, double y, double r) {
        return (x >= -5 && x <= 3)&&(y >= -5 && y <= 5)&&(r >= 1 && r <= 4);
    };
    private static boolean isTriangle(int x, double y, double r) {
        return x >= 0 && y >= 0 && y <= (-x + r);
    }


    private static boolean isCircle(int x, double y, double r) {
        return x >= 0 && y <= 0 && (x * x + y * y <= Math.pow(r / 2, 2));
    }


    private static boolean isRectangle(int x, double y, double r) {
        return x >= -r && x <= 0 && y >= (double) -r / 2 && y <= 0;
    }


    public static boolean isInTheSpot(int x, double y, double r) {
        return isCircle(x, y, r) || isTriangle(x, y, r) || isRectangle(x, y, r);
    }


}