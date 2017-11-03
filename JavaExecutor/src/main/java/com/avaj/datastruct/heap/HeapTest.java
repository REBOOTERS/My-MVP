package com.avaj.datastruct.heap;

/**
 * Created by engineer on 2017/11/2.
 */

public class HeapTest {
    private static Integer[] arrays = new Integer[]{10, 8, 3, 12, 9, 4, 5, 7, 1, 11, 17};

    public static void main(String[] args) {
        MaxHeapTest();

        MinHeapTest();

    }

    private static void MinHeapTest() {
        MinHeap<Integer> mMinHeap = new MinHeap<>();
        for(int i=0;i<arrays.length;i++) {
            mMinHeap.insert(arrays[i]);
        }
        mMinHeap.printHeap();

        System.out.printf("remove value %d from minHeap isSuccess=%b \n", 17, mMinHeap.remove(17));
        mMinHeap.printHeap();
        System.out.printf("remove value %d from minHeap isSuccess=%b \n", 1, mMinHeap.remove(1));
        mMinHeap.printHeap();
        System.out.printf("remove value %d from minHeap isSuccess=%b \n", 12, mMinHeap.remove(12));
        mMinHeap.printHeap();
        System.out.printf("insert value %d to minHeap \n", 16);
        mMinHeap.insert(16);
        mMinHeap.printHeap();
    }

    private static void MaxHeapTest() {
        MaxHeap<Integer> mMaxHeap = new MaxHeap<>();
        for (int i = 0; i < arrays.length; i++) {
            mMaxHeap.insert(arrays[i]);
        }

        mMaxHeap.printHeap();
        System.out.printf("remove value %d from maxHeap isSuccess=%b \n", 17, mMaxHeap.remove(17));
        mMaxHeap.printHeap();
        System.out.printf("remove value %d from maxHeap isSuccess=%b \n", 1, mMaxHeap.remove(1));
        mMaxHeap.printHeap();
        System.out.printf("remove value %d from maxHeap isSuccess=%b \n", 12, mMaxHeap.remove(12));
        mMaxHeap.printHeap();
        System.out.printf("insert value %d to maxHeap \n", 16);
        mMaxHeap.insert(16);
        mMaxHeap.printHeap();

    }
}
