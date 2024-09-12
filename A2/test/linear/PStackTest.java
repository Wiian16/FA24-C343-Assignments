package linear;

import exceptions.EmptyStackE;
import interfaces.StackI;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PStackTest {

    @Test
    void test() {

        @NotNull StackI<Integer> stack = new PStack<>();
        @NotNull StackI<Integer> stack2 = new PStack<>();
        assertTrue(stack.isEmpty());
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            assertEquals(i + 1, stack.size());
            try {
                assertEquals(i, stack.top());
            } catch (EmptyStackE e) {
                fail();
            }
        }
        assertFalse(stack.isEmpty());
        assertTrue(stack2.isEmpty());
        assertEquals(10, stack.size());
        assertEquals(0, stack2.size());
        for (int i = 9; i >= 0; i--) {
            try {
                assertEquals(i, stack.top());
                assertEquals(i, stack.pop());
            } catch (EmptyStackE e) {
                fail();
            }
        }
        assertTrue(stack.isEmpty());
    }
}