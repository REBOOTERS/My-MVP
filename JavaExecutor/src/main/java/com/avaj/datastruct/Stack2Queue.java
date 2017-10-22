package com.avaj.datastruct;

import java.util.Stack;

/**
 * Created by engineer on 2017/10/22.
 * <p>
 * 用栈实现队列
 */

public class Stack2Queue {


    private static class SQueue<E> {
        //只负责付出进栈元素
        private Stack<E> mTStackA;
        //负责中转
        private Stack<E> mTStackB;

        public SQueue() {
            mTStackA = new Stack<>();
            mTStackB = new Stack<>();
        }


        public int getSize() {
            return mTStackA.size() + mTStackB.size();
        }

        private boolean enqueue(E e) {
            return mTStackA.add(e);

        }

        private E dequeue() {
            //两个栈都为空时，则队列也为空
            if (mTStackA.isEmpty() && mTStackB.isEmpty()) {
                return null;
            }


            if (mTStackB.isEmpty()) {
                while (!mTStackA.isEmpty()) {
                    mTStackB.push(mTStackA.pop());
                }
            }

            return mTStackB.pop();
        }
    }


    //测试类
    public static void main(String[] args) {
        SQueue<String> mSQueue = new SQueue<>();

        mSQueue.enqueue("A");
        mSQueue.enqueue("b");
        System.out.println("出对列:"+mSQueue.dequeue());
        mSQueue.enqueue("B");
        mSQueue.enqueue("C");
        System.out.println("出队列:"+mSQueue.dequeue());
        mSQueue.enqueue("D");

        int size = mSQueue.getSize();

        System.out.printf("%d 个元素出队：\n", size);
        for (int i = 1; i <= size; i++) {
            System.out.printf("第 %d 个出队元素：%s\n", i, mSQueue.dequeue());
        }

    }

}
