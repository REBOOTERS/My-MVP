package com.letcode.string;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/2/8
 * desc   :
 * version: 1.0
 */
public class Runner {
    public static void main(String[] args) {
        String source = "abcdefghijklmn";
        String testA = "cde";
        String target = "hello";

        int index = findSubStr(source, testA);

        System.out.println("find subStr cde at index " + index);

        testA.replace("a", "c");
    }

    private static void replace(String source, String target, String place) {
        char[] s = source.toCharArray();
        char[] t = source.toCharArray();
        char[] p = place.toCharArray();

        int index = 0;
        while ((index = findSubStr(source, target)) > 0) {
            for(int i=index;i<s.length;i++){

            }
        }
    }

    private static int findSubStr(String source, String subStr) {

        if (source == null && subStr == null) {
            return -1;
        }

        if (source.equals(subStr)) {
            return 0;
        }

        char[] s = source.toCharArray();
        char[] t = subStr.toCharArray();

        int sLength = s.length;
        int tLength = t.length;
        int m = sLength - tLength;

        for (int i = 0; i <= m; i++) {
            int j = 0;
            while (j < tLength && s[i + j] == t[j]) {
                j++;
            }

            if (j >= tLength) {
                return i;
            }
        }
        return -1;
    }
}
