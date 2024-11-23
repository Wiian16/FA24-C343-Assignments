package graph.withweight;

import graph.noweight.DirectedGraph;
import graph.noweight.Edge;

import java.util.*;

/**
 * TIER III
 * <p>
 * This class extended DirectedGraph with information about the weights of
 * the edges. The weights are maintained separately from the adjacency lists
 * in a HashMap.
 */
public class WeightedDirectedGraph extends DirectedGraph {
    private final HashMap<Edge,Weight> weights;

    // Constructors

    public WeightedDirectedGraph() {
        super();
        this.weights = new HashMap<>();
    }

    public WeightedDirectedGraph(Set<String> nodes) {
        super(nodes);
        this.weights = new HashMap<>();
    }

    public WeightedDirectedGraph (DirectedGraph graph, HashMap<Edge,Weight> weights) {
        super(graph);
        this.weights = weights;
    }

    // Getters

    public HashMap<Edge,Weight> getWeights() {
        return weights;
    }

    /**
     * Subtract the given weight from the weight of the given edge. If the result is zero,
     * remove the edge from the graph.
     */
    public void subtractEdgeWeight (Edge edge, Weight diff) {
        throw new Error("TODO");
    }

    /**
     * Insert the given edge.
     */
    public void insertEdge (Edge edge, Weight weight) {
        throw new Error("TODO");
    }

    public void insertEdge (String source, String destination, int weight) {
        insertEdge(new Edge(source, destination), new Weight(weight));
    }

    /**
     * This method should return a new WeightedDirectedGraph that is a copy of this one.
     * It is important to create a new copy of the adjacency lists and weights, rather than
     * just returning the existing ones, because otherwise the caller could modify the
     * adjacency lists or weights of the returned graph, which would also modify
     * the original graph.
     */
    public WeightedDirectedGraph copy () {
        throw new Error("TODO");
    }

    /**
     * Return a new WeightedDirectedGraph that is the bidirectional version of this graph.
     */
    public WeightedDirectedGraph bidirectional () {
        throw new Error("TODO");
    }

    public boolean equals (Object o) {
        if (o instanceof WeightedDirectedGraph other) {
            return adjacencyLists.equals(other.adjacencyLists) && weights.equals(other.weights);
        }
        return false;
    }
}
