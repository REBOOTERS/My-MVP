package com.avaj.datastruct;

import java.util.Deque;

/**
 * Created by engineer on 2017/10/22.
 */

public class ShareStack<T> {

    Deque mDeque;

    private Object[] element; //存放元素的数组

    private int stackSize;  // 栈大小

    private int top1; //栈1的栈顶指针

    private int top2; //栈2的栈顶指针


    /**
     * 初始化栈
     * @param size
     */
    public ShareStack(int size){
        element = new Object[size];
        stackSize = size;
        top1 = -1;
        top2 = stackSize;
    }


    /**
     * 压栈
     * @param i 第几个栈
     * @param o 入栈元素
     * @return
     */
    public boolean push(int i , Object o){

        if(top1 == top2 - 1)
            throw new RuntimeException("栈满！");
        else if(i == 1){
            top1++;
            element[top1] = o;
        }else if(i == 2){
            top2--;
            element[top2] = o;
        }else
            throw new RuntimeException("输入错误！");

        return true;
    }

    /**
     * 出栈
     * @param i
     * @return
     */
    @SuppressWarnings("unchecked")
    public T pop(int i){

        if(i == 1){
            if(top1 == -1)
                throw new RuntimeException("栈1为空");
            return (T)element[top1--];
        } else if(i == 2){
            if(top2 == stackSize)
                throw new RuntimeException("栈2为空");
            return (T)element[top2++];
        } else
            throw new RuntimeException("输入错误！");

    }


    /**
     * 获取栈顶元素
     * @param i
     * @return
     */
    @SuppressWarnings("unchecked")
    public T get(int i){

        if(i == 1){
            if(top1 == -1)
                throw new RuntimeException("栈1为空");
            return (T)element[top1];
        } else if(i == 2){
            if(top2 == stackSize)
                throw new RuntimeException("栈2为空");
            return (T)element[top2];
        } else
            throw new RuntimeException("输入错误！");
    }


    /**
     * 判断栈是否为空
     * @param i
     * @return
     */
    public boolean isEmpty(int i){

        if(i == 1){
            if(top1 == -1)
                return true;
            else
                return false;
        } else if(i == 2){
            if(top2 == stackSize)
                return true;
            else
                return false;
        } else
            throw new RuntimeException("输入错误！");
    }

    /**
     * 遍历
     */
    @SuppressWarnings("unchecked")
    @Override
    public String toString(){

        String str1 = "栈1：[";
        String str2 = "栈2：[";

        for(int i=top1;i>=0;i--){
            if(i == 0)
                str1 = str1 + (T)element[i];
            else
                str1 = str1 + (T)element[i] + ",";
        }

        str1 += "]";

        for(int i=top2;i<stackSize;i++){
            if(i == stackSize-1)
                str2 = str2 + (T)element[i];
            else
                str2 = str2 + (T)element[i] + ",";
        }

        str2 += "]";

        return str1 + "\n\r" + str2;

    }
}
