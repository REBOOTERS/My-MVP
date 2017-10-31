package com.avaj.datastruct.tree.avl;

/**
 * Created by Rookie on 2017/10/31.
 */

public class AvlTreeTest {

    private static Integer[] arrays = new Integer[]{10, 8, 3, 12, 9, 4, 5, 7, 1, 11, 17};

    public static void main(String[] args) {
        AvlTree<Integer> mAvlTree = new AvlTree<>();
        for (int i = 0; i < arrays.length; i++) {
            mAvlTree.insert(arrays[i]);
        }

        mAvlTree.printTree();
    }
}
