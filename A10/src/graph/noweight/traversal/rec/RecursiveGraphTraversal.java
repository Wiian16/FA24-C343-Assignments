package graph.noweight.traversal.rec;

import graph.noweight.DirectedGraph;
import graph.noweight.traversal.GraphTraversal;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 *  TIER II
 *  <p>
 *  This class provides a generic recursive graph traversal. The traverseFromSource
 *  visits the given node, and recursively visits all of its neighbors. The traversal
 *  is parameterized by three actions: what to do when we first encounter a node
 *  (enterAction), what to do when we encounter a node again (touchAction), and
 *  what to do when we finish processing a node and all its neighbors (exitAction).
 *  <p>
 *  The traverseFromAllSources method performs the same traversal starting from
 *  each node in the graph. The traversal is parameterized by a fourth action:
 *  what to do when we finish a traversal from a given source, and we are
 *  about to start a traversal from another source.
 */

public abstract class RecursiveGraphTraversal extends GraphTraversal {
    public RecursiveGraphTraversal (DirectedGraph graph) { super(graph); }
    public RecursiveGraphTraversal (DirectedGraph graph, List<String> allNodes) {
        super(graph, allNodes);
    }

    public void enterAction (String node) {
        visited.add(node);
    }
    public void touchAction (String node) {}
    public void exitAction (String node) {}
    public void sourceAction(String node) {
        visited.clear();
    }

    public void traverseFromSource(String current) {
        if(visited.contains(current)){
            touchAction(current);
            return;
        }

        enterAction(current);

        for(String node : graph.neighbors(current)){
            traverseFromSource(node);
        }

        exitAction(current);
    }

    public void traverseFromAllSources() {
        for(String node : allNodes){
            sourceAction(node);
            traverseFromSource(node);
        }
    }
}
