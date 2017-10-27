package com.avaj.datastruct.tree.bst;

/**
 * Created by engineer on 2017/10/26.
 * <p>
 * 二叉搜索树 测试类
 */

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> mSearchTree = new BinarySearchTree<>();

        mSearchTree.insert(8);
        mSearchTree.insert(2);
        mSearchTree.insert(10);
        mSearchTree.insert(4);
        mSearchTree.insert(12);
        //插入相同值测试
        mSearchTree.insert(10);
        mSearchTree.insert(5);
        mSearchTree.insert(7);
        mSearchTree.insert(1);
        mSearchTree.insert(17);

        mSearchTree.printTree();


        System.out.printf("\nfind value %d in mSearchTree \n", 12);
        TreeNode mTreeNode = mSearchTree.find(12);
        TreeNode mTreeNode_1 = mSearchTree.findIter(12);
        System.out.println("递归实现结点  = :" + mTreeNode+" value="+mTreeNode.getData());
        System.out.println("非递归实现结点= :" + mTreeNode_1+" value="+mTreeNode_1.getData());

        System.out.println("\nfind the max value in mSearchTree = " + mSearchTree.findMax());
        System.out.println("find the min value in mSearchTree = " + mSearchTree.findMin());

    }
}

