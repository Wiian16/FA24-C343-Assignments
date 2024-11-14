import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(expectedSet, graph.getNodes(),
                String.format("Expected :%s\nActual   :%s", expectedSet, graph.getNodes()));

        Map<String, Set<Edge>> expectedMap = Map.of("A", Set.of(new Edge("A", "B")),
                "B", Set.of(new Edge("B", "C")),
                "C", Set.of(new Edge("C", "D")),
                "D", Set.of());
        assertEquals(expectedMap, graph.getAdjacencyLists(),
                String.format("Expected :%s\nActual   :%s", expectedMap, graph.getNodes()));
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
        assertEquals(expected, graph.outgoingEdges("A"),
                String.format("Expected :%s\nActual   :%s", expected, graph.outgoingEdges("A")));

        expected = Set.of(new Edge("B", "C"), new Edge ("B", "A"));
        assertEquals(expected, graph.outgoingEdges("B"),
                String.format("Expected :%s\nActual   :%s", expected, graph.outgoingEdges("B")));

        expected = Set.of(new Edge("C", "C"));
        assertEquals(expected, graph.outgoingEdges("C"),
                String.format("Expected :%s\nActual   :%s", expected, graph.outgoingEdges("C")));

        expected = Set.of();
        assertEquals(expected, graph.outgoingEdges("D"),
                String.format("Expected :%s\nActual   :%s", expected, graph.outgoingEdges("D")));
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
        assertEquals(expected, graph.neighbors("A"),
                String.format("Expected :%s\nActual   :%s", expected, graph.neighbors("A")));

        expected = Set.of("C", "A");
        assertEquals(expected, graph.neighbors("B"),
                String.format("Expected :%s\nActual   :%s", expected, graph.neighbors("B")));

        expected = Set.of("C");
        assertEquals(expected, graph.neighbors("C"),
                String.format("Expected :%s\nActual   :%s", expected, graph.neighbors("C")));

        expected = Set.of();
        assertEquals(expected, graph.neighbors("D"),
                String.format("Expected :%s\nActual   :%s", expected, graph.neighbors("C")));
    }

    @Test
    void testTransposeSimple () {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        DirectedGraph transposed = graph.transpose();

        Set<String> expectedNodes = Set.of("A", "B", "C", "D");
        assertEquals(expectedNodes, transposed.getNodes(),
                String.format("Expected :%s\nActual   :%s", expectedNodes, transposed.getNodes()));

        Map<String, Set<Edge>> expectedEdges = Map.of("A", Set.of(),
                "B", Set.of(new Edge("B", "A")),
                "C", Set.of(new Edge("C", "B")),
                "D", Set.of(new Edge("D", "C")));
        assertEquals(expectedEdges, transposed.getAdjacencyLists(),
                String.format("Expected :%s\nActual   :%s", expectedEdges, transposed.getAdjacencyLists()));
    }

    @Test
    void testTransposeG3 () {
        DirectedGraph graph = ExampleGraphs.g3();
        DirectedGraph transposed = graph.transpose();
        HashMap<String, Set<Edge>> adjacencyLists = transposed.getAdjacencyLists();

        Set<Edge> expected;

        expected = Set.of(new Edge("A", "B"), new Edge("A", "E"));
        assertEquals(expected, adjacencyLists.get("A"),
                String.format("Expected :%s\nActual   :%s", expected, adjacencyLists.get("A")));

        expected = Set.of();
        assertEquals(expected, adjacencyLists.get("B"),
                String.format("Expected :%s\nActual   :%s", expected, adjacencyLists.get("B")));

        expected = Set.of(new Edge("C", "J"), new Edge("C", "A"));
        assertEquals(expected, adjacencyLists.get("C"),
                String.format("Expected :%s\nActual   :%s", expected, adjacencyLists.get("C")));

        expected = Set.of(new Edge("D", "C"));
        assertEquals(expected, adjacencyLists.get("D"),
                String.format("Expected :%s\nActual   :%s", expected, adjacencyLists.get("D")));

        expected = Set.of();
        assertEquals(expected, adjacencyLists.get("E"),
                String.format("Expected :%s\nActual   :%s", expected, adjacencyLists.get("E")));

        expected = Set.of(new Edge("F", "H"), new Edge("F", "D"));
        assertEquals(expected, adjacencyLists.get("F"),
                String.format("Expected :%s\nActual   :%s", expected, adjacencyLists.get("F")));

        expected = Set.of(new Edge("G", "H"), new Edge("G", "B"));
        assertEquals(expected, adjacencyLists.get("G"),
                String.format("Expected :%s\nActual   :%s", expected, adjacencyLists.get("G")));

        expected = Set.of(new Edge("H", "I"), new Edge("H", "A"));
        assertEquals(expected, adjacencyLists.get("H"),
                String.format("Expected :%s\nActual   :%s", expected, adjacencyLists.get("H")));

        expected = Set.of(new Edge("I", "E"), new Edge("I", "G"));
        assertEquals(expected, adjacencyLists.get("I"),
                String.format("Expected :%s\nActual   :%s", expected, adjacencyLists.get("I")));

        expected = Set.of(new Edge("J", "F"));
        assertEquals(expected, adjacencyLists.get("J"),
                String.format("Expected :%s\nActual   :%s", expected, adjacencyLists.get("J")));
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
        assertEquals(expected, traversal,
                String.format("Expected :%s\nActual   :%s", expected, traversal));
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
        assertEquals(expected, dfs.getAllTraversals().get("A"),
                String.format("Expected :%s\nActual   :%s", expected, dfs.getAllTraversals().get("A")));

        expected = List.of("C", "D");
        assertEquals(expected, dfs.getAllTraversals().get("C"),
                String.format("Expected :%s\nActual   :%s", expected, dfs.getAllTraversals().get("C")));
    }

    @Test
    void testg0 () {
        DirectedGraph graph = ExampleGraphs.g0();
        DFS dfs = new DFS(graph);
        dfs.traverse("A");

        List<String> expected = List.of("A", "B", "C", "D");
        assertEquals(expected, dfs.getTraversal(),
                String.format("Expected :%s\nActual   :%s", expected, dfs.getTraversal())); //todo: remove all messages
    }

    @Test
    void testg1 () {
        DirectedGraph graph = ExampleGraphs.g1();
        DFS dfs = new DFS(graph);
        dfs.traverse("A");

        List<String> expected = List.of("A", "B", "C", "D", "E");
        assertEquals(expected, dfs.getTraversal());
    }

    @Test
    void testg2 () {
        DirectedGraph graph = ExampleGraphs.g2();
        DFS dfs = new DFS(graph);
        dfs.traverse("A");

        List<String> expected = List.of("A", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A12", "A9", "A10",
                "A11");
        assertEquals(expected, dfs.getTraversal());
    }

    @Test
    void testg3 () {
        DirectedGraph graph = ExampleGraphs.g3();
        DFS dfs;

        dfs = new DFS(graph);
        dfs.traverse("A");

        List<String> expected;

        expected = List.of("A", "C", "D", "F", "J", "H", "G", "I");
        assertEquals(expected, dfs.getTraversal());

        HashMap<String,List<String>> traversals;

        dfs.reset();
        dfs.traverse(List.of("A","B","E"));
        traversals = dfs.getAllTraversals();
        expected = List.of("A", "C", "D", "F", "J", "H", "G", "I");
        assertEquals(expected, traversals.get("A"));
        expected = List.of("B");
        assertEquals(expected, traversals.get("B"));
        expected = List.of("E");
        assertEquals(expected, traversals.get("E"));

        dfs.reset();
        dfs.traverse(List.of("B","E","A"));
        traversals = dfs.getAllTraversals();
        expected = List.of("B", "A", "C", "D", "J", "H", "G", "I");
        assertEquals(expected, traversals.get("B"));
        expected = List.of("E");
        assertEquals(expected, traversals.get("E"));
        expected = List.of();
        assertEquals(expected, traversals.get("A"));

        dfs.reset();
        dfs.traverse(List.of("E","B","A"));
        traversals = dfs.getAllTraversals();
        expected = List.of("E", "A", "C", "D", "F", "J", "H", "G", "I");
        assertEquals(expected, traversals.get("E"));
        expected = List.of("B");
        assertEquals(expected, traversals.get("B"));
        expected = List.of();
        assertEquals(expected, traversals.get("A"));
    }
}
