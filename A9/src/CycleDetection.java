import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class CycleDetection {
    private final @NotNull DirectedGraph graph;
    private final @NotNull HashSet<String> visited;
    private boolean hasCycle;
    private final @NotNull Set<String> ancestors;

    public CycleDetection(@NotNull DirectedGraph graph) {
        this.graph = graph;
        this.visited = new HashSet<>();
        this.hasCycle = false;
        this.ancestors = new HashSet<>();
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    /**
     * Traverse the graph starting from the given node.
     * If the traversal encounters a node that has already been visited,
     * then it checks if the node is an ancestor of the current node.
     * If it is, then a cycle has been detected.
     */
    public void traverse (@NotNull String current) {
       // TODO: Implement the traverse method
    }
}
