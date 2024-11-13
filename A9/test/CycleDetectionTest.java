import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CycleDetectionTest {
    @Test
    void testNoCycleDetection() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        CycleDetection cycleDetection = new CycleDetection(graph);
        cycleDetection.traverse("A");
        assertFalse(cycleDetection.hasCycle());
    }

    @Test
    void testCycleDetection() {
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
    void testGraphs () {
        for (DirectedGraph graph : ExampleGraphs.allGraphs()) {
            CycleDetection cycleDetection = new CycleDetection(graph);
            cycleDetection.traverse("A");
            if (graph.getName().equals("g0") || graph.getName().equals("g2"))
                assertFalse(cycleDetection.hasCycle());
            else
                assertTrue(cycleDetection.hasCycle());
        }
    }
}