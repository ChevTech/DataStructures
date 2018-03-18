package DataStructures;

import DataStructures.Interfaces.Queue;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<E> implements Queue<E> {
    ArrayList<E> data;
    Comparator<E> comperator = null;

    public PriorityQueue(Comparator<E> comp){
        data = new ArrayList<E>();
        comperator = comp;
    }

    public boolean offer(E item){
        data.add(item);
        int child = data.size() - 1;
        int parent = (child - 1) / 2;

        while(parent >= 0 && compare(data.get(parent), data.get(child)) > 0){
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
        return true;
    }

    public E poll(){
        if(data.isEmpty()){
            return null;
        }

        E root = data.get(0);

        if(data.size() == 1){
            data.remove(0);
            return root;
        }

        data.set(0, data.remove(data.size()-1));
        int parent = 0;

        while(true){
            int leftChild = (parent * 2) + 1;
            int rightChild = (parent * 2) + 2;

            if(leftChild >= data.size()){
                break;
            }

            int minChild = leftChild;
            if(rightChild < data.size() && compare(data.get(leftChild), data.get(rightChild)) > 0){
                minChild = rightChild;
            }

            if(compare(data.get(parent), data.get(minChild)) > 0){
                swap(parent, minChild);
                parent = minChild;
            } else {
                break;
            }
        }

        return root;
    }

    public E remove(){
        if(data.isEmpty()){
            return null;
        }

        return data.remove();
    }

    public E peek() throws NoSuchElementException {
        if(data.isEmpty()){
            throw new NoSuchElementException();
        }

        return data.get(data.size() - 1);
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    @Override
    public String toString() {
        return data.toString();
    }

    private boolean swap(int parent, int child){
        E tmp = data.get(child);
        data.set(child, data.get(parent));
        data.set(parent, tmp);
        return true;
    }

    private int compare(E left, E right){
        if(comperator != null){
            return comperator.compare(left, right);
        } else {
            return ((Comparable<E>) left).compareTo(right);
        }
    }
}