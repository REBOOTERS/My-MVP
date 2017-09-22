package com.avaj.datastruct;

/**
 *
 * 结点对象
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
