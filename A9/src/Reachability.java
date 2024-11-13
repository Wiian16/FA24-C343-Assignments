import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class Reachability {
    private @NotNull final DirectedGraph graph;
    private @NotNull final HashSet<String> visited;

    public Reachability (@NotNull DirectedGraph graph) {
        this.graph = graph;
        this.visited = new HashSet<>();
    }

    /**
     * Check if the given node is reachable from the starting node that
     * was used in the most recent traversal.
     */
    public boolean isReachable (@NotNull String node) {
        // TODO: Implement the isReachable method
        return false;
    }

    /**
     * Traverse the graph starting from the given node.
     * When a node is visited, it is added to the visited set,
     * and the method is called recursively on all its neighbors.
     */
    public void traverse (@NotNull String current) {
        // TODO: Implement the traverse method
    }
}
