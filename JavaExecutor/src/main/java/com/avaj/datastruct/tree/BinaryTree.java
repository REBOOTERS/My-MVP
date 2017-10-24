package com.avaj.datastruct.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by engineer on 2017/10/23.
 * <p>
 * 二叉树的链式存储结构
 */

public class BinaryTree {

    /**
     * 构建二叉树
     *
     * @return 树根
     */
    TreeNode CreateTree() {
        TreeNode<String> nodeH = new TreeNode<>("H");
        TreeNode<String> nodeG = new TreeNode<>("G");

        TreeNode<String> nodeF = new TreeNode<>(nodeH, "F", null);
        TreeNode<String> nodeE = new TreeNode<>(nodeG, "E", null);
        TreeNode<String> nodeD = new TreeNode<>("D");

        TreeNode<String> nodeC = new TreeNode<>(null, "C", nodeF);
        TreeNode<String> nodeB = new TreeNode<>(nodeD, "B", nodeE);
        TreeNode<String> nodeA = new TreeNode<>(nodeB, "A", nodeC);
        return nodeA;
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
     * @param node
     */
    void postTraversal(TreeNode node) {
        if (node != null) {
            postTraversal(node.getLeftChild());
            postTraversal(node.getRightChild());
            visitNode(node);
        }
    }

    /**
     * 前序遍历-迭代实现
     * @param node
     */
    void preTraversalIteration(TreeNode node) {
        // 创建一个栈
        Stack<TreeNode> mStack = new Stack<>();
        while (true) {
            while (node != null) { // 非叶子结点的子树
                // 前序遍历，先访问根结点
                visitNode(node);
                // 将当前结点压入栈
                mStack.push(node);
                // 对左子树继续进行前序遍历
                node=node.getLeftChild();
            }

            if (mStack.isEmpty()) {
                //所有元素已遍历完成
                break;
            }
            // 弹出栈顶结点
            node=mStack.pop();
            // 右子树前序遍历
            node=node.getRightChild();
        }
    }

    /**
     * 中序遍历-迭代实现
     * @param node
     */
    void TraversalIteration(TreeNode node) {
        // 创建一个栈
        Stack<TreeNode> mStack = new Stack<>();
        while (true) {
            while (node != null) { // 非叶子结点的子树
                // 将当前结点压入栈
                mStack.push(node);
                // 对左子树继续进行中序遍历
                node=node.getLeftChild();
            }

            if (mStack.isEmpty()) {
                //所有元素已遍历完成
                break;
            }
            // 弹出栈顶结点
            node=mStack.pop();
            // 中序遍历，访问根结点
            visitNode(node);
            // 右子树中序遍历
            node=node.getRightChild();
        }
    }

    /**
     * 后序遍历-迭代实现
     * @param node
     */
    void postTraversalIteration(TreeNode node) {
        // 创建一个栈
        Stack<TreeNode> mStack = new Stack<>();
        while (true) {
            if (node != null) {
                //当前结点非空，压入栈
                mStack.push(node);
                // 左子树继续遍历
                node=node.getLeftChild();
            }else {
                // 左子树为空

                if(mStack.isEmpty()){
                    return;
                }

                if (mStack.lastElement().getRightChild() == null) {
                    // 栈顶元素右子树为空，则当前结点为叶子结点，输出
                    node=mStack.pop();
                    visitNode(node);
                    while (node == mStack.lastElement().getRightChild()) {
                        visitNode(mStack.lastElement());
                        node=mStack.pop();
                        if (mStack.isEmpty()) {
                            break;
                        }
                    }
                }

                if (!mStack.isEmpty()) {
                    node=mStack.lastElement().getRightChild();
                }else {
                    node=null;
                }
            }
        }
    }

    /**
     * 层序遍历
     * @param node
     */
    void levelTraversal(TreeNode node) {
        //创建队列
        Queue<TreeNode> mNodeQueue = new LinkedList<>();
        // 根结点加入队列
        mNodeQueue.add(node);

        TreeNode temp;

        while (!mNodeQueue.isEmpty()) {
            //元素出队列
            temp=mNodeQueue.poll();
            //输出
            visitNode(temp);
            if (temp.getLeftChild() != null) {
                // 左子树入队列
                mNodeQueue.add(temp.getLeftChild());
            }

            if (temp.getRightChild() != null) {
                //右子树入队列
                mNodeQueue.add(temp.getRightChild());
            }
        }
    }

}
