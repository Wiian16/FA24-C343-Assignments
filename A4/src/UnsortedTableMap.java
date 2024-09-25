import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

/*
 * Code fragment 9.1.4 from book
 * Modified to use exceptions instead of returning null
 */

public class UnsortedTableMap<K,V> extends AbstractMap<K,V> {
    /** Underlying storage for the map of entries. */
    private final ArrayList<MapEntry<K,V>> table = new ArrayList<>();

    /** Constructs an initially empty map. */
    public UnsortedTableMap() { }

    // -------------------- private utility --------------------
    /**
     * Original code returns the index of an entry with equal key, or -1 if none found.
     * We throw an exception if the key is not found.
     */
    private int findIndex(K key) throws KeyNotFoundE {
        int n = table.size();
        for (int j=0; j < n; j++)
            if (table.get(j).getKey().equals(key))
                return j;
        throw new KeyNotFoundE();
    }

    // -------------------- public methods --------------------
    /** Returns the number of entries in the map. */
    public int size() { return table.size(); }

    /**
     * Original code returns the value associated with the specified key (or else null).
     * We throw an exception instead.
     */
    public @NotNull V get(@NotNull K key) throws KeyNotFoundE {
        int j = findIndex(key);
        return table.get(j).getValue();
    }

    /**
     * Associates given value with given key, replacing a previous value (if any).
     */
    public void put(@NotNull K key, @NotNull V value) {
        try {
            int j = findIndex(key);
            table.get(j).setValue(value);
        }
        catch (KeyNotFoundE e) {
            table.add(new MapEntry<>(key, value));   // add new entry
        }
    }

    /**
     * Removes the entry with the specified key (if any) and returns its value.
     * If the entry didn't exit we throw an exception
     */
    public void remove(K key) throws KeyNotFoundE {
        int j = findIndex(key);
        int n = size();
        if (j != n - 1)
            table.set(j, table.get(n-1));            // relocate last entry to 'hole' created by removal
        table.remove(n-1);                     // remove last entry of table
    }

    // --------- support for public entrySet() iterator ---------
    private class EntryIterator implements Iterator<Entry<K,V>> {
        private int j=0;
        public boolean hasNext() { return j < table.size(); }
        public Entry<K,V> next() {
            if (j == table.size()) throw new NoSuchElementException("No further entries");
            return table.get(j++);
        }
        public void remove() { throw new UnsupportedOperationException("remove not supported"); }
    }

    private class EntryIterable implements Iterable<Entry<K,V>> {
        public @NotNull Iterator<Entry<K,V>> iterator() { return new EntryIterator(); }
    }

    /** Returns an iterable collection of all key-value entries of the map. */
    public @NotNull Iterable<Entry<K,V>> entrySet() { return new EntryIterable(); }
}
