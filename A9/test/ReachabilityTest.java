import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReachabilityTest {
    @Test
    void testReachable() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        Reachability reachability = new Reachability(graph);
        reachability.traverse("A");
        assertTrue(reachability.isReachable("D"));
    }

}