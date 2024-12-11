import graph.withweight.Weight;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.traversal.MaximumFlow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTier5Tests {
    @Test
    void simpleGraph(){
        // Create the graph
        WeightedDirectedGraph graph = new WeightedDirectedGraph();

        // Add edges and their capacities
        graph.insertEdge("A", "B", 10);
        graph.insertEdge("A", "C", 5);
        graph.insertEdge("B", "C", 15);
        graph.insertEdge("B", "D", 10);
        graph.insertEdge("C", "D", 10);

        // Initialize MaximumFlow
        MaximumFlow maxFlow = new MaximumFlow(graph, "A", "D");

        // Run the max flow algorithm
        maxFlow.runMaxFlow();

        assertEquals(15, maxFlow.getMaxFlow());
    }
}
