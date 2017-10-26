package com.avaj.datastruct.tree.bst;

/**
 * Created by engineer on 2017/10/26.
 * <p>
 * 二叉搜索树实现
 */

public class BinarySearchTree<T extends Comparable<T>> {

    //根结点
    private TreeNode<T> root = null;


    private TreeNode<T> insert(TreeNode<T> node, T value) {
        if (node == null) {
            return new TreeNode<>(null, value, null);
        } else {
            if (value.compareTo(node.getData()) < 0) {
                node.setLeftChild(insert(node.getLeftChild(), value));
            } else if (value.compareTo(node.getData()) > 0) {
                node.setRightChild(insert(node.getRightChild(), value));
            }
        }

        return node;
    }


    public void insert(T value) {
        if (value == null) {
            return;
        }
        root = insert(root, value);
    }

    /**
     * 打印树
     */
    public void printTree() {
        System.out.print("\n前序遍历：");
        preTraversal(root);
        System.out.print("\n中序遍历：");
        traversal(root);
        System.out.print("\n后序遍历：");
        postTraversal(root);

    }


    /**
     * 访问每个结点
     *
     * @param node
     */
    private void visitNode(TreeNode node) {
        System.out.print(node.getData().toString());
        System.out.print(" ");
    }

    /**
     * 前序遍历-递归实现
     *
     * @param node
     */
    void preTraversal(TreeNode node) {
        if (node != null) {
            visitNode(node);
            preTraversal(node.getLeftChild());
            preTraversal(node.getRightChild());
        }
    }/**
     * 中序遍历-递归实现
     *
     * @param node
     */
    void traversal(TreeNode node) {
        if (node != null) {
            traversal(node.getLeftChild());
            visitNode(node);
            traversal(node.getRightChild());
        }
    }

    /**
     * 后序遍历-递归实现
     * @param node
     */
    void postTraversal(TreeNode node) {
        if (node != null) {
            postTraversal(node.getLeftChild());
            postTraversal(node.getRightChild());
            visitNode(node);
        }
    }


}
