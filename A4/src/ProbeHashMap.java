import org.jetbrains.annotations.NotNull;

/**
 * An implementation of a hash map using an unspecified probing
 * strategy for collision resolution.
 * <p>
 * Subclasses must specify the probing strategy.
 * <p>
 * The difference between this class and code fragment 9.2.4 is that
 * we do no use any null values in the table. Also instead of using
 * DEFUNCT we use a second array to keep track of the status of each
 * bucket.
 */

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;

public abstract class ProbeHashMap<K,V> extends AbstractHashMap<K,V> {
    protected Optional<MapEntry<K,V>>[] table;
    protected Status[] status;
    protected BiFunction<Integer,Integer,Integer> secondary;

    public ProbeHashMap(BiFunction<Integer,Integer,Integer> secondary) {
        super();
        this.secondary = secondary;
    }
    public ProbeHashMap(BiFunction<Integer,Integer,Integer> secondary, int cap) {
        super(cap);
        this.secondary = secondary;
    }
    public ProbeHashMap(BiFunction<Integer,Integer,Integer> secondary, int cap, int p) {
        super(cap, p);
        this.secondary = secondary;
    }

    protected void setSecondary (BiFunction<Integer,Integer,Integer> secondary) {
        this.secondary = secondary;
    }

    @SuppressWarnings("unchecked")
    public void createTable() {
        table = (Optional<MapEntry<K,V>>[]) new Optional[capacity]; // safe cast
        status = new Status[capacity];
        for (int j = 0; j < capacity; j++) {
            table[j] = Optional.empty(); // an empty bucket
            status[j] = Status.FRESH;
        }
    }

    /**
     * Each of the methods that access the hash table start by going to the
     * initial bucket and then call a recursive helper method to handle the
     * probing strategy.
     */

    protected @NotNull V bucketGet (int h, K k) throws KeyNotFoundE {
        return bucketGetIter(h, k, 0);
    }

    protected void bucketPut(int h, K k, V v) {
        bucketPutIter(h, k, v, 0);
        n++;
    }

    protected void bucketRemove(int h, K k) throws KeyNotFoundE {
        bucketRemoveIter(h, k, 0);
        n--;
    }

    /**
     * To search for a value in the hash table, we start at the initial bucket
     * and then recursively follow the secondary clustering sequence until we
     * either find the target value or reach an empty bucket. If we reach an
     * empty bucket, we know that the target value is not in the table.
     * <p>
     * Care must be taken to distinguish between a bucket that is empty because
     * it has never been used and a bucket that is empty because it once held
     * an entry that has since been deleted. We use a second array to keep track
     * of the status of each bucket.
     */

    protected @NotNull V bucketGetIter(int h, K k, int iter) throws KeyNotFoundE {
        if(status[h] == Status.FRESH){
            throw new KeyNotFoundE();
        }

        if(table[h].isPresent() && table[h].get().getKey().equals(k)){
            return table[h].orElseThrow(KeyNotFoundE::new).getValue();
        }

        return bucketGetIter(mod(h + secondary.apply(h, iter), capacity), k, iter + 1);
    }

    /**
     * To insert a new entry into the hash table, we start at the initial bucket
     * and then recursively follow the secondary clustering sequence until we
     * either find an empty bucket or a bucket that contains an entry with the
     * target key. If we find an empty bucket, we insert the new entry. If we
     * find an entry with the target key, we replace the value of that entry with
     * the new value.
     */
    protected void bucketPutIter(int h, K k, V v, int iter) {
        if(table[h].isPresent() && table[h].get().getKey().equals(k)){
            table[h] = Optional.of(new MapEntry<>(k, v));
            return;
        }

        if(status[h] == Status.OCCUPIED){
            bucketPutIter(mod(h + secondary.apply(h, iter), capacity), k, v, iter + 1);
            return;
        }

        table[h] = Optional.of(new MapEntry<>(k, v));
        status[h] = Status.OCCUPIED;
    }

    /**
     * To remove an entry from the hash table, we start at the initial bucket
     * and then recursively follow the secondary clustering sequence until we
     * either find the target entry or reach an empty bucket. If we reach an
     * empty bucket, we know that the target entry is not in the table.
     */

    protected void bucketRemoveIter(int h, K k, int iter) throws KeyNotFoundE {
        if(status[h] == Status.FRESH){
            throw new KeyNotFoundE();
        }

        if(table[h].isPresent() && table[h].get().getKey().equals(k)){
            table[h] = Optional.empty();
            status[h] = Status.DELETED;
            return;
        }

        bucketRemoveIter(mod(h + secondary.apply(h, iter), capacity), k, iter + 1);
    }

    public @NotNull Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        for (int h=0; h < capacity; h++) table[h].ifPresent(buffer::add);
        return buffer;
    }

    private int mod(int a, int b){
        return (a % b + b) % b;
    }
}
