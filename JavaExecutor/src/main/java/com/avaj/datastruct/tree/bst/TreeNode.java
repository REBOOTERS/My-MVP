package com.avaj.datastruct.tree.bst;

/**
 * Created by engineer on 2017/10/26.
 * <p>
 * 二叉搜索树树结点定义
 */

public class TreeNode<T extends Comparable<T>> {

    // 数据域
    public T data;
    // 左子树
    public TreeNode<T> leftChild;
    // 右子树
    public TreeNode<T> rightChild;


    public TreeNode(T data) {
        this(null, data, null);
    }

    public TreeNode(TreeNode leftChild, T data, TreeNode rightChild) {
        this.leftChild = leftChild;
        this.data = data;
        this.rightChild = rightChild;
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

}
