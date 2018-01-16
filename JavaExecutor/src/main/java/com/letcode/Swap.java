package com.letcode;

/**
 * author : Rookie
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/01/16
 * desc   :
 * version: 1.0
 */

public class Swap {
    public static void main(String[] args) {
        int A = 10;
        int B = 20;


        System.err.printf("before swap A=%d,B=%d\n", A, B);
//        swap1(A, B);
//        swap2(A,B);
        swap3(A, B);
    }

    private static void swap3(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.err.printf("after swap A=%d,B=%d\n", a, b);
    }

    private static void swap2(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;

        System.err.printf("after swap A=%d,B=%d\n", a, b);
    }

    private static void swap1(int a, int b) {
        int temp = a;
        a = b;
        b = temp;

        System.err.printf("after swap A=%d,B=%d\n", a, b);
    }
}
