package huyifei.mymvp;

import org.junit.Test;

import huyifei.mymvp.util.MathUtil;

import static junit.framework.TestCase.assertEquals;


/**
 * @author: zhuyongging
 * @since: 2019-06-05
 */
public class MathUtilUnitTest {
    @Test
    public void addTest() {
        assertEquals(MathUtil.add(1, 2), 3);
        assertEquals(MathUtil.add(0, -1), -1);
        assertEquals(MathUtil.add(-1, -2), -3);
    }

    @Test
    public void calculateTest() {

        assertEquals(MathUtil.calculateLength("1"), 1);
        assertEquals(MathUtil.calculateLength("a"), 1);
        assertEquals(MathUtil.calculateLength("A"), 1);
        assertEquals(MathUtil.calculateLength("aa"), 2);
        assertEquals(MathUtil.calculateLength("aA"), 2);
        assertEquals(MathUtil.calculateLength("AA"), 2);
        assertEquals(MathUtil.calculateLength("AA1"), 3);
        assertEquals(MathUtil.calculateLength("中"), 2);
        assertEquals(MathUtil.calculateLength("中国"), 4);
        assertEquals(MathUtil.calculateLength("中国a"), 5);
        assertEquals(MathUtil.calculateLength("😁"), 4);
        assertEquals(MathUtil.calculateLength("😁😳"), 8);
        assertEquals(MathUtil.calculateLength("😁😳11"), 10);
    }
}
