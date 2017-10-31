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
     * @param node 失衡结点
     * @return 旋转后根节点
     */
    private TreeNode<T> leftRotate(TreeNode<T> node) {
        // 将失衡结点的左子树赋给一个临时结点，也就是将A的左子树B 赋给新的结点
        TreeNode<T> newRoot = node.leftChild;
        // 将B 被右子树BR 挂在A 的左子树上
        node.leftChild = newRoot.rightChild;
        // B 的右子树为失衡的结点即A
        newRoot.rightChild = node;
        // 结点A 的高度为左右子树高度最大值加1
        node.height = getMax(height(node.leftChild), height(node.rightChild)) + 1;
        // 结点B 的高度为左右子树高度最大值加1
        newRoot.height = getMax(height(newRoot.leftChild), newRoot.height) + 1;
        // 返回根节点
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
        // 首先围绕失衡结点的左子树(图中Aug) 和Mar进行一次右旋，这样Mar 和 Aug 换了位置
        node.leftChild = rightRotate(node.leftChild);
        // 最后，围绕May和Mar进行一次左旋
        return leftRotate(node);
    }

    /**
     * RL 右左旋转
     *
     * @param node
     * @return
     */
    private TreeNode<T> rightLeftRotate(TreeNode<T> node) {
        node.rightChild = leftRotate(node.rightChild);
        return rightRotate(node);
    }

    /**
     * 插入结点
     *
     * @param value
     */
    public void insert(T value) {
        root = insert(root, value);
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

            if (cmp < 0) {    // 应该将value插入到"node的左子树"的情况
                node.leftChild = insert(node.leftChild, value);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height(node.leftChild) - height(node.rightChild) == 2) {
                    if (value.compareTo(node.leftChild.getData()) < 0)
                        node = leftRotate(node);
                    else
                        node = leftRightRotate(node);
                }
            } else if (cmp > 0) {    // 应该将value插入到"node的右子树"的情况
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


    /**********************************Tree print************************************/

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
