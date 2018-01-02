package com.avaj.sort;


import java.util.Arrays;

/**
 * author : rookie
 * e-mail : yingkongshi11@gmail.com
 * time   : 2017/1/16
 * desc   :
 * version: 1.0
 */

public class JavaSort {

    private static final float BASE = 1000000.0f;

    private static final int SIZE = 100 ;

    private static int[] array = new int[SIZE];


    /**
     * 选择排序
     */
    private static void selectSort() {
        long startTime = System.nanoTime();    //获取开始时间
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            if (k != i) {
                temp = array[i];
                array[i] = array[k];
                array[k] = temp;
            }
        }

        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) / BASE + "ms");    //输出程序运行时间
    }


    /**
     * 快速直接插入排序
     */
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

//            System.out.print("when i=" + i + " array is ");
//            System.out.println(Arrays.toString(array));
        }
        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) / BASE + "ms");    //输出程序运行时间
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

    private static void BubbleSort2() {
        long startTime = System.nanoTime();    //获取开始时间
        int temp;
        int flag = array.length - 1;
        while (flag > 0) {
            int n = flag - 1;
            flag = 0;
            for (int i = 0; i <= n; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    flag = i;
                }
                System.out.format("when i=%d ,flag=%d     ", i, flag);
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


//        String[] strs = {"1111", "b", "c"};
//        List<String> lists = Arrays.asList(strs);
//        System.out.println("1111's length is " + strs[0].length());
//        System.out.println("b's length is " + strs[1].length());
//        System.out.println("array length is " + strs.length);
//        System.out.println("list size is " + lists.size());
//
//
//        System.out.println("array length is " + array.length);
//        List<Integer> mList = IntStream.of(array).boxed().collect(Collectors.toList());
//
//        System.out.println("list size is " + mList.size());


        for (int i = 0; i < SIZE; i++) {
            array[i] = (int) (Math.random() * SIZE);
        }


        System.out.println("The Original data is :");
        System.out.println(Arrays.toString(array));
        QuickInsertSort();
//        BubbleSort2();
//        selectSort();
        System.out.print("\nAfter Sort data is : ");
        System.out.println(Arrays.toString(array));

        int pos = BinarySearch(array, 89);

        if (pos != -1) {
            System.out.println("\nSearch result position=" + pos);
        } else {
            System.err.println("\nerror !");
        }


    }
}
