package huyifei.mymvp.util;

/**
 * @author: zhuyongging
 * @since: 2019-06-05
 */
public class MathUtil {

    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * @param c
     * @return 计算字符串长度
     */
    public static int calculateLength(final CharSequence c) {
        int len = 0;
        final int l = c.length();
        for (int i = 0; i < l; i++) {
            final char tmp = c.charAt(i);
            if (tmp >= 0x20 && tmp <= 0x7E) {
                len += 1;
            } else {
                len += 2;
            }
        }
        return len;
    }


}
