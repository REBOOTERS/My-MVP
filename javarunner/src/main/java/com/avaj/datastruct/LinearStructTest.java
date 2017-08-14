package com.avaj.datastruct;

/**
 * Created by engineer on 2017/8/12.
 */

public class LinearStructTest {

    //线性表的长度
    private static int length;

    public static void main(String[] args) {
        int[] data = new int[30];


        //在线性表的某个位置之前插入元素
        dataInsert(data, 1, 101);
        dataInsert(data, 2, 102);
        dataInsert(data, 3, 103);
        dataInsert(data, 4, 104);
        dataInsert(data, 5, 105);
        dataInsert(data, 6, 106);
        dataInsert(data, 3, 1003);
        dataInsert(data, 4, 1004);
        dataDel(data, 1);
        dataDel(data, 5);
        System.out.println("after insert :");
        for (int e : data) {
            System.out.print(e + " ");
        }
        System.out.println("");

        //获取某个位置的数据元素
        int a = getData(data, 5);
        System.out.println("the element at pos=5 is " + a);

        System.out.println("\nthe length =" + length);


    }

    /**
     * 删除位置pos的元素,这里的pos 不是数组下标，而是正常自然位置
     *
     * @param data
     * @param pos
     */
    private static boolean dataDel(int[] data, int pos) {
        //线性表位空
        if (length == 0) {
            return false;
        }

        //
        if (pos < 1 || pos > length) {
            return false;
        }

        if (pos < length - 1) {
            for (int m = pos; m < length; m++) {
                data[m - 1] = data[m];
            }
        }


        length--;
        return true;


    }

    /**
     * 将新元素e插入到数组data的第i个位置之前
     *
     * @param data
     * @param pos
     * @param e
     * @return
     */
    private static boolean dataInsert(int[] data, int pos, int e) {
        //线性表已满
        if (length == data.length) {
            return false;
        }
        //位置越界
        if (pos < 1 || pos > length + 1) {
            return false;
        }

        if (pos <= length) {
            //从pos-1 位置的元素都往后移动
            for (int m = length - 1; m >= pos - 1; m--) {
                data[m + 1] = data[m];
            }
        }
        data[pos - 1] = e;
        length++;
        return true;


    }

    /**
     * 获取线性表中第i个位置的元素
     * @param data
     * @param i
     * @return
     */
    private static int getData(int[] data, int i) {
        //线性表为空，或者位置越界返回-1
        if (length == 0 || i < 1 || i > length) {
            return -1;
        }
        return data[i - 1];
    }
}
