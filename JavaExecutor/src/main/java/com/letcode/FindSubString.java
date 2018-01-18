package com.letcode;

public class FindSubString {
    public static void main(String[] args) {
        String source = "abcdefghijk";
        String sub = "bcd";
        int index=indexOf(source, sub, 0);
        System.out.println("the index=" + index);
    }


    private static int indexOf(String source,
                               String target,
                               int fromIndex) {
        final int sourceLength = source.length();
        final int targetLength = target.length();
        if (fromIndex >= sourceLength) {
            return (targetLength == 0 ? sourceLength : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetLength == 0) {
            return fromIndex;
        }

        char first = target.charAt(0);
        int max = (sourceLength - targetLength);

        for (int i = fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source.charAt(i) != first) {
                while (++i <= max && source.charAt(i) != first) ;
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetLength - 1;
                for (int k = 1; j < end && source.charAt(j)
                        == target.charAt(k); j++, k++)
                    ;

                if (j == end) {
                    /* Found whole string. */
                    return i;
                }
            }
        }
        return -1;
    }

}