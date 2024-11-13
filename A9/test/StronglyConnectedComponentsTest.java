import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StronglyConnectedComponentsTest {
    @Test
    void simple () {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> components = scc.compute();
        assertEquals("[A]", components.get("A").toString());
        assertEquals("[B]", components.get("B").toString());
        assertEquals("[C]", components.get("C").toString());
        assertEquals("[D]", components.get("D").toString());
    }

    @Test
    void g1 () {
        DirectedGraph graph = ExampleGraphs.g1();
        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> components = scc.compute();
        assertEquals("[A]", components.get("A").toString());
        assertEquals("[B, E, D]", components.get("B").toString());
        assertEquals("[C]", components.get("C").toString());
        assertEquals("[]", components.get("D").toString());
        assertEquals("[]", components.get("E").toString());
    }

    @Test
    void g3 () {
        DirectedGraph graph = ExampleGraphs.g3();
        StronglyConnectedComponents scc = new StronglyConnectedComponents(graph);
        HashMap<String, List<String>> components = scc.compute();
        assertEquals("[A]", components.get("A").toString());
        assertEquals("[B]", components.get("B").toString());
        assertEquals("[C, J, F, D]", components.get("C").toString());
        assertEquals("[]", components.get("D").toString());
        assertEquals("[E]", components.get("E").toString());
        assertEquals("[]", components.get("F").toString());
        assertEquals("[]", components.get("G").toString());
        assertEquals("[H, I, G]", components.get("H").toString());
        assertEquals("[]", components.get("I").toString());
        assertEquals("[]", components.get("J").toString());

    }

}