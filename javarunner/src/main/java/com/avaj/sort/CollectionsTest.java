package com.avaj.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by co-mall on 2017/6/2.
 */

public class CollectionsTest {
    public static void main(String[] args) {
        String[] a = new String[10];
//        a = new String[]{"1", "2", "3", "4"};
        String[] b = new String[]{"5", "6", "7", "8"};


        System.arraycopy(b, 0, a, 0, b.length);
        for (String m : a) {
            System.out.println("element is " + m);
        }




        Collection<String> lists = new ArrayList<>();
        Collection<String> sets = new HashSet<>();
        Collection<String> treeSets = new TreeSet<>();


    }
}
