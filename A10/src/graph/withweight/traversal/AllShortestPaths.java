package graph.withweight.traversal;

import graph.noweight.Edge;
import graph.withweight.Weight;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.WeightedPath;

import java.util.HashMap;

/**
 * TIER IV
 * <p>
 * This class implements Dijkstra's algorithm to find the shortest path from a
 * source node to all other nodes in a graph. This information is represented
 * as a HashMap that maps each node to the previous node along the shortest
 * path back to the source.
 * <p>
 * The traversal itself is implemented by customizing the generic iterative
 * traversal by defining the relaxEdge method. (See below.)
 */

public class AllShortestPaths extends WeightedIterativeGraphTraversal {
    private final HashMap<String,String> previousNodes;

    public AllShortestPaths(WeightedDirectedGraph graph, String source) {
        this(graph, new HeapCollection(graph.getAllNodes()), source);
    }

    public AllShortestPaths(WeightedDirectedGraph graph, WeightedNodeCollection heap, String source) {
        super(graph, heap);
        this.previousNodes = new HashMap<>();
        heap.setWeight(source, new Weight(0));
    }

    /**
     * This method is called whenever a node is visited. It checks all outgoing
     * edges from the node and performs the following actions:
     * <ul>
     *     <li> If the destination node has been visited, we skip it
     *     <li> Otherwise, we calculate the new way of reaching the
     *     destination node by adding the weight of the edge to the weight of
     *     the current node.
     *     <li> If this new way is shorter than the previous way, we update
     *     the weight of the destination node and record the current node as
     *     the predecessor of the destination node along the shortest path so far.
     * </ul>
     */
    public void relaxEdge(Edge edge) {
        if(visited.contains(edge.destination())){
            return;
        }

        Weight newWeight = weights.get(edge).add(collection.getWeight(edge.source()));


    }

    /**
     * This method follows the previousNodes map to reconstruct the shortest
     * path from the source to the given destination node.
     */
    public WeightedPath getPath (String destination) {
        WeightedPath path = new WeightedPath();

        for(String node : previousNodes.keySet()){
            Edge edge = new Edge(previousNodes.get(node), node);

            path.add(edge, weights.get(edge));
        }

        return path;
    }

}
