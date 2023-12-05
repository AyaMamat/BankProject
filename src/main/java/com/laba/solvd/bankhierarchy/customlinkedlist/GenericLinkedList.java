package com.laba.solvd.bankhierarchy.customlinkedlist;

import java.util.*;

public class GenericLinkedList<E> {

    private Node<E> head;
    private int size;

    public GenericLinkedList() {
        head = null;
        size = 0;
    }

    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void remove(E key) {
        Node<E> current = head;
        Node<E> previous = null;

        while (current != null) {
            if (key.equals(current.data)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    public E get(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }

        Node<E> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        return current.data;
    }

    public List<E> getAll() {
        List<E> resultList = new ArrayList<>();
        Node<E> current = head;
        while (current != null) {
            resultList.add(current.data);
            current = current.next;
        }
        return resultList;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }
}
