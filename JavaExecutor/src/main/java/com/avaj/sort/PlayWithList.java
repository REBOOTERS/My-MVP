package com.avaj.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/2/5
 * desc   :
 * version: 1.0
 */
public class PlayWithList {
    public static void main(String[] args) {
        List<Integer> mList = new ArrayList<>();
        genRandom(mList);


        System.out.println("origin data=");
        System.out.print("[");
        for (Integer data : mList) {
            System.out.print(" " + data);
        }
        System.out.print(" ]");

//        selectSort(mList);
//        insertSort(mList);
//        bubbleSort(mList);
//        quickSort(mList, 0, mList.size() - 1);

        System.out.println("sorted data=");
        System.out.print("[");
        for (Integer data : mList) {
            System.out.print(" " + data);
        }
        System.out.print(" ]");

//        test();

    }

    private static void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int index = partition(list, low, high);
            quickSort(list, low, index - 1);
            quickSort(list, index + 1, high);
        }
    }

    private static int partition(List<Integer> list, int low, int high) {
        int temp = list.get(low);
        int i = low;
        int j = high;
        while (i != j) {
            while (temp < list.get(j) && i < j) {
                j--;
            }

            if (i < j) {
                list.set(i, list.get(j));
                i++;
            }

            while (temp > list.get(i) && i < j) {
                i++;
            }

            if (i < j) {
                list.set(j, list.get(i));
                j--;
            }
        }
        list.set(i, temp);
        return i;
    }

    private static void bubbleSort(List<Integer> list) {
        int lastChange = list.size() - 1;
        while (lastChange > 0) {
            int n = lastChange;
            lastChange = 0;
            for (int i = 0; i < n; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    lastChange = i;
                }
            }
        }
    }


    private static void insertSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int temp = list.get(i);
            int j = i - 1;
            for (; j >= 0 && temp < list.get(j); j--) {
                list.set(j + 1, list.get(j));
            }
            list.set(j + 1, temp);
        }
    }

    private static void selectSort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int k = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(k) > list.get(j)) {
                    k = j;
                }
            }
            if (k != i) {
                int temp = list.get(k);
                list.set(k, list.get(i));
                list.set(i, temp);
            }
        }
    }

    private static void genRandom(List<Integer> list) {
        int count = 0;
        int flag = -1;
        while (count < 100) {
            int num = (int) (Math.random() * 100);
            if (!list.contains(num)) {
                list.add(num * flag);
                flag = flag * -1;
                count++;
            }
        }
    }

    private static void test() {

        List<Integer> data = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<>();

        int[] numbers = {1, 0, -1, -1, -1, -1, 0, 1, 1, 1};
        for (int i = 0; i < numbers.length - 2; i++) {
            for (int j = i + 1; j < numbers.length - 1; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 0) {
                        data.add(numbers[i]);
                        data.add(numbers[j]);
                        data.add(numbers[k]);
                        result.add(data);
                        break;
                    }
                }
            }
        }


        System.out.println("\nsize==" + result.size());
    }
}
