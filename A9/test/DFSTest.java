import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DFSTest {
    @Test
    void testDFS() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        DFS dfs = new DFS(graph);
        dfs.traverse("A");
        List<String> traversal = dfs.getTraversal();
        assertEquals("[A, B, C, D]", traversal.toString());
    }

    @Test
    void testDisconnectedDFS () {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("C", "D");
        DFS dfs = new DFS(graph);
        dfs.traverse(List.of("A", "C"));
        assertEquals("[A, B]", dfs.getAllTraversals().get("A").toString());
        assertEquals("[C, D]", dfs.getAllTraversals().get("C").toString());
    }

    @Test
    void testg0 () {
        DirectedGraph graph = ExampleGraphs.g0();
        DFS dfs = new DFS(graph);
        dfs.traverse("A");
        assertEquals("[A, B, D, C]", dfs.getTraversal().toString());
    }

    @Test
    void testg1 () {
        DirectedGraph graph = ExampleGraphs.g1();
        DFS dfs = new DFS(graph);
        dfs.traverse("A");
        assertEquals("[A, B, C, D, E]", dfs.getTraversal().toString());
    }

    @Test
    void testg2 () {
        DirectedGraph graph = ExampleGraphs.g2();
        DFS dfs = new DFS(graph);
        dfs.traverse("A");
        assertEquals("[A, A2, A3, A4, A5, A6, A7, A8, A12, A9, A10, A11]",
                dfs.getTraversal().toString());
    }

    @Test
    void testg3 () {
        DirectedGraph graph = ExampleGraphs.g3();
        DFS dfs;

        dfs = new DFS(graph);
        dfs.traverse("A");
        assertEquals("[A, C, D, F, J, H, G, I]", dfs.getTraversal().toString());

        HashMap<String,List<String>> traversals;

        dfs.reset();
        dfs.traverse(List.of("A","B","E"));
        traversals = dfs.getAllTraversals();
        assertEquals("[A, C, D, F, J, H, G, I]", traversals.get("A").toString());
        assertEquals("[B]", traversals.get("B").toString());
        assertEquals("[E]", traversals.get("E").toString());

        dfs.reset();
        dfs.traverse(List.of("B","E","A"));
        traversals = dfs.getAllTraversals();
        assertEquals("[B, A, C, D, F, J, H, G, I]", traversals.get("B").toString());
        assertEquals("[E]", traversals.get("E").toString());
        assertEquals("[]", traversals.get("A").toString());

        dfs.reset();
        dfs.traverse(List.of("E","B","A"));
        traversals = dfs.getAllTraversals();
        assertEquals("[E, A, C, D, F, J, H, G, I]", traversals.get("E").toString());
        assertEquals("[B]", traversals.get("B").toString());
        assertEquals("[]", traversals.get("A").toString());
    }
}