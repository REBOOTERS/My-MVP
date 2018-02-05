package com.avaj.datastruct.linear;

import java.util.Arrays;

/**
 * Created by Rookie on 2017/7/31.
 */

public class MainClass {
   Arrays  mArrays;

    public static void main(String[] args) {
//        testDoubleLink();
        testArrayStack();
    }

    private static void testArrayStack() {
        GeneralArrayStack<String> astack = new GeneralArrayStack<>(String.class);

        // 将10, 20, 30 依次推入栈中
        astack.push("10");
        astack.push("20");
        astack.push("30");

        astack.PrintArrayStack();

        // 将“栈顶元素”赋值给tmp，并删除“栈顶元素”
        String tmp = astack.pop();
        System.out.println("tmp=" + tmp);

        // 只将“栈顶”赋值给tmp，不删除该元素.
        tmp = astack.peek();
        System.out.println("tmp=" + tmp);

        astack.push("40");
        astack.PrintArrayStack();    // 打印栈
    }


    private static void testDoubleLink() {
        DoubleLoopLink<String> lists = new DoubleLoopLink<>();

        lists.insert(0, "a");
        lists.insert(1, "b");
        lists.insert(2, "c");

        lists.insert(1, "d");
        lists.insert(4, "e");

        String value = lists.pop(4);


        println("the pop value is " + value);
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
