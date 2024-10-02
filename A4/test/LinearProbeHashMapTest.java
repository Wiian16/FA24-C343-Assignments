import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearProbeHashMapTest {
    @Test
    void testLinear() {
        LinearProbeHashMap<Integer, Integer> linear = new LinearProbeHashMap<>();

        linear.put(1, 1);
        linear.put(2, 2);
        linear.put(3, 3);
        linear.put(4, 4);
        linear.put(5, 5);

        try {
            assertEquals(linear.get(1), 1);
            assertEquals(linear.get(2), 2);
            assertEquals(linear.get(3), 3);
            assertEquals(linear.get(4), 4);
            assertEquals(linear.get(5), 5);

            linear.put(1, 6);
            assertEquals(linear.get(1), 6);

            linear.remove(1);
            linear.remove(2);
            linear.remove(3);
            linear.remove(4);
            linear.remove(5);

            assertThrows(KeyNotFoundE.class, () -> linear.get(1));

            for(int i = 0; i < 100; i++) {
                linear.put(i, i);
            }

            assertEquals(0, linear.get(0));
            assertEquals(50, linear.get(50));
            assertEquals(99, linear.get(99));
            assertEquals(100, linear.size());

            for(int i = 0; i < 50; i++) {
                linear.remove(i);
            }

            assertEquals(50, linear.size());
            assertEquals(50, linear.get(50));
            assertEquals(75, linear.get(75));
            assertEquals(99, linear.get(99));
            assertThrows(KeyNotFoundE.class, () -> linear.get(0));
            assertThrows(KeyNotFoundE.class, () -> linear.get(25));

            for(int i = 0; i < 50; i++) {
                linear.put(i, i + 100);
            }

            assertEquals(100, linear.size());
            assertEquals(100, linear.get(0));
            assertEquals(125, linear.get(25));
            assertEquals(50, linear.get(50));
            assertEquals(75, linear.get(75));
            assertEquals(99, linear.get(99));
        }
        catch(KeyNotFoundE e) {
            e.printStackTrace();
            fail();
        }
    }

    //Running the test multiple times catches issues with the random aspect of hashing
    @Test
    void repeatTest() {
        for(int i = 0; i < 100; i++) {
            testLinear();
        }
    }
}