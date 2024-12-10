import graph.noweight.DirectedGraph;
import graph.noweight.traversal.rec.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MyTier2Tests {
    //DFSrec Tests
    private DirectedGraph dfsGraph;
    private DFSrec dfs;

    @BeforeEach
    void setUp() {
        dfsGraph = new DirectedGraph();
        dfsGraph.insertEdge("A", "B");
        dfsGraph.insertEdge("A", "C");
        dfsGraph.insertEdge("B", "D");
        dfsGraph.insertEdge("C", "E");
        dfsGraph.insertEdge("D", "F");
        dfsGraph.insertEdge("E", "F");

        dfs = new DFSrec(dfsGraph);
    }

    @Test
    void testTraverseFromSource() {
        dfs.traverseFromSource("A");

        List<String> currentTraversal = dfs.getCurrentTraversal();

        assertEquals(Arrays.asList("A", "B", "D", "F", "C", "E"), currentTraversal,
                "DFS from source 'A' should visit nodes in the expected order.");
    }

    @Test
    void testTraverseFromAllSources() {
        dfsGraph.insertEdge("X", "Y");
        dfsGraph.insertEdge("Y", "Z");

        dfs = new DFSrec(dfsGraph);
        dfs.traverseFromAllSources();

        HashMap<String, List<String>> allTraversals = dfs.getAllTraversals();

        assertTrue(allTraversals.containsKey("A"), "Traversal should include component starting from 'A'.");
        assertTrue(allTraversals.containsKey("X"), "Traversal should include component starting from 'X'.");

        assertEquals(Arrays.asList("A", "B", "D", "F", "C", "E"), allTraversals.get("A"),
                "Traversal for component starting at 'A' should be correct.");
        assertEquals(Arrays.asList("X", "Y", "Z"), allTraversals.get("X"),
                "Traversal for component starting at 'X' should be correct.");
    }
    //Cycle Detection tests

    private DirectedGraph cycleGraph;

    @BeforeEach
    void setUp2() {
        cycleGraph = new DirectedGraph();
        // Example setup for DirectedGraph
        // Add edges to simulate a graph with and without cycles
        cycleGraph.insertEdge("A", "B");
        cycleGraph.insertEdge("B", "C");
        cycleGraph.insertEdge("C", "D");
        cycleGraph.insertEdge("D", "A"); // Cycle A -> B -> C -> D -> A
        cycleGraph.insertEdge("E", "F"); // No cycle in this subgraph
    }

    @Test
    void testHasCycleInGraphWithCycle() {
        CycleDetection cycleDetection = new CycleDetection(cycleGraph);
        cycleDetection.traverseFromSource("A");
        assertTrue(cycleDetection.hasCycle(), "Graph should contain a cycle.");
    }

    @Test
    void testNoCycleInGraphWithoutCycle() {
        // Create a graph without cycles
        DirectedGraph acyclicGraph = new DirectedGraph();
        acyclicGraph.insertEdge("A", "B");
        acyclicGraph.insertEdge("B", "C");
        acyclicGraph.insertEdge("C", "D");

        CycleDetection cycleDetection = new CycleDetection(acyclicGraph);
        cycleDetection.traverseFromSource("A");
        assertFalse(cycleDetection.hasCycle(), "Graph should not contain a cycle.");
    }

    @Test
    void testEmptyGraph() {
        DirectedGraph emptyGraph = new DirectedGraph();
        CycleDetection cycleDetection = new CycleDetection(emptyGraph);
        cycleDetection.traverseFromSource("A"); // Traversing a non-existent node
        assertFalse(cycleDetection.hasCycle(), "Empty graph should not contain a cycle.");
    }

    @Test
    void testDisjointGraph() {
        DirectedGraph disjointGraph = new DirectedGraph();
        disjointGraph.insertEdge("A", "B");
        disjointGraph.insertEdge("C", "D");

        CycleDetection cycleDetection = new CycleDetection(disjointGraph);
        cycleDetection.traverseFromSource("A");
        assertFalse(cycleDetection.hasCycle(), "Disjoint subgraph starting at A should not contain a cycle.");

        cycleDetection = new CycleDetection(disjointGraph);
        cycleDetection.traverseFromSource("C");
        assertFalse(cycleDetection.hasCycle(), "Disjoint subgraph starting at C should not contain a cycle.");
    }

    @Test
    void testGraphWithSelfLoop() {
        DirectedGraph selfLoopGraph = new DirectedGraph();
        selfLoopGraph.insertEdge("A", "A"); // Self-loop at A

        CycleDetection cycleDetection = new CycleDetection(selfLoopGraph);
        cycleDetection.traverseFromSource("A");
        assertTrue(cycleDetection.hasCycle(), "Graph with self-loop should contain a cycle.");
    }

    @Test
    void testGraphWithMultipleCycles() {
        DirectedGraph multiCycleGraph = new DirectedGraph();
        multiCycleGraph.insertEdge("A", "B");
        multiCycleGraph.insertEdge("B", "C");
        multiCycleGraph.insertEdge("C", "A"); // Cycle A -> B -> C -> A
        multiCycleGraph.insertEdge("D", "E");
        multiCycleGraph.insertEdge("E", "F");
        multiCycleGraph.insertEdge("F", "D"); // Cycle D -> E -> F -> D

        CycleDetection cycleDetection = new CycleDetection(multiCycleGraph);
        cycleDetection.traverseFromSource("A");
        assertTrue(cycleDetection.hasCycle(), "Graph with multiple cycles should detect a cycle.");

        cycleDetection = new CycleDetection(multiCycleGraph);
        cycleDetection.traverseFromSource("D");
        assertTrue(cycleDetection.hasCycle(), "Graph with multiple cycles should detect a cycle from a different start node.");
    }

    @Test
    void testGraphWithNestedCycle() {
        DirectedGraph nestedCycleGraph = new DirectedGraph();
        nestedCycleGraph.insertEdge("A", "B");
        nestedCycleGraph.insertEdge("B", "C");
        nestedCycleGraph.insertEdge("C", "A"); // Outer cycle A -> B -> C -> A
        nestedCycleGraph.insertEdge("B", "D");
        nestedCycleGraph.insertEdge("D", "E");
        nestedCycleGraph.insertEdge("E", "B"); // Inner cycle B -> D -> E -> B

        CycleDetection cycleDetection = new CycleDetection(nestedCycleGraph);
        cycleDetection.traverseFromSource("A");
        assertTrue(cycleDetection.hasCycle(), "Graph with nested cycles should detect a cycle.");
    }

    //Reachability Tests

    private DirectedGraph topoGraph;
    private Reachability reachability;

    @BeforeEach
    void setUp3() {
        topoGraph = new DirectedGraph();
        topoGraph.insertEdge("A", "B");
        topoGraph.insertEdge("B", "C");
        topoGraph.insertEdge("C", "A"); // Cycle
        topoGraph.insertEdge("D", "B");
        topoGraph.insertEdge("E", "F"); // Disconnected subgraph

        reachability = new Reachability(topoGraph);
        reachability.traverseFromAllSources();
    }

    @Test
    void testReachabilityForCycle() {
        Map<String, Set<String>> table = reachability.getTable();

        assertTrue(table.get("A").contains("B"), "Node A should include B in its reachability set (cycle)");
        assertTrue(table.get("A").contains("C"), "Node A should include C in its reachability set (cycle)");
        assertTrue(table.get("B").contains("A"), "Node B should include A in its reachability set (cycle)");
        assertTrue(table.get("C").contains("A"), "Node C should include A in its reachability set (cycle)");
    }

    @Test
    void testDisconnectedGraph() {
        Map<String, Set<String>> table = reachability.getTable();

        assertTrue(table.get("F").contains("E"), "Node F should include E in its reachability set");
        assertFalse(table.get("A").contains("E"), "Node A should not include E in its reachability set");
        assertFalse(table.get("E").contains("A"), "Node E should not include A in its reachability set");
    }

    @Test
    void testReachabilityWithBranching() {
        topoGraph.insertEdge("A", "D");
        topoGraph.insertEdge("D", "E");
        reachability = new Reachability(topoGraph);
        reachability.traverseFromAllSources();

        Map<String, Set<String>> table = reachability.getTable();

        assertTrue(table.get("E").contains("A"), "Node E should include A in its reachability set (via D)");
        assertTrue(table.get("E").contains("D"), "Node E should include D in its reachability set");
    }

    @Test
    void testEmptyReachabilityGraph() {
        DirectedGraph emptyGraph = new DirectedGraph();

        Reachability emptyReachability = new Reachability(emptyGraph);
        emptyReachability.traverseFromAllSources();

        Map<String, Set<String>> table = emptyReachability.getTable();

        assertTrue(table.isEmpty(), "Reachability table should be empty for an empty graph");
    }

    //Topological Sort Tests

    @Test
    void testTraverseFromSourceTopo() {
        // Create a directed acyclic graph (DAG)
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");

        // Initialize TopologicalSort
        TopologicalSort topoSort = new TopologicalSort(graph);

        // Perform traversal from a specific source
        topoSort.traverseFromSource("A");

        // Get the result of the traversal
        List<String> traversal = topoSort.getTraversal();

        // Assert that the result is a valid topological order
        assertTrue(isValidTopologicalOrder(traversal, graph));
    }

    @Test
    void testTraverseFromAllSourcesTopo() {
        // Create a directed acyclic graph (DAG)
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");
        graph.insertEdge("E", "F");

        // Initialize TopologicalSort
        TopologicalSort topoSort = new TopologicalSort(graph);

        // Perform traversal from all sources
        topoSort.traverseFromAllSources();

        // Get the result of the traversal
        List<String> traversal = topoSort.getTraversal();

        // Assert that the result is a valid topological order
        assertTrue(isValidTopologicalOrder(traversal, graph));
    }

    /**
     * Helper method to check if a given list is a valid topological ordering of the graph.
     */
    private boolean isValidTopologicalOrder(List<String> order, DirectedGraph graph) {
        // Create a map to store the position of each node in the topological order
        Map<String, Integer> position = new HashMap<>();
        for (int i = 0; i < order.size(); i++) {
            position.put(order.get(i), i);
        }

        // Check each edge (u, v) to ensure u appears before v in the order
        for (String u : graph.getAllNodes()) {
            for (String v : graph.neighbors(u)) {
                if (position.get(u) >= position.get(v)) {
                    return false; // u must appear before v
                }
            }
        }

        return true; // Valid topological order
    }

    @Test
    void testAllSourcesTopo2(){
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "C");
        graph.insertEdge("B", "E");
        graph.insertEdge("B", "D");
        graph.insertEdge("E", "B");
        graph.insertEdge("D", "E");

        TopologicalSort topologicalSort = new TopologicalSort(graph);
        topologicalSort.traverseFromAllSources();
        System.out.println("topologicalSort = " + topologicalSort.getTraversal());
        assertEquals(5, topologicalSort.getTraversal().size());
    }

    //SCC tests

    private DirectedGraph graph;

    @BeforeEach
    void setUp4() {
        // Initialize the graph for each test.
        graph = new DirectedGraph();
    }

    @Test
    void testEmptySCCGraph() {
        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> result = scc.computeSCC();
        assertTrue(result.isEmpty(), "The SCC result should be empty for an empty graph.");
    }

    @Test
    void testSimpleConnectedGraph() {
        DirectedGraph graph = new DirectedGraph();
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "C");
        graph.insertEdge("B", "E");
        graph.insertEdge("B", "D");
        graph.insertEdge("E", "B");
        graph.insertEdge("D", "E");

        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> sccMap = scc.computeSCC();

        assertEquals(List.of("A"), sccMap.get("A"));
        assertEquals(Set.of("B", "E", "D"), Set.copyOf(sccMap.get("B")));
        assertEquals(List.of("C"), sccMap.get("C"));
        assertEquals(List.of(), sccMap.get("D"));
        assertEquals(List.of(), sccMap.get("E"));
    }

    @Test
    void testSelfLoop() {
        graph.insertEdge("A", "A");

        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> result = scc.computeSCC();

        assertEquals(1, result.size(), "There should be 1 strongly connected component.");
        List<String> component = result.get("A");
        assertNotNull(component, "The component should not be null.");
        assertEquals(1, component.size(), "The component should contain just node 'A'.");
        assertTrue(component.contains("A"), "The component should contain node 'A'.");
    }
}
