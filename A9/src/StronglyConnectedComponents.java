import org.jetbrains.annotations.NotNull;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StronglyConnectedComponents {
    private @NotNull final DirectedGraph graph;

    public StronglyConnectedComponents(@NotNull DirectedGraph graph) {
        this.graph = graph;
    }

    /**
     * Compute the strongly connected components of the graph.
     * The method first computes the topological sort of the graph starting
     * from each node in the graph. It then transposes the graph and performs
     * a depth-first search on the transposed graph starting from the nodes
     * in the order of the topological sort. The result is a map from the
     * exit node of each traversal to the list of nodes visited in that traversal.
     */
    public @NotNull HashMap<String,List<String>> compute () {
        TopologicalSort topo = new TopologicalSort(graph);
        topo.traverse(new ArrayList<>(graph.getNodes()));
        List<String> topoList = topo.getSortedList();
        DirectedGraph transposed = graph.transpose();
        DFS dfs = new DFS(transposed);

        dfs.traverse(new ArrayList<>(topoList));

        return dfs.getAllTraversals();

    }
}
