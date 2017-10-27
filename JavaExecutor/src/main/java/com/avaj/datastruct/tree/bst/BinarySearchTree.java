package com.avaj.datastruct.tree.bst;

/**
 * Created by engineer on 2017/10/26.
 * <p>
 * 二叉搜索树实现
 */

public class BinarySearchTree<T extends Comparable<T>> {

    //根结点
    private TreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }


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
            // 树为空,则创建根节点
            return new TreeNode<>(value);
        } else {
            if (compare(node, value) < 0) { // 插入值比根节点小，在左子树继续创建搜索二叉树
                node.leftChild = insert(node.getLeftChild(), value);
            } else if (compare(node, value) > 0) { // 插入值比根节点大，在右子树继续创建二叉树
                node.rightChild = insert(node.getRightChild(), value);
            }
        }

        return node;
    }

    private int compare(TreeNode<T> node, T value) {
        return value.compareTo(node.getData());
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
        if (node == null) {
            // 当查找一个不在树中元素时，抛出异常
            throw new RuntimeException("the value must not in the tree");
        }

        if (compare(node, value) < 0) {
            // 小于根节点时，从去左子树找
            return find(node.getLeftChild(), value);
        } else if (compare(node, value) > 0) {
            // 大于根节点时，从右子树找
            return find(node.getRightChild(), value);
        } else {
            // 刚好等于，找到了
            return node;

            // 剩下还有一种情况，就是不等于，也就是所查找的元素不在树中
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
            if (compare(current, value) < 0) {
                current = current.getLeftChild();
            } else if (compare(current, value) > 0) {
                current = current.getRightChild();
            } else {
                return current;
            }
        }
        // current为null,说明所查找的元素不在tree里
        return null;
    }

    /**
     * 查找最大值
     *
     * @return
     */
    public T findMax() {
        if (isEmpty()) return null;
        return findMax(root);
    }

    /**
     * 从特定结点开始寻找最大值
     *
     * @param node
     * @return
     */
    private T findMax(TreeNode<T> node) {
        TreeNode<T> temp = node;
        while (temp.getRightChild() != null) {
            temp = temp.getRightChild();
        }
        return temp.getData();
    }


    /**
     * 查找最小值
     *
     * @return
     */
    public T findMin() {
        if (isEmpty()) return null;
        return findMin(root);
    }

    /**
     * 从特定结点开始寻找最小值
     *
     * @param node
     * @return
     */
    private T findMin(TreeNode<T> node) {
        TreeNode<T> temp = node;
        while (temp.getLeftChild() != null) {
            temp = temp.getLeftChild();
        }
        return temp.getData();
    }

    /**
     * 从树中删除值为value 的特定结点
     *
     * @param value
     */
    public void delete(T value) {
        if (value == null || isEmpty()) {
            return;
        }

        root = delete(root, value);
    }


    private TreeNode<T> delete(TreeNode<T> node, T value) {

        // 结点为空，要出删除的元素不在树中
        if (node == null) {
            return node;
        }

        if (compare(node, value) < 0) { // 去左子树删除
            node.leftChild = delete(node.getLeftChild(), value);
        } else if (compare(node, value) > 0) { // 去右子树删除
            node.rightChild = delete(node.getRightChild(), value);
        } else { // 要删除的就是当前结点
            if (node.getLeftChild() != null && node.getRightChild() != null) {// 被删除的结点，包含左右子树
                T temp = findMin(node.getRightChild()); // 得到右子树的最小值
                node.setData(temp); //右子树最小值替换当前结点
                node.rightChild = delete(node.getRightChild(), temp); // 从右子树删除这个最小值的结点
            } else {// 被删除的结点，包含一个子树或没有子树
                if (node.getLeftChild() != null) {
                    node = node.getLeftChild();
                } else {
                    node = node.getRightChild();
                }
            }
        }

        return node;
    }

    public int getTreeHeight() {
        if (isEmpty()) {
            // 空树度为0
            return 0;
        }

        return getTreeHeight(root);

    }

    private int getTreeHeight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getTreeHeight(node.getLeftChild());
        int rightHeight = getTreeHeight(node.getRightChild());
        int max = leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
        // 得到左右子树中较大的返回.
        return max;
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
