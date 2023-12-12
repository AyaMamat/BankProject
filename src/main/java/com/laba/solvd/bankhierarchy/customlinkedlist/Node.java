package com.laba.solvd.bankhierarchy.customlinkedlist;

public class Node<E> {

    E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
        this.next = null;
    }
}
