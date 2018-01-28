package com.avaj.sort;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author : rookie
 * e-mail : yingkongshi11@gmail.com
 * time   : 2017/1/16
 * desc   :
 * version: 1.0
 */

public class JavaSort {

    private static final float BASE = 1000000.0f;


    private static int[] array = new int[100];


    private static int[] generateRandomArray(){
        int[] array = new int[100];
        List<Integer> mList = new ArrayList<>();

        int count=0;
        while(count<100){
            int data= (int) (Math.random()*100);
            if(!mList.contains(data)){
                mList.add(data);
                count++;
            }
        }

        for(int j=0;j<mList.toArray().length;j++){
            array[j]= (int) mList.toArray()[j];
        }


        return array;
    }


    /**
     * 选择排序
     */
    private static void selectSort() {
        long startTime = System.nanoTime();    //获取开始时间
        for (int i = 0; i < array.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            if (k != i) {
                int temp = array[i];
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
    private static void insert() {
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

    private static void ShellSort() {
        long startTime = System.nanoTime();    //获取开始时间
        double flag = array.length;
        int temp;
        while (true) {
            flag = Math.ceil(flag / 2);
            int step = (int) flag;
            for (int i = 0; i < flag; i++) {
                for (int x = i + step; x < array.length; x += step) {
                    int j = x - step;
                    temp = array[x];
                    for (; j >= 0 && temp < array[j]; j = j - step) {
                        array[j + step] = array[j];
                    }
                    array[j + step] = temp;
                }
            }
            if (step == 1) {
                break;
            }
        }
        long endTime = System.nanoTime();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) / BASE + "ms");    //输出程序运行时间
    }

    private static int partition(int[] array, int low, int high) {
        int temp = array[low];
        int i = low;
        int j = high;
        while (i != j) {
            while (temp < array[j] && i < j) {
                j--;
            }

            if (i < j) {
                array[i] = array[j];
                i++;

                while (temp > array[i] && i < j) {
                    i++;
                }

                if (i < j) {
                    array[j] = array[i];
                    j--;
                }


            }


        }

        array[i] = temp;
        return i;
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int i = partition(array, low, high);
            quickSort(array, low, i - 1);
            quickSort(array, i+1, high);
        }
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
        int lastChange = array.length - 1;
        while (lastChange > 0) {
            int n = lastChange;
            lastChange = 0;
            for (int i = 0; i < n; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    lastChange = i;
                }
                System.out.format("when i=%d ,flag=%d     ", i, lastChange);
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

        array=generateRandomArray();
        System.out.println("The Original data is :\n");
        System.out.println(Arrays.toString(array));
//        insert();
//        ShellSort();
//        BubbleSort2();
//        selectSort();

//        partation(array, 0, array.length - 1);

        quickSort(array,0,array.length-1);

        System.out.print("\nAfter Sort data is : \n");
        System.out.println(Arrays.toString(array));

        int pos = BinarySearch(array, 89);

        if (pos != -1) {
            System.out.printf("\nfind %d at index=%d", 89, pos);
        } else {
            System.err.println("\nerror !");
        }


    }
}
