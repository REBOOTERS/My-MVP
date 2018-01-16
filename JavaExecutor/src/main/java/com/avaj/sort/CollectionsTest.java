package com.avaj.sort;

import java.util.Arrays;
import java.util.List;

/**
 * Created by co-mall on 2017/6/2.
 */

public class CollectionsTest {
    public static void main(String[] args) {
        String[] a = new String[10];
//        a = new String[]{"1", "2", "3", "4"};
        String[] b = new String[]{"5", "6", "7", "8"};
        List<String> datas = Arrays.asList(b);

        List ds = Arrays.asList(1, 2, 4, 4, 5);
        for (Object aa : ds) {
            System.err.println(aa);

        }


        for (String data : datas) {
            System.err.println(data);
        }



        System.arraycopy(b, 0, a, 0, b.length);
        for (String m : a) {
            System.out.println("element is " + m);
        }


    }
}
