import java.util.ArrayList;

public class MyHashMap<K, V> {

    private ArrayList<K> keys=new ArrayList<>();
    private ArrayList<V> values=new ArrayList<>(); 

    public void put(K key, V value) {
        int index= getTargetKeyIndex(key);
        if(index==-1){
            keys.add(key);
            values.add(value);
        }
        else{
            values.remove(index);
            values.add(index, value);
        }
    }

    public V get(K key) {
        int index=getTargetKeyIndex(key);
        if(index==-1) return null;
        else return values.get(index);
    }

    public void remove(K key) {
        int index=getTargetKeyIndex(key);
        if(index==-1) return ;
        keys.remove(index);
        values.remove(index);
    }

    private int getTargetKeyIndex(K key){
        for (K k : keys) 
            if(k.equals(key))
                return keys.indexOf(key); 
        return -1;
    }
}