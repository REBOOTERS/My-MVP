package com.avaj.datastruct.tree;

/**
 * Created by engineer on 2017/10/23.
 * <p>
 * 二叉树的链式存储结构
 */

public class BinaryTree {
    //根节点
    private TreeNode<String> root;


    private TreeNode CreateTree() {
        TreeNode<String> nodeH = new TreeNode<>("H");
        TreeNode<String> nodeG = new TreeNode<>("G");

        TreeNode<String> nodeF = new TreeNode<String>(nodeH, "F", null);
        TreeNode<String> nodeE = new TreeNode<String>(nodeG, "E", null);
        TreeNode<String> nodeD = new TreeNode<>("D");

        TreeNode<String> nodeC = new TreeNode<String>(null, "C", nodeF);
        TreeNode<String> nodeB = new TreeNode<String>(nodeD, "B", nodeE);
        TreeNode<String> nodeA = new TreeNode<String>(nodeB, "A", nodeC);
        return nodeA;
    }


}
