package com.avaj.datastruct;

/**
 *
 * 双向循环链表
 */

class DoubleLoopLink<T> {
    //头结点
    private DNode<T> mHead;
    //节点个数
    private int mCount;


    /**
     * 初始化线性表
     */
    DoubleLoopLink() {
        //初始化头结点
        mHead = new DNode<>();
        //创建一个空的循环链表，头结点的前驱、后继指针都指向自己
        mHead.prev = mHead;
        mHead.next = mHead;
        //结点个数
        mCount = 0;
    }

    /**
     * 返回线性表元素个数
     * @return
     */
    int size() {
        return mCount;
    }

    /**
     * 获得线性表某个结点
     * @param index  index 范围（0~mCount-1)
     * @return
     */
    DNode getNode(int index) {

        //位置越界
        if (index < 0 || index >= mCount) {
            throw new IndexOutOfBoundsException();
        }


        DNode mNode;

        // 比较一下查找位置和结点个数的关系，优化查找区间
        if (index < mCount / 2) {
            //指向头结点的后继结点，顺时针查找
            mNode = mHead.next;
            for (int i = 0; i < index; i++) {
                mNode = mNode.next;
            }
        } else {
            //指向头结点的前驱结点，逆时针查找
            mNode = mHead.prev;
            for (int i = 0; i < mCount - index - 1; i++) {
                mNode = mNode.prev;
            }
        }


        return mNode;
    }


    /**
     * 在线性表某个位置index插入结点
     * @param index
     * @param value
     */
    void insert(int index, T value) {

        //创建一个新的结点
        DNode<T> newNode = new DNode<T>();
        //新节点数据域赋值
        newNode.value = value;

        //插入到第一个结点之前
        if (index == 0) {
            //新节点前驱指向头结点前驱
            newNode.prev = mHead;
            //新节点后继指向头结点的后继，即第一个结点
            newNode.next = mHead.next;
            //第一个结点的前驱指向新节点
            mHead.next.prev = newNode;
            //头结点的后继指向新节点
            mHead.next = newNode;
        } else if (index == mCount) { //新节点追加到尾部结点之后
            //新节点前驱指向尾部结点
            newNode.prev = mHead.prev;
            //新节点后继指向头结点
            newNode.next = mHead;
            //原先的尾部结点，后继指向新节点
            mHead.prev.next = newNode;
            //头结点前驱指向新节点
            mHead.prev = newNode;
        } else { //插入到非头尾的中间位置结点之前
            //首先获取index位置的结点
            DNode mNode = getNode(index);
            //新节点前驱指向index结点的前驱
            newNode.prev = mNode.prev;
            //新节点的后继指向index位置结点
            newNode.next = mNode;
            //index 位置前驱结点的后继指向新节点
            mNode.prev.next = newNode;
            // idnex 位置结点前驱指向新节点
            mNode.prev = newNode;
        }

        mCount++;
    }

    /**
     * 返回某个位置的结点的数据域
     * @param index
     * @return
     */
    T pop(int index) {
        T value = null;
        //获取Index位置的结点
        DNode indexNode = getNode(index);
        //index位置结点的--前驱结点的-后继指向--index位置的后继结点
        indexNode.prev.next = indexNode.next;
        //index位置结点的--后驱结点的-前驱指向--index位置的前驱结点
        indexNode.next.prev = indexNode.prev;
        value = (T) indexNode.value;
        indexNode = null;
        mCount--;
        return value;
    }

    /**
     * 线性表是否为空
     * @return
     */
    public boolean isEmpty() {
        return mCount == 0;
    }

}
