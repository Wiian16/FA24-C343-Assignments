import graph.withweight.Weight;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.MinimumSpanningTree;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
