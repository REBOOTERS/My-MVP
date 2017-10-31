package com.avaj.datastruct.tree.avl;

/**
 * Created by engineer on 2017/10/31.
 *
 * AVL 树节点定义
 */

public class TreeNode<T extends Comparable<T>> {

    // 数据域
    public T data;
    // 左子树
    public TreeNode<T> leftChild;
    // 右子树
    public TreeNode<T> rightChild;

    //当前结点的高度
    public int height;


    public TreeNode(T data) {
        this(null, data, null);
    }

    public TreeNode(TreeNode leftChild, T data, TreeNode rightChild) {
        this(data, leftChild, rightChild, 0);
    }

    public TreeNode(T data, TreeNode<T> leftChild, TreeNode<T> rightChild, int height) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.height = height;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
