package com.laba.solvd.bankhierarchy.customlinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedListIterator<E> implements Iterator<E> {
    private Node<E> currentNode;

    public CustomLinkedListIterator(Node<E> head) {
        this.currentNode = head;
    }

    @Override
    public boolean hasNext() {
        return currentNode != null;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E data = currentNode.data;
        currentNode = currentNode.next;
        return data;
    }

}
