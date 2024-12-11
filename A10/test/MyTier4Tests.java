import graph.noweight.Edge;
import graph.withweight.Weight;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.WeightedPath;
import graph.withweight.traversal.AllShortestPaths;
import graph.withweight.traversal.MinimumSpanningTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MyTier4Tests {
    // Helper method to create a simple weighted directed graph
    private WeightedDirectedGraph createTestGraph1() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();

        // Adding nodes and edges
        graph.insertEdge("A", "B", 10);
        graph.insertEdge("A", "C", 15);
        graph.insertEdge("B", "D", 20);
        graph.insertEdge("C", "D", 30);
        graph.insertEdge("D", "E", 25);
        graph.insertEdge("C", "E", 10);

        return graph;
    }

    private WeightedDirectedGraph createTestGraph2() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();

        // Adding nodes and edges
        graph.insertEdge("A", "B", 5);
        graph.insertEdge("A", "C", 7);
        graph.insertEdge("B", "D", 9);
        graph.insertEdge("C", "D", 4);
        graph.insertEdge("D", "E", 3);

        return graph;
    }

    private WeightedDirectedGraph createTestGraph3() {
        WeightedDirectedGraph graph = new WeightedDirectedGraph();

        // Adding nodes and edges
        graph.insertEdge("A", "B", 3);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("B", "D", 6);
        graph.insertEdge("C", "D", 1);
        graph.insertEdge("D", "E", 4);
        graph.insertEdge("B", "E", 7);

        return graph;
    }

    @Test
    public void mst1() {
        // Create the first graph for the test
        WeightedDirectedGraph g = createTestGraph1();

        // Create MinimumSpanningTree object with "A" as the source node
        MinimumSpanningTree mst = new MinimumSpanningTree(g, "A");

        // Perform the traversal to compute MST
        mst.iterativeTraversal();

        // Get the result map of previous nodes in the MST
        HashMap<String, String> result = mst.getPreviousNodes();

        // Verify the parent nodes in the MST
        assertEquals("A", result.get("B"));
        assertEquals("A", result.get("C"));
        assertEquals("B", result.get("D"));
        assertEquals("C", result.get("E"));

        // Verify the total weight of the MST
        assertEquals(55, mst.getWeight());
    }

    @Test
    public void mst2() {
        // Create the second graph for the test
        WeightedDirectedGraph g = createTestGraph2();

        // Create MinimumSpanningTree object with "A" as the source node
        MinimumSpanningTree mst = new MinimumSpanningTree(g, "A");

        // Perform the traversal to compute MST
        mst.iterativeTraversal();

        // Get the result map of previous nodes in the MST
        HashMap<String, String> result = mst.getPreviousNodes();

        // Verify the parent nodes in the MST
        assertEquals("A", result.get("B"));
        assertEquals("A", result.get("C"));
        assertEquals("C", result.get("D"));
        assertEquals("D", result.get("E"));

        // Verify the total weight of the MST
        assertEquals(19, mst.getWeight());
    }

    @Test
    public void mst3() {
        // Create the third graph for the test
        WeightedDirectedGraph g = createTestGraph3();

        // Create MinimumSpanningTree object with "A" as the source node
        MinimumSpanningTree mst = new MinimumSpanningTree(g, "A");

        // Perform the traversal to compute MST
        mst.iterativeTraversal();

        // Get the result map of previous nodes in the MST
        HashMap<String, String> result = mst.getPreviousNodes();

        // Verify the parent nodes in the MST
        assertEquals("A", result.get("B"));
        assertEquals("A", result.get("C"));
        assertEquals("C", result.get("D"));
        assertEquals("D", result.get("E"));

        // Verify the total weight of the MST
        assertEquals(10, mst.getWeight());
    }

    // Shortest Path tests

    private WeightedDirectedGraph graph;
    private AllShortestPaths shortestPaths;

    @BeforeEach
    void setUp() {
        graph = new WeightedDirectedGraph();

        graph.insertEdge("A", "B", 1);
        graph.insertEdge("A", "C", 4);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("B", "D", 6);
        graph.insertEdge("C", "D", 3);
        graph.insertEdge("D", "E", 1);
    }

    @Test
    void testShortestPathToSingleNode() {
        shortestPaths = new AllShortestPaths(graph, "A");
        shortestPaths.iterativeTraversal();
        WeightedPath path = shortestPaths.getPath("B");

        assertEquals(1, path.totalWeight().value(), "Shortest path weight from A to B should be 1");
        List<Edge> edges = path.edges();
        assertEquals(1, edges.size(), "Path from A to B should have 1 edge");
        assertEquals(new Edge("A", "B"), edges.getFirst(), "Edge should be from A to B");
    }

    @Test
    void testShortestPathToMultipleNodes() {
        shortestPaths = new AllShortestPaths(graph, "A");
        shortestPaths.iterativeTraversal();

        // Path from A to D
        WeightedPath pathToD = shortestPaths.getPath("D");
        assertEquals(6, pathToD.totalWeight().value(), "Shortest path weight from A to D should be 6");

        List<Edge> edgesToD = pathToD.edges();
        assertEquals(3, edgesToD.size(), "Path from A to D should have 3 edges");
        assertEquals(new Edge("A", "B"), edgesToD.get(0), "First edge should be from A to B");
        assertEquals(new Edge("B", "C"), edgesToD.get(1), "Second edge should be from B to C");
        assertEquals(new Edge("C", "D"), edgesToD.get(2), "Third edge should be from C to D");
    }

    @Test
    void testPathThroughMultipleEdges() {
        shortestPaths = new AllShortestPaths(graph, "A");
        shortestPaths.iterativeTraversal();
        WeightedPath pathToE = shortestPaths.getPath("E");

        assertEquals(7, pathToE.totalWeight().value(), "Shortest path weight from A to E should be 7");

        List<Edge> edgesToE = pathToE.edges();
        assertEquals(4, edgesToE.size(), "Path from A to E should have 4 edges");
        assertEquals(new Edge("A", "B"), edgesToE.get(0), "First edge should be from A to B");
        assertEquals(new Edge("B", "C"), edgesToE.get(1), "Second edge should be from B to C");
        assertEquals(new Edge("C", "D"), edgesToE.get(2), "Third edge should be from C to D");
        assertEquals(new Edge("D", "E"), edgesToE.get(3), "Fourth edge should be from D to E");
    }
}
