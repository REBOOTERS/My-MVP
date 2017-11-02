package com.avaj.datastruct.heap;

/**
 * Created by engineer on 2017/11/2.
 */

public class HeapTest {
    private static Integer[] arrays = new Integer[]{10, 8, 3, 12, 9, 4, 5, 7, 1, 11, 17};

    public static void main(String[] args) {
        MaxHeap mMaxHeap = new MaxHeap();
        for (int i = 0; i < arrays.length; i++) {
            mMaxHeap.insert(arrays[i]);
        }

        mMaxHeap.printHeap();
    }
}
