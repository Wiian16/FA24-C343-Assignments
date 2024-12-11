import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.noweight.traversal.iter.BFS;
import graph.noweight.traversal.iter.DFSiter;
import graph.withweight.Weight;
import graph.withweight.WeightedDirectedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MyTier3Tests {
    //Weighted Directed Graph tests
    @Test
    void testInsertEdge() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        Edge edge = new Edge("A", "B");
        Weight weight = new Weight(10);

        graph.insertEdge(edge, weight);

        assertEquals(Set.of(new Edge("A", "B")), graph.getAdjacencyLists().get("A"));
        assertEquals(weight, graph.getWeights().get(edge));
    }

    @Test
    void testCopy() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        Edge edge = new Edge("A", "B");
        Weight weight = new Weight(10);

        graph.insertEdge(edge, weight);

        WeightedDirectedGraph copy = graph.copy();

        assertNotSame(graph, copy);
        assertEquals(graph, copy);

        // Modify the copy and verify the original is unaffected
        copy.insertEdge(new Edge("B", "C"), new Weight(5));
        assertNotEquals(graph, copy);
    }

    @Test
    void testBidirectional() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();
        Edge edge = new Edge("A", "B");
        Weight weight = new Weight(10);

        graph.insertEdge(edge, weight);

        WeightedDirectedGraph bidirectionalGraph = graph.bidirectional();

        assertEquals(Set.of(new Edge("A", "B")), bidirectionalGraph.getAdjacencyLists().get("A"));
        assertEquals(Set.of(new Edge("B", "A")), bidirectionalGraph.getAdjacencyLists().get("B"));
        assertEquals(weight, bidirectionalGraph.getWeights().get(edge));
        assertEquals(weight, bidirectionalGraph.getWeights().get(edge.flip()));
    }

    //DFS Iter tests
    private DirectedGraph graph;

    @BeforeEach
    void setUp() {
        // Set up the directed graph for testing
        graph = new DirectedGraph();
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");
        graph.insertEdge("D", "E");
        graph.insertEdge("E", "F");
    }

    @Test
    void testSimpleTraversal() {
        DFSiter dfs = new DFSiter(graph, "A");
        dfs.iterativeTraversal();

        List<String> traversal = dfs.getTraversal();
        assertEquals(6, traversal.size());
        assertTrue(traversal.containsAll(List.of("A", "B", "C", "D", "E", "F")));
    }

    @Test
    void testTraversalFromDifferentStartNode() {
        DFSiter dfs = new DFSiter(graph, "D");
        dfs.iterativeTraversal();

        List<String> traversal = dfs.getTraversal();
        assertEquals(3, traversal.size());
        assertTrue(traversal.containsAll(List.of("D", "E", "F")));
    }

    @Test
    void testGraphWithCycle() {
        // Create a cycle: F -> A
        graph.insertEdge("F", "A");

        DFSiter dfs = new DFSiter(graph, "A");
        dfs.iterativeTraversal();

        List<String> traversal = dfs.getTraversal();
        assertEquals(6, traversal.size());
        assertTrue(traversal.containsAll(List.of("A", "B", "C", "D", "E", "F")));
    }

    @Test
    void testEmptyGraph() {
        DirectedGraph emptyGraph = new DirectedGraph();

        DFSiter dfs = new DFSiter(emptyGraph, "A");
        dfs.iterativeTraversal();

        List<String> traversal = dfs.getTraversal();
        assertTrue(traversal.isEmpty());
    }

    //BFS tests

    @Test
    void testSimpleBFSGraph(){
        // Create the graph
        DirectedGraph graph = new WeightedDirectedGraph();

        // Add edges and their capacities
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");

        BFS bfs = new BFS(graph, "A");
        bfs.iterativeTraversal();

        System.out.println(bfs.getTraversal());
        System.out.println(bfs.getPreviousNodes());
    }
}
