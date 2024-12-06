package graph.noweight.traversal.rec;

import graph.noweight.DirectedGraph;
import graph.noweight.traversal.GraphTraversal;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * TIER II
 * <p>
 * This class calculates the strongly connected components of a graph.
 * A strongly connected component is a subset of the nodes in a graph such that
 * every node in the subset can reach every other node in the subset.
 * <p>
 * The algorithm we will use to calculate the strongly connected components is
 * the following. First, we perform a topological sort of the graph from all sources.
 * Then, we perform a depth-first traversal from all sources of the transpose of
 * the graph. In that search we must visit the nodes in the order returned by
 * the topological sorting.
 */
public class StronglyConnectedComponents extends GraphTraversal {

    public StronglyConnectedComponents(DirectedGraph graph) {
        super(graph);
    }

    public HashMap<String,List<String>> computeSCC () { //todo
        HashMap<String, List<String>> scc = new HashMap<>();

        TopologicalSort topo = new TopologicalSort(graph);
        topo.traverseFromAllSources();
        List<String> topoSort = topo.getTraversal();

        DFSrec dfs = new DFSrec(graph.transpose(), topoSort.reversed());
        dfs.traverseFromAllSources();
        HashMap<String, List<String>> dfsMap = dfs.getAllTraversals();

        for(String node : topoSort){
            scc.put(node, dfsMap.get(node));
        }

        return scc;
    }
}
