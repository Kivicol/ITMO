package com.weblab;

import java.util.Arrays;
import java.util.List;

public class AreaChecker {

    public static boolean isInArea(double x, double y, double r) {
        x /= r;
        y /= r;
        return (x >= -5 && x <= 3) && (y >= -5 && y <= 5) && (r >= 1 && r <= 4) && (inRect(x, y) || inTriangle(x, y) || inCircle(x, y));
    }
    private static boolean inRect(double x, double y) {
        return x >= -0.5 && x <= 0 && y <= 1 && y >= 0;
    }

    private static boolean inTriangle(double x, double y) {
        return (y >= 0) && (x >= 0) && (2 * y <= -x + 1);
    }
    private static boolean inCircle(double x, double y) {
        return (x <= 0) && (y <= 0) && (x * x + y * y <= 0.25);
    }
}