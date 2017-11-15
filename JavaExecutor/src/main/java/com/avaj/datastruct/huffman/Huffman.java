package com.avaj.datastruct.huffman;

import java.util.Collections;
import java.util.List;

/**
 * Created by engineer on 2017/11/11.
 */

public class Huffman<T> {

    Huffman<T> root;

    /**
     * 根据数组创建哈夫曼树，返回根节点
     *
     * @param nodes
     * @return
     */
    public HuffmanNode<T> create(List<HuffmanNode> nodes) {
        while (nodes.size() > 1) {
            // 按权重继续排序
            Collections.sort(nodes);

            HuffmanNode left = nodes.get(nodes.size() - 1);
            HuffmanNode right = nodes.get(nodes.size() - 2);

            int weight = left.weight + right.weight;

            HuffmanNode newNode = new HuffmanNode(weight, null, left, right);

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(newNode);
        }

        return nodes.get(0);
    }

}
