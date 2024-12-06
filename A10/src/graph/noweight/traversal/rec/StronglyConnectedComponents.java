package graph.noweight.traversal.rec;

import graph.noweight.DirectedGraph;
import graph.noweight.traversal.GraphTraversal;

import java.util.HashMap;
import java.util.List;
import java.util.function.ToLongBiFunction;

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

    public HashMap<String,List<String>> computeSCC () {
        HashMap<String, List<String>> scc = new HashMap<>();
        DirectedGraph transpose = graph.transpose();

        for(String node : allNodes){
            TopologicalSort topo = new TopologicalSort(graph);
            topo.traverseFromSource(node);
            List<String> topoSort = topo.getTraversal();

            DFSrec dfs = new DFSrec(transpose, topoSort);
            dfs.traverseFromAllSources();
            scc.putAll(dfs.getAllTraversals());
        }

        return scc;
    }

}
