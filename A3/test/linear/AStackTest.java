package linear;

import exceptions.EmptyStackE;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AStackTest {
    @Test
    void test () throws EmptyStackE {
        AStack<Integer> stack = new AStack<>(5);
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        assertFalse(stack.isEmpty());
        assertEquals(6, stack.size());
        assertEquals(6, stack.top());
        assertEquals(6, stack.pop());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertFalse(stack.isEmpty());
    }

}