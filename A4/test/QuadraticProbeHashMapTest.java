import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuadraticProbeHashMapTest {
    @Test
    void testQuadratic(){
        QuadraticProbeHashMap<Integer, Integer> quadratic = new QuadraticProbeHashMap<>();

        quadratic.put(1, 1);
        quadratic.put(2, 2);
        quadratic.put(3, 3);
        quadratic.put(4, 4);
        quadratic.put(5, 5);

        try {
            assertEquals(quadratic.get(1), 1);
            assertEquals(quadratic.get(2), 2);
            assertEquals(quadratic.get(3), 3);
            assertEquals(quadratic.get(4), 4);
            assertEquals(quadratic.get(5), 5);

            quadratic.put(1, 6);
            assertEquals(quadratic.get(1), 6);

            quadratic.remove(1);
            quadratic.remove(2);
            quadratic.remove(3);
            quadratic.remove(4);
            quadratic.remove(5);

            assertThrows(KeyNotFoundE.class, () -> quadratic.get(1));

            for(int i = 0; i < 100; i++){
                quadratic.put(i, i);
            }

            assertEquals(0, quadratic.get(0));
            assertEquals(50, quadratic.get(50));
            assertEquals(99, quadratic.get(99));
            assertEquals(100, quadratic.size());

            for(int i = 0; i < 50; i++){
                quadratic.remove(i);
            }

            assertEquals(50, quadratic.size());
            assertEquals(50, quadratic.get(50));
            assertEquals(75, quadratic.get(75));
            assertEquals(99, quadratic.get(99));
            assertThrows(KeyNotFoundE.class, () -> quadratic.get(0));
            assertThrows(KeyNotFoundE.class, () -> quadratic.get(25));

            for(int i = 0; i < 50; i++){
                quadratic.put(i, i + 100);
            }

            assertEquals(100, quadratic.size());
            assertEquals(100, quadratic.get(0));
            assertEquals(125, quadratic.get(25));
            assertEquals(50, quadratic.get(50));
            assertEquals(75, quadratic.get(75));
            assertEquals(99, quadratic.get(99));
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
            testQuadratic();
        }
    }

}