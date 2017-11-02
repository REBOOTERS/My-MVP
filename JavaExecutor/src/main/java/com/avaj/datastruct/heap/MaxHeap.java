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
        // 获取最后一个元素的在数组中的索引位置,注意是从index=1的位置开始添加，因此最后一个元素的位置是size-1
        int index = mHeap.size() - 1;
        // 其父结点位置
        int pIndex = index / 2;
        // 得到数组中最后一个元素
        T last = mHeap.get(index);


        while (index > 1) {
            // 插入结点大于其父结点，则不用调整
            if (compare(last, mHeap.get(pIndex)) > 0) {
                break;
            } else {
                // 依次把较大值“将”下来
                mHeap.set(index, mHeap.get(pIndex));
                index = index / 2;
                pIndex = index / 2;
            }
        }
        mHeap.set(index, last);


    }


    public boolean remove(T value) {
        if (mHeap.isEmpty()) {
            return false;
        }
        // 得到数组中这个元素的下标
        int index = mHeap.indexOf(value);
        if (index == -1) { // 被删除元素不在数组中，即删除元素不在堆中
            return false;
        }

        // 获取最后一个元素的在数组中的索引位置,注意是从index=1的位置开始添加，因此最后一个元素的位置是size-1
        int lastIndex = mHeap.size() - 1;
        // 用最后一个元素替换被删除的位置
        T temp = mHeap.get(lastIndex);
        mHeap.set(index, temp);
        // 移除数组最后一个元素
        mHeap.remove(lastIndex);

        int parent;
        for (parent = index; parent * 2 <= mHeap.size(); parent = index) {
            //当前结点左子树下标
            index = parent * 2;
            // 左子树下标不等于数组长度，因此必然有右子树 ,则左右子树比较大小
            if (index != mHeap.size() && compare(mHeap.get(index), mHeap.get(index + 1))<0) {
                // 如果右子树大，则下标指向右子树
                index=index+1;
            }

            if (compare(temp, mHeap.get(index)) > 0) {
                //当前结点大于其左右子树，则不用调整，直接退出
                break;
            }else {
                // 用子树替换当前结点
                mHeap.set(parent, mHeap.get(index));
            }


        }
        mHeap.set(parent, temp);
        return true;


    }

    private int compare(T a, T b) {
        return a.compareTo(b);
    }

    public void printHeap(){
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<mHeap.size();i++) {
            sb.append(mHeap.get(i)).append(" ");
        }

        System.out.println(sb.toString());
    }

}
