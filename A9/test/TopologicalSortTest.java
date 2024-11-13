import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortTest {
    @Test
    void simple() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        TopologicalSort ts = new TopologicalSort(graph);
        ts.traverse("A");
        assertEquals("[A, B, C, D]", ts.getSortedList().toString());
    }

    @Test
    void g0() {
        DirectedGraph graph = ExampleGraphs.g0();
        TopologicalSort ts = new TopologicalSort(graph);
        ts.traverse("A");
        assertEquals("[A, C, B, D]", ts.getSortedList().toString());
    }

    @Test
    void g2() {
        DirectedGraph graph = ExampleGraphs.g2();
        TopologicalSort ts = new TopologicalSort(graph);
        ts.traverse("A");
        assertEquals("[A, A8, A9, A11, A10, A12, A7, A2, A6, A3, A5, A4]", ts.getSortedList().toString());
    }

}