package com.laba.solvd.bankhierarchy.customlinkedlist;

public class Node<E> {
    E data;
    Node<E> next;

    Node(E element) {
        this.data = element;
        this.next = null;
    }
}
