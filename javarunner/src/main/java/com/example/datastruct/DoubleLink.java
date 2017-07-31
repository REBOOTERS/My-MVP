package com.example.datastruct;

/**
 * Created by Rookie on 2017/7/31.
 *
 * 双向循环链表
 */

class DoubleLink<T> {
    private DNode<T> mHead;
    private int mCount;


    DoubleLink() {
        mHead = new DNode<>();
        mHead.prev = mHead;
        mHead.next = mHead;
        mCount = 0;
    }

    int size() {
        return mCount;
    }


    DNode getNode(int index) {

        if (index < 0 || index >= mCount) {
            throw new IndexOutOfBoundsException();
        }


        DNode mNode;

        if (index < mCount / 2) {
            mNode = mHead.next;
            for (int i = 0; i < index; i++) {
                mNode = mNode.next;
            }
        } else {
            mNode = mHead.prev;
            for (int i = 0; i < mCount - index - 1; i++) {
                mNode = mNode.prev;
            }
        }


        return mNode;
    }


    void insert(int index, T value) {

        DNode<T> newNode = new DNode<T>();
        newNode.value = value;


        if (index == 0) {
            newNode.prev = mHead.prev;
            newNode.next = mHead;
            mHead.prev = newNode;
            mHead.next = newNode;
        } else if (index == mCount) {
            newNode.prev = mHead.prev;
            newNode.next = mHead;
            mHead.prev.next = newNode;
            mHead.prev = newNode;
        } else {
            DNode mNode = getNode(index);
            newNode.prev = mNode.prev;
            newNode.next = mNode;
            mNode.prev.next = newNode;
            mNode.prev = newNode;
        }

        mCount++;
    }

    T pop(int index) {
        T value = null;

        DNode indexNode = getNode(index);

        indexNode.prev.next = indexNode.next;
        indexNode.next.prev = indexNode.prev;
        value = (T) indexNode.value;
        indexNode = null;
        mCount--;
        return value;
    }


    public boolean isEmpty() {
        return mCount == 0;
    }

}
