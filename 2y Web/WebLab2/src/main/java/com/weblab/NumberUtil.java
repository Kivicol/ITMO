package com.weblab;

public class NumberUtil {
    public static double truncate(double value, int decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        return Math.floor(value * scale) / scale;
    }
}