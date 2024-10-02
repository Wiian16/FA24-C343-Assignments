import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainHashMapTest {
    @Test
    void testChain(){
        ChainHashMap<Integer, Integer> chain = new ChainHashMap<>();

        chain.put(1, 1);
        chain.put(2, 2);
        chain.put(3, 3);
        chain.put(4, 4);
        chain.put(5, 5);

        try {
            assertEquals(chain.get(1), 1);
            assertEquals(chain.get(2), 2);
            assertEquals(chain.get(3), 3);
            assertEquals(chain.get(4), 4);
            assertEquals(chain.get(5), 5);

            chain.put(1, 6);
            assertEquals(chain.get(1), 6);

            chain.remove(1);
            chain.remove(2);
            chain.remove(3);
            chain.remove(4);
            chain.remove(5);

            assertThrows(KeyNotFoundE.class, () -> chain.get(1));

            for(int i = 0; i < 100; i++){
                chain.put(i, i);
            }

            assertEquals(0, chain.get(0));
            assertEquals(50, chain.get(50));
            assertEquals(99, chain.get(99));
            assertEquals(100, chain.size());

            for(int i = 0; i < 50; i++){
                chain.remove(i);
            }

            assertEquals(50, chain.size());
            assertEquals(50, chain.get(50));
            assertEquals(75, chain.get(75));
            assertEquals(99, chain.get(99));
            assertThrows(KeyNotFoundE.class, () -> chain.get(0));
            assertThrows(KeyNotFoundE.class, () -> chain.get(25));

            for(int i = 0; i < 50; i++){
                chain.put(i, i + 100);
            }

            assertEquals(100, chain.size());
            assertEquals(100, chain.get(0));
            assertEquals(125, chain.get(25));
            assertEquals(50, chain.get(50));
            assertEquals(75, chain.get(75));
            assertEquals(99, chain.get(99));
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
            testChain();
        }
    }

}