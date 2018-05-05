package com.avaj.datastruct.linear;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by engineer on 2017/10/22.
 * <p>
 * 队列实现栈
 */

public class Queue2Stack {


    private static class QStack<E> {
        private Deque<E> mEQueueA;
        private Deque<E> mEQueueB;


        private QStack() {
            mEQueueA = new LinkedList<E>();
            mEQueueB = new LinkedList<E>();
        }

        private int getSize() {
            return mEQueueA.size() + mEQueueB.size();
        }

        private void push(E e) {
            if (mEQueueA.isEmpty()) {
                mEQueueB.addLast(e);
            } else {
                mEQueueA.addLast(e);
            }
        }

        private E pop() {
            if (mEQueueA.isEmpty() && mEQueueB.isEmpty()) {
                return null;
            }

            if (mEQueueA.isEmpty() && !mEQueueB.isEmpty()) {
                return swapDeque(mEQueueA, mEQueueB);
            } else if (mEQueueB.isEmpty() && !mEQueueA.isEmpty()) {
                return swapDeque(mEQueueB, mEQueueA);
            } else {
                //This should never happen
                throw new RuntimeException("At least One of Deque must be empty");
            }

        }

        private E swapDeque(Deque<E> A, Deque<E> B) {
            while (B.size() != 1) {
                //队列B从队头出队，压入队列A的尾部
                A.addLast(B.removeFirst());
            }
            //从队列B队头返回最后的一个元素
            return B.removeFirst();

        }
    }

    public static void main(String[] args) throws Exception {
        QStack<String> mQStack = new QStack<>();

        mQStack.push("A");
        mQStack.push("b");
        System.out.println("栈顶元素pop:"+mQStack.pop());
        mQStack.push("B");
        mQStack.push("C");
        mQStack.push("D");
        System.out.println("栈顶元素pop:"+mQStack.pop());
        mQStack.push("E");

        int size = mQStack.getSize();
        System.out.printf("%d 个元素出栈：\n", size);
        for (int i = 1; i <= size; i++) {
            System.out.printf("第 %d 个出栈元素：%s\n", i, mQStack.pop());
        }
    }
}
