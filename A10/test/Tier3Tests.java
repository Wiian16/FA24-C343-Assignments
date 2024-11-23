import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.noweight.traversal.iter.BFS;
import graph.noweight.traversal.iter.DFSiter;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.HeapCollection;
import graph.withweight.Weight;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TIER III TESTS
 * <p>
 * WeightedDirectedGraph
 * IterativeGraphTraversal
 * DFSiter
 * BFS
 * Heap
 */

public class Tier3Tests {

    @Test
    void weightedGraph1 () {
        WeightedDirectedGraph g = ExampleGraphs.g4();
        HashMap<String, Set<Edge>> originalAdjacencyLists = g.getAdjacencyLists();
        HashMap<Edge, Weight> originalWeights = g.getWeights();

        WeightedDirectedGraph gCopy = g.copy();
        HashMap<String, Set<Edge>> copyAdjacencyLists = gCopy.getAdjacencyLists();
        HashMap<Edge, Weight> copyWeights = gCopy.getWeights();

        assertNotSame(gCopy, g);
        assertEquals(gCopy, g);
        assertNotSame(copyAdjacencyLists, originalAdjacencyLists);
        assertEquals(copyAdjacencyLists, originalAdjacencyLists);
        assertNotSame(copyWeights, originalWeights);
        assertEquals(copyWeights, originalWeights);

        for (String node : g.getAllNodes()) {
            Set<Edge> originalEdges = g.outgoingEdges(node);
            Set<Edge> copyEdges = gCopy.outgoingEdges(node);
            assertNotSame(copyEdges, originalEdges);
            assertEquals(copyEdges, originalEdges);
        }

        for (Edge edge : originalWeights.keySet()) {
            Weight originalWeight = originalWeights.get(edge);
            Weight copyWeight = copyWeights.get(edge);
            assertEquals(copyWeight, originalWeight);
        }

        // Check that modifying the copy doesn't modify the original
        copyWeights.put(new Edge("A", "B"), new Weight(100));
        assertNotEquals(copyWeights, originalWeights);
        // Check that adding an edge to the copy doesn't add it to the original
        copyAdjacencyLists.get("A").add(new Edge("A", "D"));
        assertNotEquals(copyAdjacencyLists, originalAdjacencyLists);
    }

    @Test
    void weightedGraph2 () {
        WeightedDirectedGraph g = ExampleGraphs.g5();
        HashMap<String, Set<Edge>> originalAdjacencyLists = g.getAdjacencyLists();
        HashMap<Edge, Weight> originalWeights = g.getWeights();

        WeightedDirectedGraph gCopy = g.copy();
        HashMap<String, Set<Edge>> copyAdjacencyLists = gCopy.getAdjacencyLists();
        HashMap<Edge, Weight> copyWeights = gCopy.getWeights();

        assertNotSame(gCopy, g);
        assertEquals(gCopy, g);
        assertNotSame(copyAdjacencyLists, originalAdjacencyLists);
        assertEquals(copyAdjacencyLists, originalAdjacencyLists);
        assertNotSame(copyWeights, originalWeights);
        assertEquals(copyWeights, originalWeights);

        for (String node : g.getAllNodes()) {
            Set<Edge> originalEdges = g.outgoingEdges(node);
            Set<Edge> copyEdges = gCopy.outgoingEdges(node);
            assertNotSame(copyEdges, originalEdges);
            assertEquals(copyEdges, originalEdges);
        }

        for (Edge edge : originalWeights.keySet()) {
            Weight originalWeight = originalWeights.get(edge);
            Weight copyWeight = copyWeights.get(edge);
            assertEquals(copyWeight, originalWeight);
        }

        // Check that modifying the copy doesn't modify the original
        copyWeights.put(new Edge("A", "B"), new Weight(100));
        assertNotEquals(copyWeights, originalWeights);
        // Check that adding an edge to the copy doesn't add it to the original
        copyAdjacencyLists.get("A").add(new Edge("A", "D"));
        assertNotEquals(copyAdjacencyLists, originalAdjacencyLists);
    }

    @Test
    void weightedGraph3 () {
        WeightedDirectedGraph g = new WeightedDirectedGraph();
        g.insertEdge("A", "B", 10);
        g.insertEdge("A", "B", 10);
        assertEquals(1, g.getWeights().size());
        assertEquals(1, g.outgoingEdges("A").size());
        g.insertEdge("A", "B", 20);
        assertEquals(1, g.getWeights().size());
        assertEquals(1, g.outgoingEdges("A").size());
    }

    @Test
    void dfsIterative1 () {
        DirectedGraph g = ExampleGraphs.g0();
        DFSiter dfsIter = new DFSiter(g, "A");
        dfsIter.iterativeTraversal();
        List<String> tr = dfsIter.getTraversal();
        List<String> tr1 = List.of("A","B","D", "C");
        List<String> tr2 = List.of("A","C","D", "B");
        assertTrue(tr.equals(tr1) || tr.equals(tr2));
    }

    @Test
    void dfsIterative2 () {
        DirectedGraph g = ExampleGraphs.g1();
        DFSiter dfsIter = new DFSiter(g, "A");
        dfsIter.iterativeTraversal();
        List<String> tr = dfsIter.getTraversal();
        List<String> tr1 = List.of("A","B","D","E","C");
        List<String> tr2 = List.of("A","B","E","D","C");
        List<String> tr3 = List.of("A","C","B","D","E");
        List<String> tr4 = List.of("A","B","C","E","D");
        List<String> tr5 = List.of("A","C","B","E","D");
        assertTrue(tr.equals(tr1) || tr.equals(tr2) || tr.equals(tr3) || tr.equals(tr4) || tr.equals(tr5));
    }

    @Test
    void bfs1 () {
        DirectedGraph g = ExampleGraphs.g0();
        BFS bfs = new BFS(g, "A");
        bfs.iterativeTraversal();
        List<String> tr = bfs.getTraversal();
        List<String> tr1 = List.of("A","B","C", "D");
        List<String> tr2 = List.of("A","C","B", "D");
        assertTrue(tr.equals(tr1) || tr.equals(tr2));
    }

    @Test
    void bfs2 () {
        DirectedGraph g = ExampleGraphs.g1();
        BFS bfs = new BFS(g, "A");
        bfs.iterativeTraversal();
        List<String> tr = bfs.getTraversal();
        List<String> tr1 = List.of("A","B","C","E","D");
        List<String> tr2 = List.of("A","B","C","D","E");
        List<String> tr3 = List.of("A","C","B","E","D");
        List<String> tr4 = List.of("A","C","B","D","E");
        assertTrue(tr.equals(tr1) || tr.equals(tr2) || tr.equals(tr3) || tr.equals(tr4));
    }

    @Test
    void heap1 () {
        HeapCollection heap = new HeapCollection(Set.of("A", "B", "C", "D", "E"));
        heap.setWeight("A", new Weight(4));
        heap.setWeight("B", new Weight(3));
        heap.setWeight("C", new Weight(2));
        assertEquals("C", heap.extractMin());
        heap.setWeight("D", new Weight(1));
        heap.setWeight("E", new Weight(5));
        assertEquals("D", heap.extractMin());
        assertEquals("B", heap.extractMin());
        assertEquals("A", heap.extractMin());
        heap.insert("X", new Weight(3));
        heap.insert("Y", new Weight(2));
        heap.setWeight("E", new Weight(0));
        assertEquals("E", heap.extractMin());
        assertEquals("Y", heap.extractMin());
        assertEquals("X", heap.extractMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    void heap2 () {
        HeapCollection heap = new HeapCollection(Set.of("A", "B", "C", "D", "E", "F", "G"));
        heap.setWeight("A", new Weight(62));
        heap.setWeight("B", new Weight(100));
        heap.setWeight("C", new Weight(15));
        heap.setWeight("D", new Weight(12));
        heap.setWeight("E", new Weight(53));
        heap.setWeight("F", new Weight(51));
        heap.setWeight("G", new Weight(20));
        assertEquals("D", heap.extractMin());
        assertEquals("C", heap.extractMin());
        assertEquals("G", heap.extractMin());
        assertEquals("F", heap.extractMin());
        assertEquals("E", heap.extractMin());
        assertEquals("A", heap.extractMin());
        assertEquals("B", heap.extractMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    void heap3 () {
        HeapCollection heap = new HeapCollection(Set.of("A", "B", "C", "D", "E"));
        heap.setWeight("A", new Weight(-1034));
        heap.setWeight("B", new Weight(0));
        heap.setWeight("C", new Weight(134));
        heap.setWeight("D", new Weight(110000));
        heap.setWeight("E", new Weight(0));
        assertEquals("A", heap.extractMin());
        String tieBreaker = heap.extractMin();
        assertTrue("E".equals(tieBreaker) || "B".equals(tieBreaker));
        tieBreaker = heap.extractMin();
        assertTrue("E".equals(tieBreaker) || "B".equals(tieBreaker));
        assertEquals("C", heap.extractMin());
        assertEquals("D", heap.extractMin());
        assertTrue(heap.isEmpty());
    }
}
