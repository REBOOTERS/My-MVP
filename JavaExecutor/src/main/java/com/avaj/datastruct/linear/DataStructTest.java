package com.avaj.datastruct.linear;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created by engineer on 2017/8/13.
 */

public class DataStructTest {
    private static final int DATA_SIZE = 10 * 10 * 10 * 10 * 10 * 10 * 10;


    public static void main(String[] args) {
        ArrayListTest();
        VectorTest();
        LinkedListTest();
    }

    private static void ArrayListTest() {
        long start = System.nanoTime();

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < DATA_SIZE; i++) {
            datas.add("item-" + i);
        }

        long end = System.nanoTime();
        System.err.printf("ArrayList  Add %d element in %d nanoseconds\n", DATA_SIZE, (end - start));

        start = System.nanoTime();
        String data = datas.get(DATA_SIZE / 2);
        end = System.nanoTime();


        System.err.printf("Get data %s from ArrayList  at pos=%d in %d nanoseconds\n", data, DATA_SIZE / 2, end - start);
    }

    private static void VectorTest() {
        long start = System.nanoTime();

        List<String> datas = new Vector<>();
        for (int i = 0; i < DATA_SIZE; i++) {
            datas.add("item-" + i);
        }

        long end = System.nanoTime();
        System.err.printf("Vector     Add %d element in %d nanoseconds\n", DATA_SIZE, end - start);

        start = System.nanoTime();
        String data = datas.get(DATA_SIZE / 2);
        end = System.nanoTime();


        System.err.printf("Get data %s from Vector     at pos=%d in %d nanoseconds\n", data, DATA_SIZE / 2, end - start);
    }

    private static void LinkedListTest() {
        long start = System.nanoTime();

        List<String> datas = new LinkedList<>();
        for (int i = 0; i < DATA_SIZE; i++) {
            datas.add("item-" + i);
        }

        long end = System.nanoTime();
        System.err.printf("LinkedList Add %d element in %d nanoseconds\n", DATA_SIZE, end - start);

        start = System.nanoTime();
        String data = datas.get(DATA_SIZE / 2);
        end = System.nanoTime();


        System.err.printf("Get data %s from LinkedList at pos=%d in %d nanoseconds\n", data, DATA_SIZE / 2, end - start);

    }
}
