package com.example.datastruct;

/**
 * Created by Rookie on 2017/7/31.
 */

public class MainClass {
    public static void main(String[] args) {
        testDoubleLink();
    }

    private static void testDoubleLink() {
        DoubleLink<String> lists = new DoubleLink<>();

        lists.insert(0, "a");
        lists.insert(1, "b");
        lists.insert(2, "c");

        lists.insert(1, "d");
        lists.insert(4, "e");

        String value = lists.pop(4);


        println("the pop value is "+value);
        println("");

        for (int i = 0; i < lists.size(); i++) {
            println("the value at " + i + " is " + lists.getNode(i).value);
        }



    }


    private static void print(String msg) {
        System.err.print(msg);
    }

    private static void println(String msg) {
        System.err.println(msg);
    }
}
