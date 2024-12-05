import graph.noweight.DirectedGraph;
import graph.noweight.traversal.rec.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TIER II TESTS
 * <p>
 * RecursiveGraphTraversal
 * DFSrec
 * CycleDetection
 * Reachability
 * TopologicalSort
 * StronglyConnectedComponents
 */

class Tier2Tests {

    @Test
    void g1 () {
        DirectedGraph g = ExampleGraphs.g1();

        DFSrec dfs = new DFSrec(g);
        dfs.traverseFromSource("A");
        assertEquals(List.of("A","B","C","D","E"), dfs.getCurrentTraversal());

        CycleDetection cd = new CycleDetection(g);
        cd.traverseFromSource("A");
        assertTrue(cd.hasCycle());

        Reachability r = new Reachability(g);
        r.traverseFromSource("A");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of(), table.get("A"));
        assertEquals(Set.of("A","B","D","E"), table.get("B"));
        assertEquals(Set.of("A","B","D","E"), table.get("C"));
        assertEquals(Set.of("A","B","D","E"), table.get("D"));
        assertEquals(Set.of("A","B","D","E"), table.get("E"));
    }

    @Test
    void g2 () {
        DirectedGraph g = ExampleGraphs.g2();

        DFSrec dfs = new DFSrec(g);
        dfs.traverseFromSource("A1");
        assertEquals(List.of("A1","A2","A3","A4","A5","A6","A7","A8","A12","A9","A10","A11"), dfs.getCurrentTraversal());

        CycleDetection cd = new CycleDetection(g);
        cd.traverseFromSource("A1");
        assertFalse(cd.hasCycle());

        TopologicalSort ts = new TopologicalSort(g);
        ts.traverseFromSource("A1");
        assertEquals(List.of("A1","A8","A9","A11","A10","A12","A7","A2","A6","A3","A5","A4"), ts.getTraversal());

        Reachability r = new Reachability(g);
        r.traverseFromSource("A1");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of(), table.get("A1"));
        assertEquals(Set.of("A1"), table.get("A2"));
        assertEquals(Set.of("A1","A2"), table.get("A3"));
        assertEquals(Set.of("A1","A2","A3"), table.get("A4"));
        assertEquals(Set.of("A1","A2", "A3"), table.get("A5"));
        assertEquals(Set.of("A1","A2"), table.get("A6"));
        assertEquals(Set.of("A1"), table.get("A7"));
        assertEquals(Set.of("A1"), table.get("A8"));
        assertEquals(Set.of("A1","A8"), table.get("A9"));
        assertEquals(Set.of("A1","A8","A9"), table.get("A10"));
        assertEquals(Set.of("A1","A8","A9"), table.get("A11"));
        assertEquals(Set.of("A1","A8"), table.get("A12"));
    }

    @Test
    void g3 () {
        DirectedGraph g = ExampleGraphs.g3();

        StronglyConnectedComponents scc = new StronglyConnectedComponents(g);
        HashMap<String,List<String>> components = scc.computeSCC();
        assertEquals(List.of("A"), components.get("A"));
        assertEquals(List.of("B"), components.get("B"));
        assertEquals(List.of("C", "J", "F", "D"), components.get("C"));
        assertEquals(List.of(), components.get("D"));
        assertEquals(List.of("E"), components.get("E"));
        assertEquals(List.of(), components.get("F"));
        assertEquals(List.of(), components.get("G"));
        assertEquals(List.of("H", "I", "G"), components.get("H"));
        assertEquals(List.of(), components.get("I"));
        assertEquals(List.of(), components.get("J"));
    }

    @Test
    void g4 () {
        DirectedGraph g = ExampleGraphs.g4();

        StronglyConnectedComponents scc = new StronglyConnectedComponents(g);
        HashMap<String,List<String>> components = scc.computeSCC();
        assertEquals(List.of("A"), components.get("A"));
        assertEquals(List.of("B", "E", "D"), components.get("B"));
        assertEquals(List.of("C"), components.get("C"));
        assertEquals(List.of(), components.get("D"));
        assertEquals(List.of(), components.get("E"));

        CycleDetection cd = new CycleDetection(g);
        cd.traverseFromSource("A");
        assertTrue(cd.hasCycle());

        Reachability r = new Reachability(g);
        r.traverseFromSource("A");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of(), table.get("A"));
        assertEquals(Set.of("A", "B", "D", "E"), table.get("B"));
        assertEquals(Set.of("A", "B", "D", "E"), table.get("C"));
        assertEquals(Set.of("A", "B", "D", "E"), table.get("D"));
        assertEquals(Set.of("A", "B", "D", "E"), table.get("E"));

        TopologicalSort ts = new TopologicalSort(g);
        ts.traverseFromSource("A");
        assertEquals(List.of("A","B","D","E","C"), ts.getTraversal());

        DFSrec dfs = new DFSrec(g);
        dfs.traverseFromSource("A");
        assertEquals(List.of("A","B","C","D","E"), dfs.getCurrentTraversal());
    }

    @Test
    void g5 () {
        DirectedGraph g = ExampleGraphs.g5();

        StronglyConnectedComponents scc = new StronglyConnectedComponents(g);
        HashMap<String,List<String>> components = scc.computeSCC();
        assertEquals(List.of("A"), components.get("A"));
        assertEquals(List.of("B"), components.get("B"));
        assertEquals(List.of("C"), components.get("C"));
        assertEquals(List.of("D"), components.get("D"));
        assertEquals(List.of("E"), components.get("E"));
        assertEquals(List.of("F"), components.get("F"));

        CycleDetection cd = new CycleDetection(g);
        cd.traverseFromSource("A");
        assertFalse(cd.hasCycle());

        Reachability r = new Reachability(g);
        r.traverseFromSource("A");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of(), table.get("A"));
        assertEquals(Set.of("A"), table.get("B"));
        assertEquals(Set.of("A", "B"), table.get("C"));
        assertEquals(Set.of("A", "B", "C", "E"), table.get("D"));
        assertEquals(Set.of("A", "B", "C"), table.get("E"));
        assertEquals(Set.of("A", "B", "C", "D", "E"), table.get("F"));

        TopologicalSort ts = new TopologicalSort(g);
        ts.traverseFromSource("A");
        assertEquals(List.of("A","B","C","E","D","F"), ts.getTraversal());

        DFSrec dfs = new DFSrec(g);
        dfs.traverseFromSource("A");
        assertEquals(List.of("A","B","C","E","D","F"), dfs.getCurrentTraversal());
    }

    @Test
    void g6 () {
        DirectedGraph g = ExampleGraphs.g6();

        StronglyConnectedComponents scc = new StronglyConnectedComponents(g);
        HashMap<String,List<String>> components = scc.computeSCC();
        assertEquals(List.of("A", "B", "C", "D", "E", "G", "F"), components.get("A"));
        assertEquals(List.of(), components.get("B"));
        assertEquals(List.of(), components.get("C"));
        assertEquals(List.of(), components.get("D"));
        assertEquals(List.of(), components.get("E"));
        assertEquals(List.of(), components.get("F"));
        assertEquals(List.of(), components.get("G"));

        CycleDetection cd = new CycleDetection(g);
        cd.traverseFromSource("A");
        assertTrue(cd.hasCycle());

        Reachability r = new Reachability(g);
        r.traverseFromSource("A");
        HashMap<String,Set<String>> table = r.getTable();
        assertEquals(Set.of("A", "B", "C", "D", "E", "F", "G"), table.get("A"));
        assertEquals(Set.of("A", "B", "C", "D", "E", "F", "G"), table.get("B"));
        assertEquals(Set.of("A", "B", "C", "D", "E", "F", "G"), table.get("C"));
        assertEquals(Set.of("A", "B", "C", "D", "E", "F", "G"), table.get("D"));
        assertEquals(Set.of("A", "B", "C", "D", "E", "F", "G"), table.get("E"));
        assertEquals(Set.of("A", "B", "C", "D", "E", "F", "G"), table.get("F"));
        assertEquals(Set.of("A", "B", "C", "D", "E", "F", "G"), table.get("G"));

        TopologicalSort ts = new TopologicalSort(g);
        ts.traverseFromSource("A");
        assertEquals(List.of("A","B","C","D","E","G","F"), ts.getTraversal());

        DFSrec dfs = new DFSrec(g);
        dfs.traverseFromSource("A");
        assertEquals(List.of("A","B","C","D","E","G","F"), dfs.getCurrentTraversal());
    }
}
