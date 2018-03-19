package DataStructures;

import java.util.LinkedList;

public class HashMap<K, V> {
    private LinkedList<Entry<K, V>>[] table; //HashMap with chaining. Array with LinkedLists holding all collisions
    private int numKeys; //Size of HashMap
    private static int CAPACITY = 101; //A prime number!
    private static double LOAD_THRESHOLD = 3.0; //Double size of HashMap when over threshold

    public HashMap() {
        table = new LinkedList[CAPACITY];
    }

    public V get(K key){
        int index = key.hashCode() % numKeys;
        index = index < 0 ? index + table.length : index;

        if(table[index] == null){
            return null;
        }

        for(Entry<K, V> entry : table[index]){
            if(entry.getKey().equals(key)){
                return entry.getValue();
            }
        }

        return null;
    }

    public V put(K key, V value){
        int index = key.hashCode() % table.length;
        index = index < 0 ? index + table.length : index;

        if(table[index] == null){
            table[index] = new LinkedList<>();
        }

        for(Entry<K, V> entry : table[index]){
            if(entry.getKey().equals(key)){
                V oldValue = entry.setValue(value);
                return oldValue;
            }
        }

        table[index].addFirst(new Entry(key, value));
        numKeys++;

        if(numKeys > (LOAD_THRESHOLD * table.length)){
            rehash();
        }

        return null;
    }

    public V remove(K key){
        int index = key.hashCode() % table.length;
        index = index < 0 ? index + table.length : index;

        if(table[index] == null){
            return null;
        }

        for(Entry<K, V> entry : table[index]){
            if(entry.getKey().equals(key)){
                V oldValue = entry.getValue();
                table[index].remove(key);

                if(table[index].size() == 0){
                    table[index] = null;
                }

                return oldValue;
            }
        }

        return null;
    }

    private void rehash(){
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[CAPACITY * 2];
        CAPACITY = CAPACITY * 2;

        for(LinkedList<Entry<K, V>> chain : table){
            if(chain == null) continue;
            for(Entry<K, V> entry : chain) {
                int index = entry.getKey().hashCode() % newTable.length;
                index = index < 0 ? index + newTable.length : index;

                if(newTable[index] == null){
                    newTable[index] = new LinkedList<>();
                }

                newTable[index].addFirst(entry);
            }
        }

        table = newTable;
    }

    public class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.value;
        }

        public V setValue(V value){
            V oldValue = value;
            this.value = value;
            return oldValue;
        }
    }
}
