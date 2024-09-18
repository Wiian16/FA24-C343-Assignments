package linear;

import exceptions.EmptyQueueE;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AQueueTest {
    @Test
    void test () throws EmptyQueueE {
        AQueue<Integer> queue = new AQueue<>(5);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        assertFalse(queue.isEmpty());
        assertEquals(6, queue.size());
        assertEquals(1, queue.first());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertEquals(5, queue.dequeue());
        assertFalse(queue.isEmpty());
    }

}