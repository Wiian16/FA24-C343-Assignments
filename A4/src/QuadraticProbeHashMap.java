/**
 * This is a special case of the ProbeHashMap class where the probe sequence is quadratic.
 */

public class QuadraticProbeHashMap<K,V>  extends ProbeHashMap<K,V> {
    public QuadraticProbeHashMap() {
        super( (h,i) -> i * i);
    }

    public QuadraticProbeHashMap(int cap) {
        super( (h,i) -> i * i, cap);
    }

    public QuadraticProbeHashMap(int cap, int p) {
        super( (h,i) -> i * i, cap, p);
    }
}
