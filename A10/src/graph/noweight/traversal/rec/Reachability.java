package graph.noweight.traversal.rec;

import graph.noweight.DirectedGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * TIER II
 * <p>
 * This class extends RecursiveGraphTraversal to calculate all the nodes that
 * can reach a given node. The result is represented as a HashMap that maps each
 * node to the set of nodes that can reach it.
 * <p>
 * To implement this idea, we perform the same action every time we enter, visit,
 * or exit a node. This action does the following steps:
 * <ul>
 *     <li> Get the set of nodes that can reach the current node. Call it S.
 *     <li> For each neighbor, add the current node and all of the nodes in S
 *     to the set of nodes that can reach the neighbor.
 * </ul>
 * Note that this problem is the opposite of the Reachability problem in the
 * previous assignment. In that problem, we calculated all the nodes that a given
 * node can reach. In this problem, we calculate all the nodes that can reach a
 * given node.
 */
public class Reachability extends RecursiveGraphTraversal {
    private final HashMap<String, Set<String>> table;

    public Reachability(DirectedGraph graph) {
        super(graph);
        this.table = new HashMap<>();
        for(String node : graph.getAllNodes())
            table.put(node, new HashSet<>());
    }

    public HashMap<String, Set<String>> getTable() {
        return table;
    }

    public void enterAction(String node) {
        super.enterAction(node);

        touchAction(node);
    }

    public void touchAction(String node) {

        if(!table.containsKey(node)) {
            table.put(node, new HashSet<>());
        }

        for(String neighbor : graph.neighbors(node)) {
            if(!table.containsKey(neighbor)) {
                table.put(neighbor, new HashSet<>());
            }

            table.get(neighbor).add(node);
            table.get(neighbor).addAll(table.get(node));
        }
    }

    public void exitAction(String node) {}
}
