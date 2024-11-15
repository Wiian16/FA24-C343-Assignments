import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TODOTests {
    // The current test cases are fragile, testing the string representation of
    // the output of the algorithms. This is not a good practice because the
    // output of the algorithms may change slightly and the tests will fail.
    // In particular, the output of the algorithms may change due to changes in
    // the order in which the nodes are visited.
    //
    // 1. Consider improving the existing test cases especially if your code returns
    // the correct results but in a slightly different order.
    //
    // 2. Create two new graphs of at least 10 nodes and 15 edges each and
    // Feel free to share the graphs on discord for others to use in their test cases
    //
    // 3. Using new graphs, write one additional test case for each algorithm

    //Directed Graph Tests

    @Test
    void testAddEdge() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");

        Set<String> expectedSet = Set.of("A", "B", "C", "D");
        assertEquals(expectedSet, graph.getNodes());

        Map<String, Set<Edge>> expectedMap = Map.of("A", Set.of(new Edge("A", "B")),
                "B", Set.of(new Edge("B", "C")),
                "C", Set.of(new Edge("C", "D")),
                "D", Set.of());
        assertEquals(expectedMap, graph.getAdjacencyLists());
    }

    @Test
    void testOutgoingEdges () {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "A");
        graph.addEdge("B", "C");
        graph.addEdge("C", "C");

        Set<Edge> expected;

        expected = Set.of(new Edge("A", "B"), new Edge("A", "C"),
                new Edge("A", "D"));
        assertEquals(expected, graph.outgoingEdges("A"));

        expected = Set.of(new Edge("B", "C"), new Edge ("B", "A"));
        assertEquals(expected, graph.outgoingEdges("B"));

        expected = Set.of(new Edge("C", "C"));
        assertEquals(expected, graph.outgoingEdges("C"));

        expected = Set.of();
        assertEquals(expected, graph.outgoingEdges("D"));
    }

    @Test
    void testNeighbors () {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "A");
        graph.addEdge("B", "C");
        graph.addEdge("C", "C");

        Set<String> expected;

        expected = Set.of("B", "C", "D");
        assertEquals(expected, graph.neighbors("A"));

        expected = Set.of("C", "A");
        assertEquals(expected, graph.neighbors("B"));

        expected = Set.of("C");
        assertEquals(expected, graph.neighbors("C"));

        expected = Set.of();
        assertEquals(expected, graph.neighbors("D"));
    }

    @Test
    void testTransposeSimple () {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        DirectedGraph transposed = graph.transpose();

        Set<String> expectedNodes = Set.of("A", "B", "C", "D");
        assertEquals(expectedNodes, transposed.getNodes());

        Map<String, Set<Edge>> expectedEdges = Map.of("A", Set.of(),
                "B", Set.of(new Edge("B", "A")),
                "C", Set.of(new Edge("C", "B")),
                "D", Set.of(new Edge("D", "C")));
        assertEquals(expectedEdges, transposed.getAdjacencyLists());
    }

    @Test
    void testTransposeG3 () {
        DirectedGraph graph = ExampleGraphs.g3();
        DirectedGraph transposed = graph.transpose();
        HashMap<String, Set<Edge>> adjacencyLists = transposed.getAdjacencyLists();

        Set<Edge> expected;

        expected = Set.of(new Edge("A", "B"), new Edge("A", "E"));
        assertEquals(expected, adjacencyLists.get("A"));

        expected = Set.of();
        assertEquals(expected, adjacencyLists.get("B"));

        expected = Set.of(new Edge("C", "J"), new Edge("C", "A"));
        assertEquals(expected, adjacencyLists.get("C"));

        expected = Set.of(new Edge("D", "C"));
        assertEquals(expected, adjacencyLists.get("D"));

        expected = Set.of();
        assertEquals(expected, adjacencyLists.get("E"));

        expected = Set.of(new Edge("F", "H"), new Edge("F", "D"));
        assertEquals(expected, adjacencyLists.get("F"));

        expected = Set.of(new Edge("G", "H"), new Edge("G", "B"));
        assertEquals(expected, adjacencyLists.get("G"));

        expected = Set.of(new Edge("H", "I"), new Edge("H", "A"));
        assertEquals(expected, adjacencyLists.get("H"));

        expected = Set.of(new Edge("I", "E"), new Edge("I", "G"));
        assertEquals(expected, adjacencyLists.get("I"));

        expected = Set.of(new Edge("J", "F"));
        assertEquals(expected, adjacencyLists.get("J"));
    }

    // DFS Tests

    @Test
    void testDFS() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        DFS dfs = new DFS(graph);
        dfs.traverse("A");
        List<String> traversal = dfs.getTraversal();

        List<String> expected = List.of("A", "B", "C", "D");
        assertEquals(expected, traversal);
    }

    @Test
    void testDisconnectedDFS () {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("C", "D");
        DFS dfs = new DFS(graph);
        dfs.traverse(List.of("A", "C"));

        List<String> expected;

        expected = List.of("A", "B");
        assertEquals(expected, dfs.getAllTraversals().get("A"));

        expected = List.of("C", "D");
        assertEquals(expected, dfs.getAllTraversals().get("C"));
    }

    @Test
    void testg0 () {
        DirectedGraph graph = ExampleGraphs.g0();
        DFS dfs = new DFS(graph);
        dfs.traverse("A");

        List<String> expected = List.of("A", "B", "C", "D");
        List<String> actual = dfs.getTraversal();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void testg1 () {
        DirectedGraph graph = ExampleGraphs.g1();
        DFS dfs = new DFS(graph);
        dfs.traverse("A");

        List<String> expected = List.of("A", "B", "C", "D", "E");
        List<String> actual = dfs.getTraversal();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void testg2 () {
        DirectedGraph graph = ExampleGraphs.g2();
        DFS dfs = new DFS(graph);
        dfs.traverse("A");

        List<String> expected = List.of("A", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A12", "A9", "A10",
                "A11");
        List<String> actual = dfs.getTraversal();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void testg3 () {
        DirectedGraph graph = ExampleGraphs.g3();
        DFS dfs;

        dfs = new DFS(graph);
        dfs.traverse("A");

        List<String> expected;
        List<String> actual;

        expected = List.of("A", "C", "D", "F", "J", "H", "G", "I");
        actual = dfs.getTraversal();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));

        HashMap<String,List<String>> traversals;

        dfs.reset();
        dfs.traverse(List.of("A","B","E"));
        traversals = dfs.getAllTraversals();
        expected = List.of("A", "C", "D", "F", "J", "H", "G", "I");
        actual = traversals.get("A");
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        expected = List.of("B");
        actual = traversals.get("B");
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        expected = List.of("E");
        actual = traversals.get("E");
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));

        dfs.reset();
        dfs.traverse(List.of("B","E","A"));
        traversals = dfs.getAllTraversals();
        expected = List.of("B", "A", "C", "D", "F", "J", "H", "G", "I");
        actual = traversals.get("B");
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        expected = List.of("E");
        actual = traversals.get("E");
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        expected = List.of();
        actual = traversals.get("A");
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));

        dfs.reset();
        dfs.traverse(List.of("E","B","A"));
        traversals = dfs.getAllTraversals();
        expected = List.of("E", "A", "C", "D", "F", "J", "H", "G", "I");
        actual = traversals.get("E");
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        expected = List.of("B");
        actual = traversals.get("B");
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
        expected = List.of();
        actual = traversals.get("A");
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    //Topological Sort Tests

    @Test
    void simple() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        TopologicalSort ts = new TopologicalSort(graph);
        ts.traverse("A");
        List<String> expected = List.of("A", "B", "C", "D");
        List<String> actual = ts.getSortedList();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void g0() {
        DirectedGraph graph = ExampleGraphs.g0();
        TopologicalSort ts = new TopologicalSort(graph);
        ts.traverse("A");
        List<String> expected = List.of("A", "B", "C", "D");
        List<String> actual = ts.getSortedList();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test
    void g2() {
        DirectedGraph graph = ExampleGraphs.g2();
        TopologicalSort ts = new TopologicalSort(graph);
        ts.traverse("A");
        List<String> expected = List.of("A", "A8", "A9", "A11", "A10", "A12", "A7", "A2", "A6", "A3", "A5", "A4");
        List<String> actual = ts.getSortedList();
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    //Cycle detection tests
    @Test
    void testSimpleTriangleCycleDetection() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        CycleDetection cycleDetection = new CycleDetection(graph);
        cycleDetection.traverse("A");
        assertTrue(cycleDetection.hasCycle());
    }

    @Test
    void testSquareCycleDetection() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "A");
        CycleDetection cycleDetection = new CycleDetection(graph);
        cycleDetection.traverse("A");
        assertTrue(cycleDetection.hasCycle());
    }

    @Test
    void testAcyclicTreeGraph() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        CycleDetection cycleDetection = new CycleDetection(graph);
        cycleDetection.traverse("A");
        assertFalse(cycleDetection.hasCycle());
    }

    @Test
    void testDAGNoCycleDetection() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        CycleDetection cycleDetection = new CycleDetection(graph);
        cycleDetection.traverse("A");
        assertFalse(cycleDetection.hasCycle());
    }

    @Test
    void testMultipleCycles() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        graph.addEdge("B", "D");
        graph.addEdge("D", "E");
        graph.addEdge("E", "B");
        CycleDetection cycleDetection = new CycleDetection(graph);
        cycleDetection.traverse("A");
        assertTrue(cycleDetection.hasCycle());
    }

    @Test
    void testNestedCycles() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "A");
        graph.addEdge("B", "E");
        graph.addEdge("E", "F");
        graph.addEdge("F", "B");
        CycleDetection cycleDetection = new CycleDetection(graph);
        cycleDetection.traverse("A");
        assertTrue(cycleDetection.hasCycle());
    }

    @Test
    void testSelfLoopCycleDetection() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "A");
        CycleDetection cycleDetection = new CycleDetection(graph);
        cycleDetection.traverse("A");
        assertTrue(cycleDetection.hasCycle());
    }

    @Test
    void testPartlyCyclicGraph() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A"); // Cyclic component
        graph.addEdge("D", "E");
        graph.addEdge("E", "F"); // Acyclic component
        CycleDetection cycleDetection = new CycleDetection(graph);
        cycleDetection.traverse("A");
        assertTrue(cycleDetection.hasCycle());
    }

    @Test
    void testCompleteGraphCycleDetection() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");
        graph.addEdge("B", "A");
        graph.addEdge("C", "A");
        graph.addEdge("D", "A");
        graph.addEdge("C", "B");
        graph.addEdge("D", "B");
        graph.addEdge("D", "C");
        CycleDetection cycleDetection = new CycleDetection(graph);
        cycleDetection.traverse("A");
        assertTrue(cycleDetection.hasCycle());
    }

    //Strongly Connected Components tests

    @Test
    void singleCycle() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> components = scc.compute();
        assertTrue(components.get("A").containsAll(List.of("A", "B", "C")));
    }

    @Test
    void twoDisconnectedCycles() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "A");
        graph.addEdge("C", "D");
        graph.addEdge("D", "C");
        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> components = scc.compute();
        assertTrue(components.get("A").containsAll(List.of("A", "B")));
        assertTrue(components.get("C").containsAll(List.of("C", "D")));
    }

    @Test
    void multiComponentWithCrossConnections() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        graph.addEdge("D", "E");
        graph.addEdge("E", "D");
        graph.addEdge("C", "D");
        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> components = scc.compute();
        assertTrue(components.get("A").containsAll(List.of("A", "B", "C")));
        assertTrue(components.get("D").containsAll(List.of("D", "E")));
    }

    @Test
    void chainOfComponents() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "A");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "C");
        graph.addEdge("D", "E");
        graph.addEdge("E", "F");
        graph.addEdge("F", "E");
        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> components = scc.compute();
        assertTrue(components.get("A").containsAll(List.of("A", "B")));
        assertTrue(components.get("C").containsAll(List.of("C", "D")));
        assertTrue(components.get("E").containsAll(List.of("E", "F")));
    }

    @Test
    void largeSCCWithSmallerOnes() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        graph.addEdge("E", "A");
        graph.addEdge("F", "G");
        graph.addEdge("G", "H");
        graph.addEdge("H", "F");
        graph.addEdge("I", "J");
        graph.addEdge("E", "F");
        graph.addEdge("G", "I");
        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> components = scc.compute();
        assertTrue(components.get("A").containsAll(List.of("A", "B", "C", "D", "E")));
        assertTrue(components.get("F").containsAll(List.of("F", "G", "H")));
        assertTrue(components.get("I").contains("I"));
    }

    @Test
    void nestedCycles() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        graph.addEdge("D", "E");
        graph.addEdge("E", "F");
        graph.addEdge("F", "D");
        graph.addEdge("C", "D");
        graph.addEdge("F", "B");
        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> components = scc.compute();
        assertTrue(components.get("A").containsAll(List.of("A", "B", "C", "D", "E", "F")));
    }

    //Reachability Tests

    @Test
    void testReachableSimpleGraph() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        graph.addEdge("D", "E");
        Reachability reachability = new Reachability(graph);
        reachability.traverse("A");
        assertTrue(reachability.isReachable("D"));
        assertTrue(reachability.isReachable("E"));
        assertTrue(reachability.isReachable("A"));
    }

    @Test
    void testReachableCyclicGraph() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "F");
        graph.addEdge("F", "E");
        graph.addEdge("E", "D");
        graph.addEdge("D", "A");
        Reachability reachability = new Reachability(graph);
        reachability.traverse("A");
        assertTrue(reachability.isReachable("B"));
        assertTrue(reachability.isReachable("C"));
        assertTrue(reachability.isReachable("D"));
        assertTrue(reachability.isReachable("E"));
        assertTrue(reachability.isReachable("F"));
    }

    @Test
    void testReachableDisconnectedGraph() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("D", "E");
        graph.addEdge("E", "F");
        Reachability reachability = new Reachability(graph);
        reachability.traverse("A");
        assertTrue(reachability.isReachable("C"));
        assertFalse(reachability.isReachable("D"));
        assertFalse(reachability.isReachable("E"));
    }

    @Test
    void testReachableFullyConnectedGraph() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("B", "C");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("D", "E");
        graph.addEdge("D", "F");
        graph.addEdge("E", "F");
        Reachability reachability = new Reachability(graph);
        reachability.traverse("A");
        assertTrue(reachability.isReachable("B"));
        assertTrue(reachability.isReachable("C"));
        assertTrue(reachability.isReachable("D"));
        assertTrue(reachability.isReachable("E"));
        assertTrue(reachability.isReachable("F"));
    }

    @Test
    void testReachableTreeGraph() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        graph.addEdge("C", "F");
        Reachability reachability = new Reachability(graph);
        reachability.traverse("A");
        assertTrue(reachability.isReachable("B"));
        assertTrue(reachability.isReachable("C"));
        assertTrue(reachability.isReachable("D"));
        assertTrue(reachability.isReachable("E"));
        assertTrue(reachability.isReachable("F"));
        assertTrue(reachability.isReachable("A"));
    }

    //todo: add more tests from other files
}
