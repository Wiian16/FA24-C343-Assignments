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

        //todo: write test cases

        StackI<String> stack3 = new PStack<>();

        assertTrue(stack3.isEmpty());
        assertEquals(0, stack3.size());
        assertThrows(EmptyStackE.class, stack3::pop);
        assertThrows(EmptyStackE.class, stack3::top);

        for(int i = 1; i <= 5; i++){
            stack3.push("a".repeat(i));
        }

        try{
            assertEquals("aaaaa", stack3.top());
            assertFalse(stack3.isEmpty());
            assertEquals(5, stack3.size());

            StackI<String> stack4 = new PStack<>();

            while(!stack3.isEmpty()){
                stack4.push(stack3.pop());
            }

            assertFalse(stack4.isEmpty());
            assertEquals(5, stack4.size());
            assertEquals("a", stack4.top());
        }
        catch(EmptyStackE e){
            fail();
        }
    }
}