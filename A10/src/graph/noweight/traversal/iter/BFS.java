package graph.noweight.traversal.iter;

import graph.noweight.DirectedGraph;
import graph.noweight.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TIER III
 * <p>
 * An iterative implementation of BFS.
 * <p>
 * The implementation uses the generic iterative traversal customizing it
 * in three ways:
 * <ul>
 *     <li>the node collection is a queue</li>
 *     <li>the enterAction method adds the node to the traversal list</li>
 *     <li>the relaxEdge method adds the destination node to the node collection</li>
 * </ul>
 * In addition to the traversal list, the BFS class also keeps track of the
 * previous node for each node in the traversal.
 */
public class BFS extends IterativeGraphTraversal{
    private final List<String> traversal;
    private final HashMap<String,String> previousNodes;

    public BFS(DirectedGraph graph, String start) {
        super(graph, new QueueCollection(start));
        this.traversal = new ArrayList<>();
        this.previousNodes = new HashMap<>();
    }

    public List<String> getTraversal() {
        return traversal;
    }
    public HashMap<String,String> getPreviousNodes() { return previousNodes; }

    public void enterAction(String node) {
        throw new Error("TODO");
    }
    public void relaxEdge(Edge edge) {
        throw new Error("TODO");
    }
}
