package com.example.sort;

/**
 * Created by rookie on 2017/1/16.
 */

public class JavaSort {
    public static int[] array = {19, 89, 588, 193, 1, 10, 543, 111, 7, 10};

    public static void QuickInsertSort() {
        int temp = 0;
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            temp = array[i];
            for (; j >= 0 && temp < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        System.out.println("The Original data is :");
        for (int m = 0; m < array.length; m++) {
            System.out.print(array[m] + " ");
        }
        System.out.println("\nAfter sortrd data is :");
        QuickInsertSort();
        for (int m = 0; m < array.length; m++) {
            System.out.print(array[m] + " ");
        }
    }
}
