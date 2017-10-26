package com.avaj.datastruct.tree.bst;

/**
 * Created by engineer on 2017/10/26.
 * <p>
 * 二叉搜索树 测试类
 */

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> mSearchTree = new BinarySearchTree<>();

        mSearchTree.insert(3);
        mSearchTree.insert(2);
        mSearchTree.insert(4);
        mSearchTree.insert(6);
        mSearchTree.insert(5);
        mSearchTree.insert(7);
        mSearchTree.insert(1);

        mSearchTree.printTree();
    }
}
