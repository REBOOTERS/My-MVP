package com.avaj.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rookie on 2017-03-23.
 */

public class MyGeneric {

    public static void main(String[] args) {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("item-" + i);
        }

        print(datas);
        Collections.sort(datas);
        System.out.println("\n");
        print(datas);
    }

    private static void print(List<String> strs) {
        for (String str : strs) {
            System.out.print(str + " ");
        }
    }
}
