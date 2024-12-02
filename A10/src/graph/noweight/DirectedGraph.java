package graph.noweight;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TIER I
 * <p>
 * This class represents a directed graph. The graph is represented as a
 * collection of nodes and a mapping from each node to its outgoing edges.
 */

public class DirectedGraph {
    protected final Set<String> nodes;
    protected final HashMap<String,Set<Edge>> adjacencyLists;

    // Constructors

    public DirectedGraph() {
        this.nodes = new HashSet<>();
        this.adjacencyLists = new HashMap<>();
    }

    public DirectedGraph(Set<String> nodes) {
        this.nodes = nodes;
        this.adjacencyLists = new HashMap<>();
        for (String node : nodes) adjacencyLists.put(node, new HashSet<>());
    }

    public DirectedGraph (DirectedGraph graph) {
        this.nodes = graph.nodes;
        this.adjacencyLists = graph.adjacencyLists;
    }

    // Getters

    public Set<String> getAllNodes() { return nodes; }

    public Set<Edge> getAllEdges () {
        return adjacencyLists.values().stream().flatMap(Set::stream).collect(Collectors.toSet());
    }

    public HashMap<String,Set<Edge>> getAdjacencyLists () { return adjacencyLists; }

    /**
     * Return a set of the outgoing edges from the given node.
     */
    public Set<Edge> outgoingEdges (String node) {
        throw new Error("TODO");
    }

    /**
     * Return a set of the neighbors of the given node.
     */
    public Set<String> neighbors(String node) {
        throw new Error("TODO");
    }

    public void insertEdge (Edge edge) {
        insertEdge(edge.source(), edge.destination());
    }

    /**
     * Insert the given edge into the graph.
     */
    public void insertEdge (String source, String destination) {
      throw new Error("TODO");
    }
    /**
     * Remove the given edge from the graph.
     */
    public void removeEdge (Edge edge) {
        throw new Error("TODO");
    }

    /**
     * This method should return a new DirectedGraph that is a copy of this one.
     * It is important to create a new copy of the adjacency lists, rather than
     * just returning the existing ones, because otherwise the caller could
     * modify the adjacency lists of the returned graph, which would also modify
     * the original lists.
     */
    public DirectedGraph copy () {
        throw new Error("TODO");
    }

    /**
     * Return a new DirectedGraph that is the transpose of this graph. In
     * a transpose graph, the direction of every edge is reversed.
     */
    public DirectedGraph transpose () {
        throw new Error("TODO");
    }

    /**
     * Return a new DirectedGraph that is the bidirectional version of this
     * graph. In a bidirectional graph, for every edge A -> B, there is also
     * an edge B -> A.
     */
    public DirectedGraph bidirectional () {
        throw new Error("TODO");
    }
}
