import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DFS {
    private final @NotNull DirectedGraph graph;
    private final @NotNull HashSet<String> visited;
    private final @NotNull List<String> currentTraversal;
    private final @NotNull HashMap<String, List<String>> allTraversals;

    public DFS(@NotNull DirectedGraph graph) {
        this.graph = graph;
        this.visited = new HashSet<>();
        this.currentTraversal = new ArrayList<>();
        this.allTraversals = new HashMap<>();
    }

    public void reset () {
        visited.clear();
        currentTraversal.clear();
        allTraversals.clear();
    }

    public @NotNull List<String> getTraversal() {
        return currentTraversal;
    }

    public @NotNull HashMap<String, List<String>> getAllTraversals() {
        return allTraversals;
    }

    /**
     * Traverse the graph starting from the given node.
     * When a node is visited, it is added to the current traversal,
     * and the method is called recursively on all its neighbors.
     */
    public void traverse (@NotNull String source) {
        if(visited(source)){
            return;
        }

        currentTraversal.add(source);
        for(String node : graph.neighbors(source)){
            traverse(node);
        }
    }


    private boolean visited(String node){
        if(currentTraversal.contains(node)){
            return true;
        }

        for(List<String> pastTraversal : allTraversals.values()){
            if(pastTraversal.contains(node)){
                return true;
            }
        }

        return false;
    }

    /**
     * Traverse the graph starting from the given nodes. After traversing
     * a node, the traversal is stored in the allTraversals map, and
     * the currentTraversal list is cleared to prepare for the next traversal.
     */
    public void traverse (@NotNull List<String> sources) {
        sources.forEach((x) -> {
            traverse(x);
            allTraversals.put(x, new ArrayList<>(currentTraversal));
            currentTraversal.clear();
        });
    }
}


