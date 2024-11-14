import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DirectedGraph {
    private final @NotNull String name;
    private final @NotNull Set<String> nodes;
    private final @NotNull HashMap<String,Set<Edge>> adjacencyLists;

    public DirectedGraph (@NotNull String name) {
        this.name = name;
        this.nodes = new HashSet<>();
        this.adjacencyLists = new HashMap<>();
    }

    public DirectedGraph(@NotNull String name, @NotNull HashMap<String,Set<Edge>> adjacencyLists) {
        this.name = name;
        this.nodes = adjacencyLists.keySet();
        this.adjacencyLists = adjacencyLists;
    }

    public @NotNull String getName () { return name; }

    public @NotNull Set<String> getNodes () { return nodes; }

    public @NotNull HashMap<String,Set<Edge>> getAdjacencyLists () { return adjacencyLists; }

    public @NotNull Set<Edge> outgoingEdges (@NotNull String node) {
        return adjacencyLists.get(node);
    }

    /**
     * Returns the set of neighbors of the given node.
     */
    public @NotNull Set<String> neighbors(@NotNull String node) {
        Set<Edge> edges = adjacencyLists.get(node);
        Set<String> neighbors = new HashSet<>();

        for(Edge edge : edges){
            neighbors.add(edge.destination());
        }

        return neighbors;
    }

    public void addEdge (@NotNull Edge edge) {
        addEdge(edge.source(), edge.destination());
    }

    /**
     * Adds an edge to the graph. If the nodes do not already exist in the graph,
     * they are added to the set of nodes. The edge is then added to the adjacency list of
     * the source node.
     * <p>
     * Important note. Every node that exists in the set of nodes must be key
     * in the adjacencyLists map even if it has no outgoing edges.
     */
    public void addEdge (@NotNull String source, @NotNull String destination) {
        nodes.add(source);
        nodes.add(destination);

        if(!adjacencyLists.containsKey(source)){
            adjacencyLists.put(source, new HashSet<>());
        }

        if(!adjacencyLists.containsKey(destination)){
            adjacencyLists.put(destination, new HashSet<>());
        }

        adjacencyLists.get(source).add(new Edge(source, destination));
    }

    /**
     * Returns the transpose of the graph. In more detail, the method
     * creates a new graph with the same nodes as the original graph, but
     * with the edges flipped. If there is an edge from A to B in the original
     * graph, then there is an edge from B to A in the transposed graph.
     */
    public @NotNull DirectedGraph transpose () {
        HashMap<String, Set<Edge>> transposed = new HashMap<>();

        for(String key : adjacencyLists.keySet()){
            if(!transposed.containsKey(key)){
                transposed.put(key, new HashSet<>());
            }

            adjacencyLists.get(key).forEach((x) -> {
                Edge newEdge = x.flip();

                if(!transposed.containsKey(newEdge.source())){
                    transposed.put(newEdge.source(), new HashSet<>());
                }

                transposed.get(newEdge.source()).add(newEdge);
            });
        }

        return new DirectedGraph(name, transposed);
    }
}
