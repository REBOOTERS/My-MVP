package com.avaj.datastruct.huffman;

/**
 * Created by engineer on 2017/11/11.
 */

public class HuffmanNode<T> implements Comparable<HuffmanNode<T>> {

    // 权重
    public int weight;
    public T value;
    public HuffmanNode left;
    public HuffmanNode right;


    public HuffmanNode(int weight, T value) {
        this(weight, value, null, null);
    }

    public HuffmanNode(int weight, T value, HuffmanNode left, HuffmanNode right) {
        this.weight = weight;
        this.value = value;
        this.left = left;
        this.right = right;
    }


    @Override
    public int compareTo(HuffmanNode<T> tHuffmanNode) {
        return tHuffmanNode.weight - this.weight;
    }
}
