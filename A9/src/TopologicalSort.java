import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TopologicalSort {
    private @NotNull final DirectedGraph graph;
    private @NotNull final HashSet<String> visited;
    private @NotNull final List<String> sortedList;

    public TopologicalSort(@NotNull DirectedGraph graph) {
        this.graph = graph;
        this.visited = new HashSet<>();
        this.sortedList = new ArrayList<>();
    }

    public @NotNull List<String> getSortedList() {
        return sortedList;
    }

    /**
     * Traverse the graph starting from the given node. When a node is visited,
     * it is added to the visited set, and the method is called
     * recursively on all its neighbors. Once all the neighbors have been visited,
     * the current node is added to the sortedList.
     */
    public void traverse(@NotNull String current) {
        // TODO: Implement the traverse method
    }

    public void traverse(@NotNull List<String> sources) {
        for (String source : sources) traverse(source);
    }
}
