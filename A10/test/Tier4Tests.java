import graph.noweight.Edge;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.AllShortestPaths;
import graph.withweight.traversal.MinimumSpanningTree;
import graph.withweight.WeightedPath;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TIER IV TESTS
 * <p>
 * AllShortestsPaths
 * MinimumSpanningTree
 */

public class Tier4Tests {

    @Test
    public void shortestPaths1() {
        WeightedDirectedGraph g = ExampleGraphs.g5();
        AllShortestPaths sp = new AllShortestPaths(g, "A");
        sp.iterativeTraversal();
        Edge ac, ce, ed, df;
        ac = new Edge("A", "C");
        ce = new Edge("C", "E");
        ed = new Edge("E", "D");
        df = new Edge("D", "F");
        List<Edge> best = List.of(ac, ce, ed, df);
        WeightedPath computed = sp.getPath("F");
        assertEquals(best,computed.edges());
        assertEquals(20, computed.totalWeight().value());
        assertEquals(2, computed.minWeight().value());
    }

    @Test
    public void shortestPaths2() {
        WeightedDirectedGraph g = ExampleGraphs.g6();
        AllShortestPaths sp = new AllShortestPaths(g, "A");
        sp.iterativeTraversal();
        Edge ac, cd, dg;
        ac = new Edge("A", "C");
        cd = new Edge("C", "D");
        dg = new Edge("D", "G");
        List<Edge> best = List.of(ac, cd, dg);
        WeightedPath computed = sp.getPath("G");
        assertEquals(best,computed.edges());
        assertEquals(22, computed.totalWeight().value());
        assertEquals(3, computed.minWeight().value());
    }

    @Test
    public void shortestPaths3() {
        WeightedDirectedGraph g = ExampleGraphs.g8();
        AllShortestPaths sp = new AllShortestPaths(g, "S");
        sp.iterativeTraversal();
        Edge sc, cd, dt;
        sc = new Edge("S", "C");
        cd = new Edge("C", "D");
        dt = new Edge("D", "T");
        List<Edge> best = List.of(sc, cd, dt);
        WeightedPath computed = sp.getPath("T");
        assertEquals(best,computed.edges());
        assertEquals(6, computed.totalWeight().value());
        assertEquals(1, computed.minWeight().value());
    }

    @Test
    public void mst1() {
        WeightedDirectedGraph g = ExampleGraphs.g6();
        MinimumSpanningTree mst = new MinimumSpanningTree(g, "A");
        mst.iterativeTraversal();
        HashMap<String,String> result = mst.getPreviousNodes();
        assertEquals("D", result.get("B"));
        assertEquals("A", result.get("C"));
        assertEquals("C", result.get("D"));
        assertEquals("D", result.get("E"));
        assertEquals("C", result.get("F"));
        assertEquals("E", result.get("G"));
        assertEquals(42, mst.getWeight());
    }

    @Test
    public void mst2() {
        WeightedDirectedGraph g = ExampleGraphs.g7().bidirectional();
        MinimumSpanningTree mst = new MinimumSpanningTree(g, "A");
        mst.iterativeTraversal();
        HashMap<String,String> result = mst.getPreviousNodes();
        assertEquals("A", result.get("B"));
        assertEquals("A", result.get("C"));
        assertEquals("B", result.get("D"));
        assertEquals("B", result.get("T"));
        assertEquals("C", result.get("S"));
        assertEquals(30, mst.getWeight());
    }

    @Test
    public void mst3() {
        WeightedDirectedGraph g = ExampleGraphs.g8().bidirectional();
        MinimumSpanningTree mst = new MinimumSpanningTree(g, "A");
        mst.iterativeTraversal();
        HashMap<String,String> result = mst.getPreviousNodes();
        assertEquals("A", result.get("S"));
        assertEquals("S", result.get("B"));
        assertEquals("S", result.get("C"));
        assertEquals("C", result.get("D"));
        assertEquals("D", result.get("T"));
        assertEquals(16, mst.getWeight());
    }
}
