import graph.noweight.DirectedGraph;
import graph.noweight.Edge;
import graph.noweight.traversal.iter.QueueCollection;
import graph.noweight.traversal.iter.StackCollection;
import graph.withweight.Weight;
import graph.withweight.WeightedPath;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TIER I TESTS
 * <p>
 * QueueCollection
 * StackCollection
 * Weight
 * Path
 * WeightedPath
 * DirectedGraph
 */

public class Tier1Tests {

    @Test
    public void queue () {
        QueueCollection q = new QueueCollection("A");
        q.add("B");
        q.add("C");
        q.add("D");
        assertEquals("A", q.get());
        assertEquals("B", q.get());
        assertEquals("C", q.get());
        q.add("E");
        q.add("F");
        assertEquals("D", q.get());
        assertEquals("E", q.get());
        assertEquals("F", q.get());
        assertTrue(q.isEmpty());
    }

    @Test
    void queueRandom() {
        QueueCollection q = new QueueCollection("A");
        Queue<String> expected = new java.util.LinkedList<>();
        expected.add("A");
        Random rand = new Random();
        String[] randomLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                                   "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                                   "U", "V", "W", "X", "Y", "Z" };

        for (int i = 0; i < 100; i++) {
            int randomIndex = rand.nextInt(randomLetters.length);
            q.add(randomLetters[randomIndex]);
            expected.add(randomLetters[randomIndex]);
        }

        while (!expected.isEmpty()) {
            assertEquals(expected.poll(), q.get());
        }

        assertTrue(q.isEmpty());
    }

    @Test
    public void stack () {
        StackCollection s = new StackCollection("A");
        s.add("B");
        s.add("C");
        s.add("D");
        assertEquals("D", s.get());
        assertEquals("C", s.get());
        assertEquals("B", s.get());
        s.add("E");
        s.add("F");
        assertEquals("F", s.get());
        assertEquals("E", s.get());
        assertEquals("A", s.get());
        assertTrue(s.isEmpty());
    }

    @Test
    public void stackRandom() {
        StackCollection s = new StackCollection("A");
        java.util.Stack<String> expected = new java.util.Stack<>();
        expected.add("A");
        Random rand = new Random();
        String[] randomLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                                   "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                                   "U", "V", "W", "X", "Y", "Z" };

        for (int i = 0; i < 100; i++) {
            int randomIndex = rand.nextInt(randomLetters.length);
            s.add(randomLetters[randomIndex]);
            expected.add(randomLetters[randomIndex]);
        }

        while (!expected.isEmpty()) {
            assertEquals(expected.pop(), s.get());
        }

        assertTrue(s.isEmpty());
    }

    @Test
    public void weight () {
        Weight w1 = new Weight(1);
        Weight w2 = new Weight(2);
        Weight w3 = new Weight(3);
        Weight w4 = new Weight(4);
        assertEquals(1, w1.value());
        assertEquals(3, w1.add(w2).value());
        assertEquals(1, w3.subtract(w2).value());
        assertEquals(0, w1.compareTo(new Weight(1)));
        assertTrue(w1.compareTo(w2) < 0);
        assertTrue(w2.compareTo(w1) > 0);
        assertEquals(new Weight(4), w4);
        assertTrue(w4.compareTo(Weight.INFINITY) < 0);
        assertTrue(Weight.INFINITY.compareTo(w4) > 0);
        assertEquals(Weight.INFINITY, Weight.INFINITY.add(w1));
        assertEquals(Weight.INFINITY, Weight.INFINITY.subtract(w1));
    }

    @Test
    public void path () {
        WeightedPath p = new WeightedPath();
        assertTrue(p.isEmpty());
        assertEquals(Weight.ZERO, p.totalWeight());
        assertEquals(Weight.INFINITY, p.minWeight());
        Edge e1, e2, e3;
        e1 = new Edge("A", "B");
        e2 = new Edge("B", "C");
        e3 = new Edge("C", "D");
        p.add(e1, new Weight(1));
        p.add(e2, new Weight(2));
        p.add(e3, new Weight(3));
        List<Edge> edges = p.edges();
        assertEquals(List.of(e1, e2, e3), edges);
        assertEquals(1, p.minWeight().value());
        assertEquals(6, p.totalWeight().value());
        p.reverse();
        List<Edge> redges = p.edges();
        assertEquals(List.of(e3, e2, e1), redges);
        assertEquals(1, p.minWeight().value());
        assertEquals(6, p.totalWeight().value());
    }

    @Test
    public void graph1 () {
        DirectedGraph g = ExampleGraphs.g1();
        // outgoing edges
        assertEquals(2, g.outgoingEdges("A").size());
        assertEquals(3, g.outgoingEdges("B").size());
        assertEquals(0, g.outgoingEdges("C").size());
        assertEquals(1, g.outgoingEdges("D").size());
        assertEquals(1, g.outgoingEdges("E").size());
        assertTrue(g.outgoingEdges("A").contains(new Edge("A", "B")));
        assertTrue(g.outgoingEdges("A").contains(new Edge("A", "C")));
        // neighbors
        assertEquals(2, g.neighbors("A").size());
        assertEquals(3, g.neighbors("B").size());
        assertEquals(0, g.neighbors("C").size());
        assertEquals(1, g.neighbors("D").size());
        assertEquals(1, g.neighbors("E").size());
        assertTrue(g.neighbors("A").contains("B"));
        assertTrue(g.neighbors("A").contains("C"));
        // transpose
        DirectedGraph gT = g.transpose();
        assertEquals(0, gT.outgoingEdges("A").size());
        assertEquals(2, gT.outgoingEdges("B").size());
        assertEquals(2, gT.outgoingEdges("C").size());
        assertEquals(1, gT.outgoingEdges("D").size());
        assertEquals(2, gT.outgoingEdges("E").size());
        assertTrue(gT.outgoingEdges("B").contains(new Edge("B", "A")));
        assertTrue(gT.outgoingEdges("B").contains(new Edge("B", "E")));
        assertTrue(gT.outgoingEdges("C").contains(new Edge("C", "A")));
        assertTrue(gT.outgoingEdges("C").contains(new Edge("C", "B")));
        assertTrue(gT.outgoingEdges("D").contains(new Edge("D", "B")));
        assertTrue(gT.outgoingEdges("E").contains(new Edge("E", "B")));
        assertTrue(gT.outgoingEdges("E").contains(new Edge("E", "D")));
        assertEquals(0, gT.neighbors("A").size());
        assertEquals(2, gT.neighbors("B").size());
        assertEquals(2, gT.neighbors("C").size());
        assertEquals(1, gT.neighbors("D").size());
        assertEquals(2, gT.neighbors("E").size());
        assertTrue(gT.neighbors("B").contains("A"));
        assertTrue(gT.neighbors("B").contains("E"));
        assertTrue(gT.neighbors("C").contains("A"));
        assertTrue(gT.neighbors("C").contains("B"));
        assertTrue(gT.neighbors("D").contains("B"));
        assertTrue(gT.neighbors("E").contains("B"));
        assertTrue(gT.neighbors("E").contains("D"));
        // bidirectional
        DirectedGraph gB = g.bidirectional();
        assertEquals(2, gB.outgoingEdges("A").size());
        assertEquals(4, gB.outgoingEdges("B").size());
        assertEquals(2, gB.outgoingEdges("C").size());
        assertEquals(2, gB.outgoingEdges("D").size());
        assertEquals(2, gB.outgoingEdges("E").size());
        assertTrue(gB.outgoingEdges("A").contains(new Edge("A", "B")));
        assertTrue(gB.outgoingEdges("A").contains(new Edge("A", "C")));
        assertTrue(gB.outgoingEdges("B").contains(new Edge("B", "A")));
        assertTrue(gB.outgoingEdges("B").contains(new Edge("B", "C")));
        assertTrue(gB.outgoingEdges("B").contains(new Edge("B", "D")));
        assertTrue(gB.outgoingEdges("B").contains(new Edge("B", "E")));
        assertTrue(gB.outgoingEdges("C").contains(new Edge("C", "A")));
        assertTrue(gB.outgoingEdges("C").contains(new Edge("C", "B")));
        assertTrue(gB.outgoingEdges("D").contains(new Edge("D", "B")));
        assertTrue(gB.outgoingEdges("D").contains(new Edge("D", "E")));
        assertTrue(gB.outgoingEdges("E").contains(new Edge("E", "B")));
        assertTrue(gB.outgoingEdges("E").contains(new Edge("E", "D")));
        assertEquals(2, gB.neighbors("A").size());
        assertEquals(4, gB.neighbors("B").size());
        assertEquals(2, gB.neighbors("C").size());
        assertEquals(2, gB.neighbors("D").size());
        assertEquals(2, gB.neighbors("E").size());
        assertTrue(gB.neighbors("A").contains("B"));
        assertTrue(gB.neighbors("A").contains("C"));
        assertTrue(gB.neighbors("B").contains("A"));
        assertTrue(gB.neighbors("B").contains("C"));
        assertTrue(gB.neighbors("B").contains("D"));
        assertTrue(gB.neighbors("B").contains("E"));
        assertTrue(gB.neighbors("C").contains("A"));
        assertTrue(gB.neighbors("C").contains("B"));
        assertTrue(gB.neighbors("D").contains("B"));
        assertTrue(gB.neighbors("D").contains("E"));
        assertTrue(gB.neighbors("E").contains("B"));
        assertTrue(gB.neighbors("E").contains("D"));
    }

    @Test

    public void graph2() {
        DirectedGraph g = ExampleGraphs.g2();
        // outgoing edges
        assertEquals(3, g.outgoingEdges("A1").size());
        assertEquals(2, g.outgoingEdges("A2").size());
        assertEquals(2, g.outgoingEdges("A3").size());
        assertEquals(0, g.outgoingEdges("A4").size());
        assertEquals(0, g.outgoingEdges("A5").size());
        assertEquals(0, g.outgoingEdges("A6").size());
        assertEquals(0, g.outgoingEdges("A7").size());
        assertEquals(2, g.outgoingEdges("A8").size());
        assertEquals(2, g.outgoingEdges("A9").size());
        assertEquals(0, g.outgoingEdges("A10").size());
        assertEquals(0, g.outgoingEdges("A11").size());
        assertEquals(0, g.outgoingEdges("A12").size());
        assertTrue(g.outgoingEdges("A1").contains(new Edge("A1", "A2")));
        assertTrue(g.outgoingEdges("A1").contains(new Edge("A1", "A7")));
        assertTrue(g.outgoingEdges("A1").contains(new Edge("A1", "A8")));
        assertTrue(g.outgoingEdges("A2").contains(new Edge("A2", "A3")));
        assertTrue(g.outgoingEdges("A2").contains(new Edge("A2", "A6")));
        assertTrue(g.outgoingEdges("A3").contains(new Edge("A3", "A4")));
        assertTrue(g.outgoingEdges("A3").contains(new Edge("A3", "A5")));
        assertFalse(g.outgoingEdges("A6").contains(new Edge("A6", "A7")));
        assertTrue(g.outgoingEdges("A8").contains(new Edge("A8", "A9")));
        assertTrue(g.outgoingEdges("A8").contains(new Edge("A8", "A12")));
        assertTrue(g.outgoingEdges("A9").contains(new Edge("A9", "A10")));
        assertTrue(g.outgoingEdges("A9").contains(new Edge("A9", "A11")));
        // neighbors
        assertEquals(3, g.neighbors("A1").size());
        assertEquals(2, g.neighbors("A2").size());
        assertEquals(2, g.neighbors("A3").size());
        assertEquals(0, g.neighbors("A4").size());
        assertEquals(0, g.neighbors("A5").size());
        assertEquals(0, g.neighbors("A6").size());
        assertEquals(0, g.neighbors("A7").size());
        assertEquals(2, g.neighbors("A8").size());
        assertEquals(2, g.neighbors("A9").size());
        assertEquals(0, g.neighbors("A10").size());
        assertEquals(0, g.neighbors("A11").size());
        assertEquals(0, g.neighbors("A12").size());
        assertTrue(g.neighbors("A1").contains("A2"));
        assertTrue(g.neighbors("A1").contains("A7"));
        assertTrue(g.neighbors("A1").contains("A8"));
        assertTrue(g.neighbors("A2").contains("A3"));
        assertTrue(g.neighbors("A2").contains("A6"));
        assertTrue(g.neighbors("A3").contains("A4"));
        assertTrue(g.neighbors("A3").contains("A5"));
        assertFalse(g.neighbors("A8").contains("A1"));
        assertTrue(g.neighbors("A8").contains("A9"));
        assertTrue(g.neighbors("A8").contains("A12"));
        assertTrue(g.neighbors("A9").contains("A10"));
        assertTrue(g.neighbors("A9").contains("A11"));
        assertFalse(g.neighbors("A9").contains("A8"));
        assertFalse(g.neighbors("A10").contains("A9"));
        assertFalse(g.neighbors("A10").contains("A11"));
        assertFalse(g.neighbors("A11").contains("A9"));
        // transpose
        DirectedGraph gT = g.transpose();
        assertEquals(0, gT.outgoingEdges("A1").size());
        assertEquals(1, gT.outgoingEdges("A2").size());
        assertEquals(1, gT.outgoingEdges("A3").size());
        assertEquals(1, gT.outgoingEdges("A4").size());
        assertEquals(1, gT.outgoingEdges("A5").size());
        assertEquals(1, gT.outgoingEdges("A6").size());
        assertEquals(1, gT.outgoingEdges("A7").size());
        assertEquals(1, gT.outgoingEdges("A8").size());
        assertEquals(1, gT.outgoingEdges("A9").size());
        assertEquals(1, gT.outgoingEdges("A10").size());
        assertEquals(1, gT.outgoingEdges("A11").size());
        assertEquals(1, gT.outgoingEdges("A12").size());
        assertTrue(gT.outgoingEdges("A2").contains(new Edge("A2", "A1")));
        assertTrue(gT.outgoingEdges("A3").contains(new Edge("A3", "A2")));
        assertTrue(gT.outgoingEdges("A4").contains(new Edge("A4", "A3")));
        assertTrue(gT.outgoingEdges("A5").contains(new Edge("A5", "A3")));
        assertTrue(gT.outgoingEdges("A6").contains(new Edge("A6", "A2")));
        assertTrue(gT.outgoingEdges("A7").contains(new Edge("A7", "A1")));
        assertTrue(gT.outgoingEdges("A8").contains(new Edge("A8", "A1")));
        assertTrue(gT.outgoingEdges("A9").contains(new Edge("A9", "A8")));
        assertTrue(gT.outgoingEdges("A10").contains(new Edge("A10", "A9")));
        assertTrue(gT.outgoingEdges("A11").contains(new Edge("A11", "A9")));
        assertTrue(gT.outgoingEdges("A12").contains(new Edge("A12", "A8")));
        assertEquals(0, gT.neighbors("A1").size());
        assertEquals(1, gT.neighbors("A2").size());
        assertEquals(1, gT.neighbors("A3").size());
        assertEquals(1, gT.neighbors("A4").size());
        assertEquals(1, gT.neighbors("A5").size());
        assertEquals(1, gT.neighbors("A6").size());
        assertEquals(1, gT.neighbors("A7").size());
        assertEquals(1, gT.neighbors("A8").size());
        assertEquals(1, gT.neighbors("A9").size());
        assertEquals(1, gT.neighbors("A10").size());
        assertEquals(1, gT.neighbors("A11").size());
        assertEquals(1, gT.neighbors("A12").size());
        assertTrue(gT.neighbors("A2").contains("A1"));
        assertTrue(gT.neighbors("A3").contains("A2"));
        assertTrue(gT.neighbors("A4").contains("A3"));
        assertTrue(gT.neighbors("A5").contains("A3"));
        assertTrue(gT.neighbors("A6").contains("A2"));
        assertTrue(gT.neighbors("A7").contains("A1"));
        assertTrue(gT.neighbors("A8").contains("A1"));
        assertTrue(gT.neighbors("A9").contains("A8"));
        assertTrue(gT.neighbors("A10").contains("A9"));
        assertTrue(gT.neighbors("A11").contains("A9"));
        assertTrue(gT.neighbors("A12").contains("A8"));
        // bidirectional
        DirectedGraph gB = g.bidirectional();
        assertEquals(3, gB.outgoingEdges("A1").size());
        assertEquals(3, gB.outgoingEdges("A2").size());
        assertEquals(3, gB.outgoingEdges("A3").size());
        assertEquals(1, gB.outgoingEdges("A4").size());
        assertEquals(1, gB.outgoingEdges("A5").size());
        assertEquals(1, gB.outgoingEdges("A6").size());
        assertEquals(1, gB.outgoingEdges("A7").size());
        assertEquals(3, gB.outgoingEdges("A8").size());
        assertEquals(3, gB.outgoingEdges("A9").size());
        assertEquals(1, gB.outgoingEdges("A10").size());
        assertEquals(1, gB.outgoingEdges("A11").size());
        assertEquals(1, gB.outgoingEdges("A12").size());
        assertTrue(gB.outgoingEdges("A1").contains(new Edge("A1", "A2")));
        assertTrue(gB.outgoingEdges("A1").contains(new Edge("A1", "A7")));
        assertTrue(gB.outgoingEdges("A1").contains(new Edge("A1", "A8")));
        assertTrue(gB.outgoingEdges("A2").contains(new Edge("A2", "A1")));
        assertTrue(gB.outgoingEdges("A2").contains(new Edge("A2", "A3")));
        assertTrue(gB.outgoingEdges("A2").contains(new Edge("A2", "A6")));
        assertTrue(gB.outgoingEdges("A3").contains(new Edge("A3", "A2")));
        assertTrue(gB.outgoingEdges("A3").contains(new Edge("A3", "A4")));
        assertTrue(gB.outgoingEdges("A3").contains(new Edge("A3", "A5")));
        assertTrue(gB.outgoingEdges("A4").contains(new Edge("A4", "A3")));
        assertTrue(gB.outgoingEdges("A5").contains(new Edge("A5", "A3")));
        assertTrue(gB.outgoingEdges("A6").contains(new Edge("A6", "A2")));
        assertTrue(gB.outgoingEdges("A7").contains(new Edge("A7", "A1")));
        assertTrue(gB.outgoingEdges("A8").contains(new Edge("A8", "A1")));
        assertTrue(gB.outgoingEdges("A8").contains(new Edge("A8", "A9")));
        assertTrue(gB.outgoingEdges("A8").contains(new Edge("A8", "A12")));
        assertTrue(gB.outgoingEdges("A8").contains(new Edge("A8", "A1")));
        assertTrue(gB.outgoingEdges("A9").contains(new Edge("A9", "A8")));
        assertTrue(gB.outgoingEdges("A9").contains(new Edge("A9", "A10")));
        assertTrue(gB.outgoingEdges("A9").contains(new Edge("A9", "A11")));
        assertTrue(gB.outgoingEdges("A9").contains(new Edge("A9", "A8")));
        assertTrue(gB.outgoingEdges("A10").contains(new Edge("A10", "A9")));
        assertTrue(gB.outgoingEdges("A11").contains(new Edge("A11", "A9")));
        assertTrue(gB.outgoingEdges("A12").contains(new Edge("A12", "A8")));
        assertEquals(3, gB.neighbors("A1").size());
        assertEquals(3, gB.neighbors("A2").size());
        assertEquals(3, gB.neighbors("A3").size());
        assertEquals(1, gB.neighbors("A4").size());
        assertEquals(1, gB.neighbors("A5").size());
        assertEquals(1, gB.neighbors("A6").size());
        assertEquals(1, gB.neighbors("A7").size());
        assertEquals(3, gB.neighbors("A8").size());
        assertEquals(3, gB.neighbors("A9").size());
        assertEquals(1, gB.neighbors("A10").size());
        assertEquals(1, gB.neighbors("A11").size());
        assertEquals(1, gB.neighbors("A12").size());
        assertTrue(gB.neighbors("A1").contains("A2"));
        assertTrue(gB.neighbors("A1").contains("A7"));
        assertTrue(gB.neighbors("A1").contains("A8"));
        assertTrue(gB.neighbors("A2").contains("A1"));
        assertTrue(gB.neighbors("A2").contains("A3"));
        assertTrue(gB.neighbors("A2").contains("A6"));
        assertTrue(gB.neighbors("A3").contains("A2"));
        assertTrue(gB.neighbors("A3").contains("A4"));
        assertTrue(gB.neighbors("A3").contains("A5"));
        assertTrue(gB.neighbors("A4").contains("A3"));
        assertTrue(gB.neighbors("A5").contains("A3"));
        assertTrue(gB.neighbors("A6").contains("A2"));
        assertTrue(gB.neighbors("A7").contains("A1"));
        assertTrue(gB.neighbors("A8").contains("A1"));
        assertTrue(gB.neighbors("A8").contains("A9"));
        assertTrue(gB.neighbors("A8").contains("A12"));
        assertTrue(gB.neighbors("A8").contains("A1"));
        assertTrue(gB.neighbors("A9").contains("A8"));
        assertTrue(gB.neighbors("A9").contains("A10"));
        assertTrue(gB.neighbors("A9").contains("A11"));
        assertTrue(gB.neighbors("A9").contains("A8"));
        assertTrue(gB.neighbors("A10").contains("A9"));
        assertTrue(gB.neighbors("A11").contains("A9"));
        assertTrue(gB.neighbors("A12").contains("A8"));
    }

    @Test
    void graph3 () {
        DirectedGraph g = new DirectedGraph();
        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        HashMap<String, Set<Edge>> table = g.getAdjacencyLists();

        DirectedGraph copy = g.copy();
        HashMap<String, Set<Edge>> copyTable = copy.getAdjacencyLists();

        table.put("A", new HashSet<>());
        assertEquals(0, table.get("A").size());
        assertEquals(2, copyTable.get("A").size());

        copy.removeEdge(new Edge("A", "B"));
        assertEquals(1, copyTable.get("A").size());
        copy.removeEdge(new Edge("A", "B"));
        assertEquals(1, copyTable.get("A").size());
    }
}
