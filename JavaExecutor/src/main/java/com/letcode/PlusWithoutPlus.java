package com.letcode;

/**
 * author : Rookie
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/01/10
 * desc   :
 * version: 1.0
 */

public class PlusWithoutPlus {
    public static void main(String[] args) {
        int a = 5;
        int b = 7;
        System.out.printf("\n %d + %d = %d ", a, b, plus(a, b));
    }

    public static int plus(Integer a, Integer b) {
        String aStr = Integer.toBinaryString(a);
        String bStr = Integer.toBinaryString(b);
        System.out.printf(" a =" + aStr);
        System.out.printf("\n b = " + bStr);


        int sum;
        int temp = a & b;
        int digit = temp << 1;

        while (digit != 0) {

            sum = a ^ b;
            temp = a & b;
            digit = temp << 1;
            a = sum;
            b = digit;
        }

        return a;
    }
}
