package com.avaj;

import java.util.ArrayList;
import java.util.Arrays;

public class Runner {


    public static void main(String[] args) {
        String a = "abc";
        String b = "abc";

        System.out.println("a==b " + a == b);
        System.out.println("a.equals(b) " + a.equals(b));
        int count = 0;
        ArrayList<Integer> datas = new ArrayList<>();
        while (count < 100) {
            int i = (int) (Math.random() * 100);
            if (!datas.contains(i)) {
                datas.add(i);
                count++;
            }

        }
        Object[] array = datas.toArray();
        System.out.println("now the datas = " + Arrays.toString(array));

        for (int i = 1; i < array.length; i++) {
            int temp = (int) array[i];
            int j = i - 1;
            for (; j >= 0 && temp < (int) array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }


//        for (int i = 0; i < array.length - 1; i++) {
//            int k = i;
//            for (int j = i + 1; j < array.length; j++) {
//                if ((int) array[j] < (int) array[k]) {
//                    k = j;
//                }
//
//            }
//
//            if (k != i) {
//                int t = (int) array[i];
//                array[i] = array[k];
//                array[k] = t;
//            }
//
//        }
        System.out.println("now the datas = " + Arrays.toString(array));
    }


}
