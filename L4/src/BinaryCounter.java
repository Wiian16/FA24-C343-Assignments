import java.util.ArrayList;
import java.util.Arrays;

public class BinaryCounter {
    private ArrayList<Boolean> bits;
    private int len;
    private int bitsFlipped;

    BinaryCounter(int len) {
        this.bits = new ArrayList<>();
        this.len = len;
        for (int i = 0; i < len; i++) bits.add(false);
        this.bitsFlipped = 0;
    }

    int bitsFlipped() { return bitsFlipped; }

    /*
    Since each bit flipped gets flipped half as often as the one preceding it, the total number of flips works out to 2n
     */
    void increment(int i) {
        // Implement the logic for incrementing the binary counter.
        if(i >= len){
            return;
        }

        bitsFlipped++;

        if(!bits.get(i)){
           bits.set(i, true);
           return;
        }

        bits.set(i, false);
        increment(i + 1);
    }

    public static void main(String[] args) {
        BinaryCounter counter = new BinaryCounter(8);
        for (int i = 0; i < 16; i++) {
            counter.increment(0);
            System.out.println(counter.bitsFlipped());
        }
    }
}