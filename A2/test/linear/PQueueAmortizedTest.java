package linear;

import exceptions.EmptyQueueE;
import interfaces.QueueI;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PQueueAmortizedTest {

    @Test
    void testFunctionality() {

        @NotNull QueueI<Integer> queue = new PQueueAmortized<>();
        @NotNull QueueI<Integer> queue2 = new PQueueAmortized<>();
        assertTrue(queue.isEmpty());
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            assertEquals(i + 1, queue.size());
            try {
                assertEquals(0, queue.first());
            } catch (EmptyQueueE e) {
                fail();
            }
        }
        assertFalse(queue.isEmpty());
        assertTrue(queue2.isEmpty());
        assertEquals(10, queue.size());
        assertEquals(0, queue2.size());
        for (int i = 0; i < 10; i++) {
            try {
                assertEquals(i, queue.first());
                assertEquals(i, queue.dequeue());
            } catch (EmptyQueueE e) {
                fail();
            }
        }
        assertTrue(queue.isEmpty());

        queue = new PQueue<>();
        try {
            for(int i = 0; i < 1000; i++) {
                queue.enqueue(1);
                queue.dequeue();
            }
        }
        catch(EmptyQueueE e){
            fail();
        }
    }

    long timeEnqueue(@NotNull QueueI<Integer> queue, int n) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
        try {
            for (int i = 0; i < n; i++) {
                queue.dequeue();
            }
        }
        catch (EmptyQueueE e) {
            fail();
        }
        return System.currentTimeMillis() - start;
    }


    @Test
    void testSpeed () {
        @NotNull QueueI<Integer> slowQueue = new PQueue<>();
        @NotNull QueueI<Integer> fastQueue = new PQueueAmortized<>();
        for (int n = 8; n <= 20000; n *= 2) { //changed to smaller amount bc of stack overflow
            long slowTime = timeEnqueue(slowQueue, n);
            long fastTime = timeEnqueue(fastQueue, n);
            System.out.printf("n=%d: %n\tSlow time: %d%n\tFast time: %d%n", n, slowTime, fastTime);
            assertTrue(fastTime <= slowTime);
        }
    }
}

