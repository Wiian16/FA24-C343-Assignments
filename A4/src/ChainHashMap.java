import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * This is fairly similar to code fragment 9.2.3 in the book but avoiding
 * the use of null
 */

public class ChainHashMap<K,V> extends AbstractHashMap<K,V> {
    private UnsortedTableMap<K,V>[] table;

    public ChainHashMap() { super(); }
    public ChainHashMap(int cap) { super(cap); }
    public ChainHashMap(int cap, int p) { super(cap, p); }

    @SuppressWarnings("unchecked")
    protected void createTable() {
        table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
        for (int i = 0; i < capacity; i++)
            table[i] = new UnsortedTableMap<>();
    }

    protected @NotNull V bucketGet(int h, K k) throws KeyNotFoundE {
        // TODO
        return null;
    }

    protected void bucketPut(int h, K k, V v) {
        // TODO
    }

    protected void bucketRemove(int h, K k) throws KeyNotFoundE {
        // TODO
    }

    public @NotNull Iterable<Entry<K, V>> entrySet() {
        // collect entries
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++)
            for (Entry<K,V> entry : table[h].entrySet())
                buffer.add(entry);
        return buffer;
    }
}
