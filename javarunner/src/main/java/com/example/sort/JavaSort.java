package com.example.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by rookie on 2017/1/16.
 */

public class JavaSort {

    private static final float BASE = 1000000.0f;
    private static int[] array = {19, 89, 588, 193, 1, 11, 543, 111, 7, 10};

    private static void QuickInsertSort() {
        long startTime = System.nanoTime();    //获取开始时间
        int temp;
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            temp = array[i];
            for (; j >= 0 && temp < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;

            System.out.print("when i=" + i + " array is ");
            System.out.println(Arrays.toString(array));
        }
        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime)/BASE + "ms");    //输出程序运行时间
    }

    private static void BubbleSort() {
        long startTime = System.nanoTime();    //获取开始时间
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                System.out.format("when i=%d ,j=%d     ", i, j);
                System.out.println(Arrays.toString(array));
            }

        }
        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) / BASE + "ms");    //输出程序运行时间
    }

    private static int BinarySearch(int[] array, int element) {
        int pos = -1;
        int left, mid, right;
        left = 0;
        right = array.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;

            if (element < array[mid]) {
                right = mid - 1;
            } else if (element > array[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return pos;
    }


    public static void main(String[] args) {


        String[] strs = {"1111", "b", "c"};
        List<String> lists = Arrays.asList(strs);
        System.out.println("1111's length is "+strs[0].length());
        System.out.println("b's length is "+strs[1].length());
        System.out.println("array length is " + strs.length);
        System.out.println("list size is "+lists.size());



        System.out.println("array length is " + array.length);
        List<Integer> mList = IntStream.of(array).boxed().collect(Collectors.toList());

        System.out.println("list size is "+mList.size());

        System.out.println("The Original data is :");
        System.out.println(Arrays.toString(array));
        QuickInsertSort();
        BubbleSort();
        System.out.print("\nAfter Sort data is : ");
        System.out.println(Arrays.toString(array));

        int pos = BinarySearch(array, 1111);
        System.out.println("\nSearch result position=" + pos);
    }
}
