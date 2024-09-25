import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map.Entry;

/*
 * Code fragment 9.1.3 from book
 * Modified to use exceptions instead of returning null
 */

public abstract class AbstractMap<K,V> implements Map<K,V> {

    //---------------- nested MapEntry class ----------------
    protected static class MapEntry<K,V> implements Entry<K,V> {
        private @NotNull K k;  // key
        private @NotNull V v;  // value

        public MapEntry(@NotNull K key, @NotNull V value) {
            k = key;
            v = value;
        }

        // public methods of the Entry interface
        public @NotNull K getKey() { return k; }
        public @NotNull V getValue() { return v; }
        public @NotNull V setValue(@NotNull V value) {
            V old = v;
            v = value;
            return old;
        }

        // utilities not exposed as part of the Entry interface
        protected void setKey(@NotNull K key) { k = key; }

    } //----------- end of nested MapEntry class -----------

    // Tests whether the map is empty
    public boolean isEmpty() { return size() == 0; } // presumes implementation of size()

    // Support for public keySet() Method
    private class KeyIterator implements Iterator<K> {
        private final Iterator<Entry<K,V>> entries = entrySet().iterator();   // reuse entrySet
        public boolean hasNext() { return entries.hasNext(); }
        public K next() { return entries.next().getKey(); }                   // return key!
        public void remove() { entries.remove(); }
    }

    private class KeyIterable implements Iterable<K> {
        public @NotNull Iterator<K> iterator() { return new KeyIterator(); }
    }

    public @NotNull Iterable<K> keySet() { return new KeyIterable(); }

    // Support for public values() method
    private class ValueIterator implements Iterator<V> {
        private final Iterator<Entry<K,V>> entries = entrySet().iterator();   // reuse entrySet
        public boolean hasNext() { return entries.hasNext(); }
        public V next() { return entries.next().getValue(); }                 // return value!
        public void remove() { entries.remove(); }
    }

    private class ValueIterable implements Iterable<V> {
        public @NotNull Iterator<V> iterator() { return new ValueIterator(); }
    }

    public @NotNull Iterable<V> values() { return new ValueIterable(); }
}

