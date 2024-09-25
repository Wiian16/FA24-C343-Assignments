import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;

/*
 * Code fragment 9.2.2 from book
 * Modified to use exceptions instead of returning null
 */

public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {
    protected int n = 0;                 // number of entries in the dictionary
    protected int capacity;              // length of the table
    private final int prime;             // prime factor
    private final long scale;
    private final long shift;            // the shift and scaling factors

    public AbstractHashMap(int cap, int p) {
        prime = p;
        capacity = cap;
        Random rand = new Random();
        scale = rand.nextInt(prime-1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }
    public AbstractHashMap(int cap) { this(cap, 109345121); }  // default prime
    public AbstractHashMap() { this(17); }                     // default capacity

    // protected abstract methods to be implemented by subclasses
    protected abstract void createTable();
    protected abstract @NotNull V bucketGet(int h, K k) throws KeyNotFoundE;
    protected abstract void bucketPut(int h, K k, V v);
    protected abstract void bucketRemove(int h, K k) throws KeyNotFoundE;

    // private utilities
    private int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
    }

    private void resize(int newCap) {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for (Entry<K,V> e : entrySet())
            buffer.add(e);
        capacity = newCap;
        createTable();                     // based on updated capacity
        n = 0;                             // will be recomputed while reinserting entries
        for (Entry<K,V> e : buffer)
            put(e.getKey(), e.getValue());
    }

    // public methods
    public int size() { return n; }
    public @NotNull V get(@NotNull K key) throws KeyNotFoundE { return bucketGet(hashValue(key), key); }
    public void remove(K key) throws KeyNotFoundE { bucketRemove(hashValue(key), key); }
    public void put(@NotNull K key, @NotNull V value) {
        bucketPut(hashValue(key), key, value);
        if (n > capacity / 2) {          // keep load factor <= 0.5
            BigInteger nextP = BigInteger.valueOf(2L * capacity).nextProbablePrime();
            resize(nextP.intValue());
        }
    }
}
