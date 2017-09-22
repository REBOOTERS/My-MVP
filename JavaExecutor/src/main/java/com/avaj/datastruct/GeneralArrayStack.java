package com.avaj.datastruct;

/**
 * Created by Rookie on 2017/8/1.
 * <p>
 * Java : 数组实现的栈，能存储任意类型的数据
 *
 * @author skywang
 * @date 2013/11/07
 */

import java.lang.reflect.Array;

public class GeneralArrayStack<T> {

    private static final int DEFAULT_SIZE = 12;
    private T[] mArray;
    private int count;

    public GeneralArrayStack(Class<T> type) {
        this(type, DEFAULT_SIZE);
    }

    public GeneralArrayStack(Class<T> type, int size) {
        // 不能直接使用mArray = new T[DEFAULT_SIZE];
        mArray = (T[]) Array.newInstance(type, size);
        count = 0;
    }

    // 将val添加到栈中
    public void push(T val) {
        mArray[count++] = val;
    }

    // 返回“栈顶元素值”
    public T peek() {
        return mArray[count - 1];
    }

    // 返回“栈顶元素值”，并删除“栈顶元素”
    public T pop() {
        T ret = mArray[count - 1];
        count--;
        return ret;
    }

    // 返回“栈”的大小
    public int size() {
        return count;
    }

    // 返回“栈”是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    // 打印“栈”
    public void PrintArrayStack() {
        if (isEmpty()) {
            System.out.printf("stack is Empty\n");
        }

        System.out.printf("stack size()=%d\n", size());

        int i = size() - 1;
        while (i >= 0) {
            System.out.println(mArray[i]);
            i--;
        }
    }


}
