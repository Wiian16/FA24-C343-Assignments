package linear;

import exceptions.EmptyDequeE;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ADequeTest {
    @Test
    void test () throws EmptyDequeE {
        ADeque<Integer> deque = new ADeque<>(5);
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        assertFalse(deque.isEmpty());
        assertEquals(5, deque.size());
        assertEquals(5, deque.first());
        assertEquals(1, deque.last());
        deque.removeFirst();
        assertEquals(4, deque.first());
        deque.removeFirst();
        assertEquals(3, deque.first());
        deque.removeFirst();
        assertEquals(2, deque.first());
        deque.removeFirst();
        assertEquals(1, deque.first());
        deque.removeFirst();
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        for (int i=0; i<100; i++)
            deque.addLast(i);
        assertEquals(100, deque.size());
        for (int i=0; i<50; i++)
            assertEquals(i, deque.removeFirst());
        for (int i=0; i<50; i++)
            assertEquals(99-i, deque.removeLast());
    }
}