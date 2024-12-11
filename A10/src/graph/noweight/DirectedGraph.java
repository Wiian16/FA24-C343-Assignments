package graph.noweight;

import java.awt.image.DirectColorModel;
import java.security.DigestException;
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
        return !adjacencyLists.containsKey(node) ? Set.of() : adjacencyLists.get(node);
    }

    /**
     * Return a set of the neighbors of the given node.
     */
    public Set<String> neighbors(String node) {
        return !adjacencyLists.containsKey(node) ? Set.of() :
                adjacencyLists.get(node).stream().map(Edge::destination).collect(Collectors.toSet());
    }

    public void insertEdge (Edge edge) {
        insertEdge(edge.source(), edge.destination());
    }

    /**
     * Insert the given edge into the graph.
     */
    public void insertEdge (String source, String destination) {
        if(!nodes.contains(source)){
            nodes.add(source);
            adjacencyLists.put(source, new HashSet<>());
        }

        if(!nodes.contains(destination)){
            nodes.add(destination);
            adjacencyLists.put(destination, new HashSet<>());
        }

        adjacencyLists.get(source).add(new Edge(source, destination));
    }
    /**
     * Remove the given edge from the graph.
     */
    public void removeEdge (Edge edge) {
        if(!nodes.contains(edge.source())){
            throw new Error("Edge not in graph");
        }

        adjacencyLists.get(edge.source()).remove(edge);

        if(adjacencyLists.get(edge.source()).isEmpty()){
            nodes.remove(edge.source());
            adjacencyLists.remove(edge.source());
        }

//        if(adjacencyLists.get(edge.destination()) != null && adjacencyLists.get(edge.destination()).isEmpty()){
//            nodes.remove(edge.destination());
//            adjacencyLists.remove(edge.destination());
//        }
    }

    /**
     * This method should return a new DirectedGraph that is a copy of this one.
     * It is important to create a new copy of the adjacency lists, rather than
     * just returning the existing ones, because otherwise the caller could
     * modify the adjacency lists of the returned graph, which would also modify
     * the original lists.
     */
    public DirectedGraph copy () {
        DirectedGraph dg = new DirectedGraph(nodes);

        for(String key : adjacencyLists.keySet()){
            for(Edge edge : adjacencyLists.get(key)){
                dg.insertEdge(edge);
            }
        }

        return dg;
    }

    /**
     * Return a new DirectedGraph that is the transpose of this graph. In
     * a transpose graph, the direction of every edge is reversed.
     */
    public DirectedGraph transpose () {
        DirectedGraph dg = new DirectedGraph();

        for(String key : adjacencyLists.keySet()){
            for(Edge edge : adjacencyLists.get(key)){
                if(!dg.adjacencyLists.containsKey(edge.destination())){
                    dg.adjacencyLists.put(edge.destination(), new HashSet<>());
                }

                dg.adjacencyLists.get(edge.destination()).add(edge.flip());
            }
        }

        dg.nodes.addAll(nodes);

        return dg;
    }

    /**
     * Return a new DirectedGraph that is the bidirectional version of this
     * graph. In a bidirectional graph, for every edge A -> B, there is also
     * an edge B -> A.
     */
    public DirectedGraph bidirectional () {
        DirectedGraph copy = copy();

        for(String key : adjacencyLists.keySet()){
            for(Edge edge : adjacencyLists.get(key)){
                copy.insertEdge(edge.flip());
            }
        }

        return copy;
    }
}
