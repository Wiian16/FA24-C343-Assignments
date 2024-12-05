import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MaxHeapTest {

    //Uncomment and pass this for extra credit
    // @Test
    // public void testInsertions() {
    //     MaxHeap heap = new MaxHeap();

    //     // Insert elements into the heap
    //     heap.insert("A", 10);
    //     heap.insert("B", 20);
    //     heap.insert("C", 5);
    //     heap.insert("D", 25);
    //     heap.insert("E", 15);

    //     // Verify heap structure
    //     assertEquals("[D, E, C, B, A]", heap.getNodes().toString(), "Heap structure after insertions is incorrect.");
    // }

    // @Test
    // public void test2() {
    //     MaxHeap heap = new MaxHeap();

    //     // Insert elements into the heap
    //     heap.insert("X", 30);
    //     heap.insert("Y", 40);
    //     heap.insert("Z", 20);
    //     heap.insert("W", 50);



    //     assertEquals("[W, X, Z, Y]", heap.getNodes().toString(), "Heap structure after insertions is incorrect.");
    // }


   @Test
   public void testExtractMax() {
       MaxHeap heap = new MaxHeap();

       // Insert elements into the heap
       heap.insert("A", 10);
       heap.insert("B", 20);
       heap.insert("C", 5);
       heap.insert("D", 25);
       heap.insert("E", 15);

       // Extract and verify maximum elements
       assertEquals("D", heap.extractMax(), "First extracted max is incorrect.");
       assertEquals("[B, E, C, A]", heap.getNodes().toString(), "Heap structure after first extraction is incorrect.");

       assertEquals("B", heap.extractMax(), "Second extracted max is incorrect.");
       assertEquals("[E, A, C]", heap.getNodes().toString(), "Heap structure after second extraction is incorrect.");

       assertEquals("E", heap.extractMax(), "Third extracted max is incorrect.");
       assertEquals("[A, C]", heap.getNodes().toString(), "Heap structure after third extraction is incorrect.");

       assertEquals("A", heap.extractMax(), "Fourth extracted max is incorrect.");
       assertEquals("[C]", heap.getNodes().toString(), "Heap structure after fourth extraction is incorrect.");

       assertEquals("C", heap.extractMax(), "Fifth extracted max is incorrect.");
       assertEquals("[]", heap.getNodes().toString(), "Heap structure after fifth extraction is incorrect.");
   }

   @Test
   public void testExtractFromEmptyHeap() {
       MaxHeap heap = new MaxHeap();

       // Extract from an empty heap
       assertNull(heap.extractMax(), "Extracting from an empty heap should return null.");
       assertTrue(heap.getNodes().isEmpty(), "Heap should be empty after extracting from an empty heap.");
   }
}
