package com.avaj.datastruct.tree;

/**
 * Created by engineer on 2017/10/23.
 */

public class TreeNode<E> {
    private E data;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;

    public TreeNode(E data, TreeNode<E> leftChild, TreeNode<E> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
