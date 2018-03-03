package com.chevtech;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class ArrayList<E> {
    private E[] data;
    private int size = 0;
    private int capacity = 0;

    public ArrayList(Integer initialCapacity) {
        capacity = initialCapacity;
        data = (E[]) new Object[capacity];
    }

    public boolean add(E value){
        if(size == capacity){
            reallocate();
        }

        data[size] = value;
        size++;
        return true;
    }

    public boolean add(Integer index, E value){
        if(size == capacity){
            reallocate();
        }

        for(int i=size; i > index; i--){
            data[i] = data[i-1];
        }
        data[index] = value;
        size++;
        return true;
    }

    public E get(Integer index) throws ArrayIndexOutOfBoundsException{
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return data[index];
    }

    public E set(Integer index, E value) throws ArrayIndexOutOfBoundsException {
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException(index);
        }

        E oldValue = data[index];
        data[index] = value;
        return oldValue;
    }

    public E remove(Integer index){
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException(index);
        }

        E oldValue = data[index];
        for(int i=index + 1; i < size; i++){
            data[i - 1] = data[i];
        }

        size--;
        return oldValue;
    }

    public int indexOf(E value){
        int index = -1;
        for(int i=0; i < size; i++){
            if(data[i].equals(value)){
                return i;
            }
        }

        return index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i < size; i++){
            sb.append(data[i]);

            if(i < size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    private void reallocate(){
        capacity = capacity * 2;
        E[] newData = (E[]) new Object[capacity];

        for(int i=0; i < size; i++){
            newData[i] = data[i];
        }

        data = newData;
    }
}
