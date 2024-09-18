package linear;

import exceptions.EmptyListE;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AListTest {
    @Test
    void test () throws EmptyListE {
        AList<Integer> list = new AList<>(5);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        assertFalse(list.isEmpty());
        assertEquals(6, list.size());
        assertEquals(6, list.first());
        assertEquals(1, list.last());
        list.removeFirst();
        assertEquals(5, list.first());
        list.removeFirst();
        assertEquals(4, list.first());
        list.removeFirst();
        assertEquals(3, list.first());
        list.removeFirst();
        assertEquals(2, list.first());
        list.removeFirst();
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(1, list.removeFirst());
        assertTrue(list.isEmpty());
        list.addLast(10);
        list.addLast(20);
        assertEquals(10, list.first());
    }

}