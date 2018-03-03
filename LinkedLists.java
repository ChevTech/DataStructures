package com.chevtech;

public class LinkedLists<E> {
    Node<E> head = null;
    private int size = 0;

    public E remove(int i) throws ArrayIndexOutOfBoundsException {
        if(i < 0 || i >= size){
            throw new ArrayIndexOutOfBoundsException(i);
        }

        if(i == 0) {
            return removeFirst();
        } else {
            Node<E> node = getNode(i - 1);
            return removeAfter(node);
        }
    }

    public E get(int index) throws ArrayIndexOutOfBoundsException {
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return getNode(index).data;
    }

    public E set(int index, E newVal) throws ArrayIndexOutOfBoundsException {
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException(index);
        }

        Node<E> node = getNode(index);
        E oldValue = node.data;
        node.data = newVal;
        return oldValue;
    }

    public boolean add(E val) {
        add(size, val);
        return true;
    }

    public void add(int index, E val) throws ArrayIndexOutOfBoundsException {
        if(index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        if(index == 0){
            addFirst(val);
        } else {
            Node<E> curr = getNode(index - 1);
            addAfter(curr, val);
        }
    }

    public int indexOf(E target){
        Node<E> ptr = head;
        int index = 0;

        while(ptr != null){
            if(ptr.data.equals(target)){
                return index;
            }

            index++;
            ptr = ptr.next;
        }

        return -1;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> ptr = head;
        while(ptr != null){
            sb.append(ptr.data);
            if(ptr.next != null) {
                sb.append(", ");
            }

            ptr = ptr.next;
        }
        sb.append("]");

        return sb.toString();
    }

    private Node<E> getNode(int i){
        Node<E> headPtr = head;
        int index = 0;
        while(headPtr != null && i != index){
            headPtr = headPtr.next;
            index++;
        }

        return headPtr;
    }

    private void addFirst(E val){
        head = new Node<E>(val, head);
        size++;
    }

    private void addAfter(Node<E> node, E val){
        Node<E> tmp = node.next;
        node.next = new Node<E>(val, tmp);
        size++;
    }

    private E removeAfter(Node<E> node){
        Node<E> tmp = node.next;
        if(node != null) {
            node.next = node.next.next;
            size--;
            return tmp.data;
        } else {
            return null;
        }
    }

    private E removeFirst(){
        Node<E> tmp = head;
        if(head != null){
            head = head.next;
            size--;
        }

        if(tmp != null) {
            return tmp.data;
        } else {
            return null;
        }
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E value){
            data = value;
            next = null;
        }

        private Node(E value, Node<E> nextNode){
            data = value;
            next = nextNode;
        }
    }
}
