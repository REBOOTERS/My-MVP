package com.example.classReference;

/**
 * Created by rookie on 2017-04-01.
 */

public class OuterClass {
    private String name = "java";

    private class InnerClass {
        private void printMessage() {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.printMessage();
    }


}
