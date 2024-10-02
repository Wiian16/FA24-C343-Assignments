import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleHashProbeHashMapTest {
    @Test
    void testDoubleHash(){
        DoubleHashProbeHashMap<Integer, Integer> doubleHash = new DoubleHashProbeHashMap<>();

        doubleHash.put(1, 1);
        doubleHash.put(2, 2);
        doubleHash.put(3, 3);
        doubleHash.put(4, 4);
        doubleHash.put(5, 5);

        try {
            assertEquals(doubleHash.get(1), 1);
            assertEquals(doubleHash.get(2), 2);
            assertEquals(doubleHash.get(3), 3);
            assertEquals(doubleHash.get(4), 4);
            assertEquals(doubleHash.get(5), 5);

            doubleHash.put(1, 6);
            assertEquals(doubleHash.get(1), 6);

            doubleHash.remove(1);
            doubleHash.remove(2);
            doubleHash.remove(3);
            doubleHash.remove(4);
            doubleHash.remove(5);

            assertThrows(KeyNotFoundE.class, () -> doubleHash.get(1));

            for(int i = 0; i < 100; i++){
                doubleHash.put(i, i);
            }

            assertEquals(0, doubleHash.get(0));
            assertEquals(50, doubleHash.get(50));
            assertEquals(99, doubleHash.get(99));
            assertEquals(100, doubleHash.size());

            for(int i = 0; i < 50; i++){
                doubleHash.remove(i);
            }

            assertEquals(50, doubleHash.size());
            assertEquals(50, doubleHash.get(50));
            assertEquals(75, doubleHash.get(75));
            assertEquals(99, doubleHash.get(99));
            assertThrows(KeyNotFoundE.class, () -> doubleHash.get(0));
            assertThrows(KeyNotFoundE.class, () -> doubleHash.get(25));

            for(int i = 0; i < 50; i++){
                doubleHash.put(i, i + 100);
            }

            assertEquals(100, doubleHash.size());
            assertEquals(100, doubleHash.get(0));
            assertEquals(125, doubleHash.get(25));
            assertEquals(50, doubleHash.get(50));
            assertEquals(75, doubleHash.get(75));
            assertEquals(99, doubleHash.get(99));
        }
        catch(KeyNotFoundE e){
            e.printStackTrace();
            fail();
        }
    }

    //Running the test multiple times catches issues with the random aspect of hashing
    @Test
    void repeatTest(){
        for(int i = 0; i < 100; i++){
            testDoubleHash();
        }
    }

}