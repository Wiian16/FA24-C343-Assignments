package linear;

import exceptions.EmptyQueueE;
import interfaces.QueueI;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PQueueTest {

    @Test
    void test() {

        @NotNull QueueI<Integer> queue = new PQueue<>();
        @NotNull QueueI<Integer> queue2 = new PQueue<>();
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
    }
}

