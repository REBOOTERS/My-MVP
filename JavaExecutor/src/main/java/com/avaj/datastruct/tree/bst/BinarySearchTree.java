package com.avaj.datastruct.tree.bst;

/**
 * Created by engineer on 2017/10/26.
 * <p>
 * 二叉搜索树实现
 */

public class BinarySearchTree<T extends Comparable<T>> {

    //根结点
    private TreeNode<T> root = null;


    /**
     * 树中插入元素
     *
     * @param value
     */
    void insert(T value) {
        if (value == null) {
            return;
        }
        root = insert(root, value);
    }

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

    private boolean isEmpty() {
        return root == null;
    }

    /**
     * find 特定值 递归实现
     *
     * @param value
     * @return
     */
    public TreeNode<T> find(T value) {
        if (isEmpty()) {
            return null;
        } else {
            return find(root, value);
        }
    }

    private TreeNode<T> find(TreeNode<T> node, T value) {
        if (value.compareTo(node.getData()) < 0) {
            return find(node.getLeftChild(), value);
        } else if (value.compareTo(node.getData()) > 0) {
            return find(node.getRightChild(), value);
        } else {
            return node;
        }
    }

    /**
     * 查找特定值-非递归实现
     *
     * @param value
     * @return 结点
     */
    public TreeNode<T> findIter(T value) {
        TreeNode<T> current = root;
        while (current != null) {
            if (value.compareTo(current.getData()) < 0) {
                current = current.getLeftChild();
            } else if (value.compareTo(current.getData()) > 0) {
                current = current.getRightChild();
            } else {
                return current;
            }
        }
        return null;
    }

    /**
     * 查找最大值
     *
     * @return
     */
    public T findMax() {
        if (isEmpty()) return null;

        TreeNode<T> temp = root;
        while (temp.getRightChild() != null) {
            temp = temp.getRightChild();
        }

        return temp.getData();
    }


    /**
     * 查找最小值
     * @return
     */
    public T findMin() {
        if (isEmpty()) return null;

        TreeNode<T> temp = root;
        while (temp.getLeftChild() != null) {
            temp = temp.getLeftChild();
        }

        return temp.getData();
    }




    /**
     * 以三种遍历方式打印树
     */
    void printTree() {
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
    }

    /**
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
     *
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
