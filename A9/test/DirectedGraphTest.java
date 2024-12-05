import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {
    @Test
    void testAddEdge() {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        assertEquals("[A, B, C, D]", graph.getNodes().toString());
        assertEquals("{A=[A->B], B=[B->C], C=[C->D], D=[]}", graph.getAdjacencyLists().toString());
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
        assertEquals("[A->B, A->C, A->D]", graph.outgoingEdges("A").toString());
        assertEquals("[B->C, B->A]", graph.outgoingEdges("B").toString());
        assertEquals("[C->C]", graph.outgoingEdges("C").toString());
        assertEquals("[]", graph.outgoingEdges("D").toString());
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
        assertEquals("[B, C, D]", graph.neighbors("A").toString());
        assertEquals("[C, A]", graph.neighbors("B").toString());
        assertEquals("[C]", graph.neighbors("C").toString());
        assertEquals("[]", graph.neighbors("D").toString());
    }

    @Test
    void testTransposeSimple () {
        DirectedGraph graph = new DirectedGraph("g");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        DirectedGraph transposed = graph.transpose();
        assertEquals("[A, B, C, D]", transposed.getNodes().toString());
        assertEquals("{A=[], B=[B->A], C=[C->B], D=[D->C]}", transposed.getAdjacencyLists().toString());
    }

    @Test
    void testTransposeG3 () {
        DirectedGraph graph = ExampleGraphs.g3();
        DirectedGraph transposed = graph.transpose();
        HashMap<String,Set<Edge>> adjacencyLists = transposed.getAdjacencyLists();
        assertEquals("[A->B, A->E]", adjacencyLists.get("A").toString());
        assertEquals("[]", adjacencyLists.get("B").toString());
        assertEquals("[C->J, C->A]", adjacencyLists.get("C").toString());
        assertEquals("[D->C]", adjacencyLists.get("D").toString());
        assertEquals("[]", adjacencyLists.get("E").toString());
        assertEquals("[F->H, F->D]", adjacencyLists.get("F").toString());
        assertEquals("[G->H, G->B]", adjacencyLists.get("G").toString());
        assertEquals("[H->I, H->A]", adjacencyLists.get("H").toString());
        assertEquals("[I->E, I->G]", adjacencyLists.get("I").toString());
        assertEquals("[J->F]", adjacencyLists.get("J").toString());
    }

}