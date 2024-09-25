/**
 * This is a special case of the ProbeHashMap class where the probe sequence is linear.
 * It is fairy similar to code fragment 9.2.4 in the book.
 */

public class LinearProbeHashMap<K,V> extends ProbeHashMap<K,V> {
    LinearProbeHashMap() {
        super( (h,i) -> i+1);
    }

    LinearProbeHashMap(int cap) {
        super( (h,i) -> i+1, cap);
    }

    LinearProbeHashMap(int cap, int p) {
        super( (h,i) -> i+1, cap, p);
    }
}
