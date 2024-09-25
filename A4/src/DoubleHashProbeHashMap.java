/**
 * This is a special case of ProbeHashMap where the secondary hash function is
 * i * (q - (h % q)) where q is a prime number.
 */


public class DoubleHashProbeHashMap<K,V> extends ProbeHashMap<K,V> {
    private final int q = 109345121; // prime number
   DoubleHashProbeHashMap() {
       super( (h,i) -> { throw new Error("Not implemented"); } );
       setSecondary( (h,i) -> i * (q - (h % q)) );
   }

    DoubleHashProbeHashMap(int cap) {
        super( (h,i) -> { throw new Error("Not implemented"); }, cap );
        setSecondary( (h,i) -> i * (q - (h % q)) );
    }

    DoubleHashProbeHashMap(int cap, int p) {
        super( (h,i) -> { throw new Error("Not implemented"); }, cap, p );
        setSecondary( (h,i) -> i * (q - (h % q)) );
    }
}
