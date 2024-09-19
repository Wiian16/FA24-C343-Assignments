import java.util.ArrayList;

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

    // TODO: Complete this method.
    void increment(int i) {
        // Implement the logic for incrementing the binary counter.
    }

    public static void main(String[] args) {
        BinaryCounter counter = new BinaryCounter(8);
        for (int i = 0; i < 16; i++) {
            counter.increment(0);
            System.out.println(counter.bitsFlipped());
        }
    }
}