import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.MaximumFlow;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TIER V TESTS
 * <p>
 * MaximumFlow
 */

public class Tier5Tests {

    @Test
    public void maxFlow() {
        WeightedDirectedGraph g = ExampleGraphs.g7();
        MaximumFlow mf = new MaximumFlow(g, "S", "T");
        mf.runMaxFlow();
        assertEquals(15, mf.getMaxFlow());
    }

    @Test
    public void maxFlowG6() {
        WeightedDirectedGraph g = ExampleGraphs.g6();
        MaximumFlow mf = new MaximumFlow(g, "A", "F");
        mf.runMaxFlow();
        assertEquals(13, mf.getMaxFlow());
    }

    @Test
    public void maxFlowG8() {
        WeightedDirectedGraph g = ExampleGraphs.g8();
        MaximumFlow mf = new MaximumFlow(g, "S", "T");
        mf.runMaxFlow();
        assertEquals(1, mf.getMaxFlow());
    }
}
