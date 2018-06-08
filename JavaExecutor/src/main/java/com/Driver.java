package com;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Driver {
    public static void main(String[] args) {
        float result = getSizeInMB(0);
        System.out.println("the result is " + result);
    }


    public static float getSizeInMB(long sizeInBytes) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        df.applyPattern("0.0");
        String result = df.format((float) sizeInBytes / 1024 / 1024);
        result = result.replaceAll(",","."); // in some case , 0.0 will be 0,0
        return Float.valueOf(result);
    }
}
