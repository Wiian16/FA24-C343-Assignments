import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.noweight.Path;
import graph.noweight.traversal.iter.QueueCollection;
import graph.noweight.traversal.iter.StackCollection;
import graph.withweight.Weight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MyTier1Tests {
    //Queue Tests
    @Test
    void testQueueCollectionConstructor() {
        // Test the constructor
        QueueCollection queueCollection = new QueueCollection("start");
        assertFalse(queueCollection.isEmpty(), "Queue should not be empty after initialization.");
        assertEquals("start", queueCollection.get(), "The first element should be 'start'.");
    }

    @Test
    void testAddAndGetMethods() {
        // Test add and get methods
        QueueCollection queueCollection = new QueueCollection("start");

        // Add nodes
        queueCollection.add("node1");
        queueCollection.add("node2");

        // Get nodes and verify the order
        assertEquals("start", queueCollection.get(), "The first dequeued element should be 'start'.");
        assertEquals("node1", queueCollection.get(), "The second dequeued element should be 'node1'.");
        assertEquals("node2", queueCollection.get(), "The third dequeued element should be 'node2'.");
    }

    @Test
    void testIsEmptyMethod() {
        // Test isEmpty method
        QueueCollection queueCollection = new QueueCollection("start");

        // Remove the only element
        queueCollection.get();
        assertTrue(queueCollection.isEmpty(), "Queue should be empty after removing all elements.");

        // Add a new element and check again
        queueCollection.add("node1");
        assertFalse(queueCollection.isEmpty(), "Queue should not be empty after adding an element.");
    }

    @Test
    void testEmptyQueueException() {
        // Test exception when getting from an empty queue
        QueueCollection queueCollection = new QueueCollection("start");
        queueCollection.get(); // Remove the only element

        assertThrows(java.util.NoSuchElementException.class, queueCollection::get, "Getting from an empty queue should throw NoSuchElementException.");
    }

    //Stack Tests

    @Test
    void testConstructorInitializesStackWithStartNode() {
        StackCollection stackCollection = new StackCollection("start");
        assertFalse(stackCollection.isEmpty(), "Stack should not be empty after initialization.");
        assertEquals("start", stackCollection.get(), "The initial value should be 'start'.");
    }

    @Test
    void testAddAndGet() {
        StackCollection stackCollection = new StackCollection("start");

        // Add elements to the stack
        stackCollection.add("node1");
        stackCollection.add("node2");

        // Get elements from the stack (LIFO)
        assertEquals("node2", stackCollection.get(), "The last pushed element should be retrieved first.");
        assertEquals("node1", stackCollection.get(), "The next retrieved element should follow LIFO order.");
        assertEquals("start", stackCollection.get(), "The initial value should be the last retrieved element.");
    }

    @Test
    void testIsEmpty() {
        StackCollection stackCollection = new StackCollection("start");

        // Initially, the stack is not empty
        assertFalse(stackCollection.isEmpty(), "Stack should not be empty after initialization.");

        // Pop all elements
        stackCollection.get(); // Pops "start"
        assertTrue(stackCollection.isEmpty(), "Stack should be empty after all elements are removed.");
    }

    @Test
    void testThrowsExceptionWhenGetFromEmptyStack() {
        StackCollection stackCollection = new StackCollection("start");
        stackCollection.get(); // Pops "start", stack becomes empty

        // Expect exception when trying to pop from an empty stack
        assertThrows(java.util.EmptyStackException.class, stackCollection::get, "Getting from an empty stack should throw an exception.");
    }

    //Weight Tests

    @Test
    void testMinWithFiniteWeights() {
        Weight w1 = new Weight(10);
        Weight w2 = new Weight(20);

        // w1 < w2
        assertEquals(w1, Weight.min(w1, w2));
        // w2 > w1
        assertEquals(w1, Weight.min(w2, w1));
    }

    @Test
    void testMinWithEqualWeights() {
        Weight w1 = new Weight(15);
        Weight w2 = new Weight(15);

        // w1 == w2
        assertEquals(w1, Weight.min(w1, w2));
    }

    @Test
    void testMinWithInfinity() {
        Weight finiteWeight = new Weight(5);

        // Finite weight vs Positive infinity
        assertEquals(finiteWeight, Weight.min(finiteWeight, Weight.INFINITY));
        assertEquals(finiteWeight, Weight.min(Weight.INFINITY, finiteWeight));

        // Positive infinity vs Positive infinity
        assertEquals(Weight.INFINITY, Weight.min(Weight.INFINITY, Weight.INFINITY));
    }

    @Test
    void testMinWithZero() {
        Weight zero = Weight.ZERO;
        Weight positive = new Weight(10);

        // Zero is smaller than positive weight
        assertEquals(zero, Weight.min(zero, positive));
        assertEquals(zero, Weight.min(positive, zero));
    }

    @Test
    void testMinWithNegativeWeights() {
        Weight w1 = new Weight(-10);
        Weight w2 = new Weight(-5);

        // Negative weights
        assertEquals(w1, Weight.min(w1, w2));
        assertEquals(w1, Weight.min(w2, w1));
    }

    //Path Tests

    private Path path;
    private Edge edge1;
    private Edge edge2;
    private Edge edge3;

    @BeforeEach
    void setup() {
        path = new Path();
        edge1 = new Edge("A", "B");
        edge2 = new Edge("B", "C");
        edge3 = new Edge("C", "D");
    }

    @Test
    void testPathInitiallyEmpty() {
        assertTrue(path.isEmpty(), "Path should initially be empty");
    }

    @Test
    void testAddEdge() {
        path.add(edge1);
        assertFalse(path.isEmpty(), "Path should not be empty after adding an edge");
        assertEquals(1, path.edges().size(), "Path should contain one edge");
        assertEquals(edge1, path.edges().get(0), "Edge added should be the same as retrieved");
    }

    @Test
    void testReverse() {
        path.add(edge1);
        path.add(edge2);
        path.add(edge3);

        path.reverse();

        assertEquals(edge3, path.edges().get(0), "First edge after reversal should be the last edge added");
        assertEquals(edge2, path.edges().get(1), "Second edge after reversal should be the second-to-last edge added");
        assertEquals(edge1, path.edges().get(2), "Last edge after reversal should be the first edge added");
    }

    // Directed Graph Tests

    @Test
    void testInsertEdge() {
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("A", "B");

        assertTrue(graph.getAllNodes().contains("A"));
        assertTrue(graph.getAllNodes().contains("B"));
        assertEquals(1, graph.outgoingEdges("A").size());
        assertTrue(graph.outgoingEdges("A").contains(new Edge("A", "B")));
    }

    @Test
    void testRemoveEdge() {
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("A", "B");
        graph.removeEdge(new Edge("A", "B"));

        assertFalse(graph.getAllNodes().contains("A"));
        assertTrue(graph.outgoingEdges("A").isEmpty());
    }

    @Test
    void testCopy() {
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("A", "B");
        graph.insertEdge("B", "C");

        DirectedGraph copiedGraph = graph.copy();
        assertEquals(graph.getAllNodes(), copiedGraph.getAllNodes());
        assertEquals(graph.getAllEdges(), copiedGraph.getAllEdges());
    }

    @Test
    void testTranspose() {
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("A", "B");
        graph.insertEdge("B", "C");

        DirectedGraph transposed = graph.transpose();
        assertTrue(transposed.outgoingEdges("B").contains(new Edge("B", "A")));
        assertTrue(transposed.outgoingEdges("C").contains(new Edge("C", "B")));
        assertFalse(transposed.outgoingEdges("A").contains(new Edge("A", "B")));
    }

    @Test
    void testBidirectional() {
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("A", "B");

        DirectedGraph bidirectionalGraph = graph.bidirectional();
        assertTrue(bidirectionalGraph.outgoingEdges("A").contains(new Edge("A", "B")));
        assertTrue(bidirectionalGraph.outgoingEdges("B").contains(new Edge("B", "A")));
    }

    @Test
    void testNeighbors() {
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");

        Set<String> neighbors = graph.neighbors("A");
        assertTrue(neighbors.contains("B"));
        assertTrue(neighbors.contains("C"));
        assertEquals(2, neighbors.size());
    }

    @Test
    void testOutgoingEdgesForNonExistingNode() {
        DirectedGraph graph = new DirectedGraph();
        assertTrue(graph.outgoingEdges("Z").isEmpty());
    }

    @Test
    void testInsertEdgeCreatesNodes() {
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("X", "Y");

        assertTrue(graph.getAllNodes().contains("X"));
        assertTrue(graph.getAllNodes().contains("Y"));
    }

    @Test
    void testRemoveNonExistingEdgeThrowsError() {
        DirectedGraph graph = new DirectedGraph();
        assertThrows(Error.class, () -> graph.removeEdge(new Edge("X", "Y")));
    }
}
