package com.avaj.datastruct.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by engineer on 2017/11/1.
 */

public class MaxHeap<T extends Comparable<T>> {

    private List<T> mHeap;
    //堆内当前元素个数
    public int size;

    public MaxHeap() {
        mHeap = new ArrayList<>();
        // 为了方便，数组下标为0 的位置，放置一个空元素，使得数组从下标为1的位置开始
        // 这样，完全二叉树中，如果结点值为n,那么其左子树则为2n,右子树为2n+1
        mHeap.add(0, null);
    }

    public void insert(T value) {
        //新插入的元素首先放在数组最后，保持完全二叉树的特性
        mHeap.add(value);
        // 获取最后一个元素的在数组中的索引位置,注意是从index=1的位置开始添加，因此最后一个元素的位置是size+1
        int index = mHeap.size() + 1;
        // 其父结点位置
        int pIndex = index / 2;
        // 得到数组中最后一个元素
        T last = mHeap.get(index);


        while (index > 1) {
            // 插入结点大于父结点，则用调整
            if (compare(last, mHeap.get(pIndex)) > 0) {
                break;
            } else {
                mHeap.set(index, mHeap.get(pIndex));
                index = index / 2;
                pIndex = index / 2;
            }
        }
        mHeap.set(index, last);


    }

    private int compare(T a, T b) {
        return a.compareTo(b);
    }

}
