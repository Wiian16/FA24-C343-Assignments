import org.jetbrains.annotations.NotNull;

import java.util.Map.Entry;

/*
 * Code fragment 9.1.1 from book
 * Modified to use exceptions instead of returning null
 */

public interface Map<K,V> {
    int size();
    boolean isEmpty();
    @NotNull V get(@NotNull K key) throws KeyNotFoundE;
    void put(@NotNull K key, @NotNull V value);
    void remove(K key) throws KeyNotFoundE;
    @NotNull Iterable<K> keySet();
    @NotNull Iterable<V> values();
    @NotNull Iterable<Entry<K,V>> entrySet();
}
