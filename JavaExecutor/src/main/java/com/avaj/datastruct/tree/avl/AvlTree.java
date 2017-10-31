package com.avaj.datastruct.tree.avl;

/**
 * Created by engineer on 2017/10/31.
 */

public class AvlTree<T extends Comparable<T>> {
    //根结点
    private TreeNode<T> root;

    public AvlTree() {
        root = null;
    }

    private int height(TreeNode<T> node) {
        if (node != null)
            return node.height;

        return 0;
    }


    private int getMax(int x, int y) {
        return x > y ? x : y;
    }

    /**
     * 左旋
     *
     * @param node
     * @return
     */
    private TreeNode<T> leftRotate(TreeNode<T> node) {

        // 失衡结点的左子树作为新的根节点
        TreeNode<T> newRoot = node.leftChild;
        node.leftChild = newRoot.rightChild;
        newRoot.rightChild = node;

        node.height = getMax(height(node.leftChild), height(node.rightChild)) + 1;
        newRoot.height = getMax(height(newRoot.leftChild), newRoot.height) + 1;

        return newRoot;
    }

    /**
     * 右旋
     *
     * @param node
     * @return
     */
    private TreeNode<T> rightRotate(TreeNode<T> node) {

        TreeNode<T> newRoot = node.rightChild;
        node.rightChild = newRoot.leftChild;
        newRoot.leftChild = node;

        node.height = getMax(height(node.leftChild), height(node.rightChild)) + 1;
        newRoot.height = getMax(height(newRoot.rightChild), node.height) + 1;

        return newRoot;
    }

    /**
     * LR 左右旋转
     *
     * @param node
     * @return
     */
    private TreeNode<T> leftRightRotate(TreeNode<T> node) {
        node.leftChild = rightRotate(node.leftChild);
        return leftRotate(node);
    }

    /**
     * RL 右左旋转
     *
     * @param node
     * @return
     */
    private TreeNode<T> rightLeftRotate(TreeNode<T> node) {
        node.rightChild = leftRightRotate(node.rightChild);
        return rightRotate(node);
    }

    /**
     * 插入结点
     * @param key
     */
    public void insert(T key) {
        root = insert(root, key);
    }

    private TreeNode<T> insert(TreeNode<T> node, T value) {
        if (node == null) {
            // 新建节点
            node = new TreeNode<T>(value);
            if (node == null) {
                return null;
            }
        } else {
            int cmp = value.compareTo(node.getData());

            if (cmp < 0) {    // 应该将key插入到"tree的左子树"的情况
                node.leftChild = insert(node.leftChild, value);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height(node.leftChild) - height(node.rightChild) == 2) {
                    if (value.compareTo(node.leftChild.getData()) < 0)
                        node = leftRotate(node);
                    else
                        node = leftRightRotate(node);
                }
            } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况
                node.rightChild = insert(node.rightChild, value);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height(node.rightChild) - height(node.leftChild) == 2) {
                    if (value.compareTo(node.rightChild.getData()) > 0)
                        node = rightRotate(node);
                    else
                        node = rightLeftRotate(node);
                }
            } else {    // cmp==0
                System.out.println("添加失败：不允许添加相同的节点！");
            }
        }

        node.height = getMax(height(node.leftChild), height(node.rightChild)) + 1;

        return node;
    }



}
