package com.laba.solvd.bankhierarchy.customlinkedlist;

import java.util.*;
import java.util.function.Consumer;

public class CustomLinkedList<E> implements List<E> {
    private Node<E> head;
    private int size;

    public CustomLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomLinkedListIterator(head);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Method not supported ");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Method not supported ");
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        List.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return List.super.spliterator();
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (index == 0) {
            E removedData = head.data;
            head = head.next;
            size--;
            return removedData;
        }

        Node<E> current = head;
        Node<E> previous = null;
        int currentIndex = 0;

        while (currentIndex < index) {
            previous = current;
            current = current.next;
            currentIndex++;
        }

        E removedData = current.data;
        previous.next = current.next;
        size--;
        return removedData;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Method not supported ");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Method not supported ");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Method not supported ");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Method not supported ");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Method not supported ");
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node<E> newNode = new Node<>(element);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<E> current = head;
            Node<E> previous = null;
            int currentIndex = 0;

            while (currentIndex < index) {
                previous = current;
                current = current.next;
                currentIndex++;
            }

            newNode.next = current;
            previous.next = newNode;
        }

        size++;
    }

    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node<E> current = head;
        int currentIndex = 0;

        while (currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        E replacedData = current.data;
        current.data = element;
        return replacedData;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node<E> current = head;
        int currentIndex = 0;

        while (currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
        return current.data;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        Node<E> current = head;
        Node<E> previous = null;

        while (current != null) {
            if (c.contains(current.data)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                modified = true;
                size--;
            } else {
                previous = current;
            }
            current = current.next;
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not supported ");
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (c.isEmpty()) {
            return false;
        }

        Node<E> current = head;
        Node<E> previous = null;
        int currentIndex = 0;

        while (currentIndex < index && current != null) {
            previous = current;
            current = current.next;
            currentIndex++;
        }

        for (E element : c) {
            Node<E> newNode = new Node<>(element);

            if (previous == null) {
                newNode.next = head;
                head = newNode;
            } else {
                newNode.next = current;
                previous.next = newNode;
            }

            previous = newNode;
            size++;
        }
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Method not supported ");
    }

    public boolean remove(Object o) {
        Node<E> current = head;
        Node<E> previous = null;

        while (current != null) {
            if (o.equals(current.data)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public boolean add(E e) {
        add(size, e);
        return true;
    }

    public boolean contains(Object o) {
        Node<E> current = head;

        while (current != null) {
            if (o.equals(current.data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
