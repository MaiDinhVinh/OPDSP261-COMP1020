package hashtable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class ChainingHashTable<K, V>{
    private LinkedList<HashTableNode<K, V>>[] items;
    private int size = 0;
    private final double loadFactor = 0.75;

    public void add(K key, V value){
        if(this.items == null) this.items = new LinkedList[9];
        int hash = key.hashCode() % this.items.length;
        if(this.items[hash] == null) this.items[hash] = new LinkedList<>();
        for(HashTableNode<K, V> node: this.items[hash]){
            if(node.getKey().equals(key)){
                throw new IllegalArgumentException("Duplicate Key");
            }
        }
        if(((double)this.size) / ((double)this.items.length) >= this.loadFactor){
            this.items = Arrays.copyOf(this.items, this.items.length*2);
        }
        this.items[hash].addFirst(new HashTableNode<>(key, value));
        this.size++;
    }

    public boolean update(K key, V value){
        boolean updated = false;
        for(LinkedList<HashTableNode<K, V>> ll: this.items){
            if(ll != null){
                for(HashTableNode<K, V> node: ll){
                    if(node.getKey() == key){
                        node.setValue(value);
                        updated = true;
                        break;
                    }
                }
            }
        }
        return updated;
    }

    public boolean remove(K key){
        boolean removed = false;
        for(LinkedList<HashTableNode<K, V>> ll: this.items){
            if(ll != null){
                for(HashTableNode<K, V> node: ll){
                    if(node.getKey() == key){
                        ll.remove(node);
                        removed = true;
                        break;
                    }
                }
            }
        }
        return removed;
    }

    public V find(K key){
        V value = null;
        for(LinkedList<HashTableNode<K, V>> ll: this.items){
            if(ll != null){
                for(HashTableNode<K, V> node: ll){
                    if(node.getKey() == key){
                        ll.remove(node);
                        value = node.getValue();
                        break;
                    }
                }
            }
        }
        return value;
    }
}