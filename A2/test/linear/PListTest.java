package linear;

import exceptions.EmptyListE;
import interfaces.PListI;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PListTest {
    @Test
    void test() {
        @NotNull PListI<Integer> list = new EmptyPList<>();
        @NotNull PListI<Integer> list2 = new EmptyPList<>();
        assertTrue(list.isEmpty());
        list.addFirst(100);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        for (int i = 0; i < 10; i++) {
            list = list.addFirst(i);
            list = list.addLast(i*10);
        }
        assertFalse(list.isEmpty());
        assertTrue(list2.isEmpty());
        assertEquals(20, list.size());
        assertEquals(0, list2.size());
        try {
            assertEquals(9, list.first());
            assertEquals(90, list.last());
            list = list.removeFirst();
            assertEquals(8, list.first());
            assertEquals(90, list.last());
            list = list.removeFirst();
            assertEquals(7, list.first());
            assertEquals(90, list.last());
            list.removeFirst();
            assertEquals(7, list.first());
            assertEquals(90, list.last());
            assertNotEquals(list, list2);
            list2 = list.removeFirst();
            assertNotEquals(list, list2);
            list = list.removeFirst();
            assertEquals(list, list2);
        } catch (EmptyListE e) {
            fail();
        }

        PListI<String> list3 = new EmptyPList<>();

        assertTrue(list3.isEmpty());
        assertEquals(0, list3.size());
        assertThrows(EmptyListE.class, list3::first);
        assertThrows(EmptyListE.class, list3::last);
        assertThrows(EmptyListE.class, list3::removeFirst);

        list3 = list3.addFirst("one");
        list3 = list3.addFirst("two");
        list3 = list3.addFirst("three");
        list3 = list3.addFirst("four");
        list3 = list3.addFirst("five");

        try{
            assertEquals(5, list3.size());
            assertEquals("five", list3.first());
            assertEquals("one", list3.last());

            for(int i = 0; i < 5; i++){
                list3 = list3.removeFirst();
            }

            assertTrue(list3.isEmpty());

            list3 = list3.addFirst("five");
            list3 = list3.addFirst("four");
            list3 = list3.addFirst("three");
            list3 = list3.addFirst("two");
            list3 = list3.addFirst("one");

            assertEquals(5, list3.size());
            assertEquals("one", list3.first());
            assertEquals("five", list3.last());
        }
        catch(EmptyListE e){
            fail();
        }
    }

}