package com.example.datastruct;

/**
 * Created by Rookie on 2017/7/31.
 */

class DNode<T> {
    DNode prev,next;
    T value;

    DNode(){
        this(null, null, null);
    }

    private DNode(DNode prev, DNode next, T value) {
        this.prev = prev;
        this.next = next;
        this.value = value;
    }
}
