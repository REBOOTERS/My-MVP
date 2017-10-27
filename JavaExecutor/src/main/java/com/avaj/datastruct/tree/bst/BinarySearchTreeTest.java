package com.avaj.datastruct.tree.bst;

/**
 * Created by engineer on 2017/10/26.
 * <p>
 * 二叉搜索树 测试类
 */

public class BinarySearchTreeTest {

    private static Integer[] arrays = new Integer[]{8, 9, 2, 10, 4, 12, 10, 5, 7, 1, 17};

    public static void main(String[] args) {
        BinarySearchTree<Integer> mSearchTree = new BinarySearchTree<>();

        for (Integer data : arrays) {
            mSearchTree.insert(data);
        }

        mSearchTree.printTree();


        System.out.printf("\nfind value %d in mSearchTree \n", 12);
        TreeNode mTreeNode = mSearchTree.find(12);
        TreeNode mTreeNode_1 = mSearchTree.findIter(12);
        System.out.println("递归实现结点  = :" + mTreeNode + ", value=" + mTreeNode.getData());
        System.out.println("非递归实现结点= :" + mTreeNode_1 + ", value=" + mTreeNode_1.getData());

        System.out.println("\nfind the max value in mSearchTree = " + mSearchTree.findMax());
        System.out.println("find the min value in mSearchTree = " + mSearchTree.findMin());


        mSearchTree.delete(4);
        mSearchTree.printTree();
        System.out.println();
        mSearchTree.delete(177);
        mSearchTree.printTree();

        System.out.println("\n Tree's height =" + mSearchTree.getTreeHeight());

    }
}

