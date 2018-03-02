package com.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/2/28
 * desc   :
 * version: 1.0
 */
public class DataDriver {

    private static List<Integer> mIntegers = new ArrayList<>();

    private static int[] data = new int[100];

    public static void main(String[] args) {
        genRandomData();
        System.err.println("the origin data: \n" + Arrays.toString(data));
//        selectSort();
//        insertSort();
//        bubbleSort();
        quickSort(data, 0, data.length - 1);
        System.err.println("after sort data: \n" + Arrays.toString(data));

        int pos = binarySearch(44);
        System.err.println("find 44 at pos=" + pos);
    }



    private static int binarySearch(int target) {
        int low = 0;
        int high = data.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (target == data[mid]) {
                return mid;
            } else if (target < data[mid]) {
                high = mid - 1;
            } else if (target > data[mid]) {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int index = partation(data, low, high);
            quickSort(data, low, index - 1);
            quickSort(data, index + 1, high);
        }
    }

    private static int partation(int[] data, int low, int high) {
        int temp = data[low];
        int i = low;
        int j = high;
        while (i != j) {
            while (temp < data[j] && i < j) {
                j--;
            }

            if (i < j) {
                data[i] = data[j];
                i++;
            }

            while (temp > data[i] && i < j) {
                i++;
            }

            if (i < j) {
                data[j] = data[i];
                j--;
            }
        }
        data[i] = temp;
        return i;
    }


    private static void bubbleSort() {
        int lastChange = data.length - 1;
        while (lastChange != 0) {
            int n = lastChange;
            lastChange = 0;
            for (int i = 0; i < n; i++) {
                if (data[i] > data[i + 1]) {
                    int temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                    lastChange = i;
                }
            }
        }
    }

    private static void insertSort() {
        for (int i = 1; i < data.length; i++) {
            int temp = data[i];
            int j = i - 1;
            for (; j >= 0 && temp < data[j]; j--) {
                data[j + 1] = data[j];
            }
            data[j + 1] = temp;
        }
    }


    private static void selectSort() {
        for (int i = 0; i < data.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[k]) {
                    k = j;
                }
            }
            if (k != i) {
                int temp = data[k];
                data[k] = data[i];
                data[i] = temp;
            }
        }
    }


    private static void genRandomData() {
        int count = 0;
        while (count < data.length) {
            int data = (int) (Math.random() * 100);
            if (!mIntegers.contains(data)) {
                mIntegers.add(data);
                count++;
            }
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = mIntegers.get(i);
        }
    }
}
