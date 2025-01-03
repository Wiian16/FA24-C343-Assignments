package graph.withweight.traversal;

import graph.noweight.Edge;
import graph.noweight.Path;
import graph.noweight.traversal.iter.BFS;
import graph.withweight.Weight;
import graph.withweight.WeightedDirectedGraph;
import graph.withweight.WeightedPath;

import java.util.HashMap;

/**
 * TIER V
 * <p>
 * Imagine pouring water into a node of a graph. The water flows from that node
 * along the edges. Obviously the amount of water going into a node must equal the
 * amount of water going out of a node. The edges have a maximum capacity of
 * water that can flow through them. The maximum flow is the maximum amount of
 * water that can flow from the source to the destination.
 * <p>
 * The Ford-Fulkerson algorithm (see below) will compute the maximum flow.
 */

public class MaximumFlow {
    private final WeightedDirectedGraph graph;
    private final String source, destination;
    private Weight maxFlow;

    public MaximumFlow(WeightedDirectedGraph graph, String source, String destination) {
        this.graph = graph.copy();
        this.source = source;
        this.destination = destination;
        this.maxFlow = Weight.ZERO;
    }

    public int getMaxFlow() { return maxFlow.value(); }
    public WeightedDirectedGraph getResidual() { return graph; }

    /**
     * We are given a path and a certain amount currentFlow. The method
     * updates the residual graph in two ways: it subtracts currentFlow from the
     * weight of every edge in the path (removing the edge if the weight becomes 0),
     * and for every edge in the given path, it inserts a flipped version with
     * weight currentFlow.
     */
    public void updateResidual (Path path, Weight currentFlow) {
        WeightedDirectedGraph residual = getResidual();

        for(Edge edge : path.edges()){
            residual.subtractEdgeWeight(edge, currentFlow);

            if(residual.getWeights().get(edge).value() == 0){
                residual.removeEdge(edge);
            }
            else if(residual.getAllEdges().contains(edge.flip())){
                Weight newWeight = residual.getWeights().get(edge.flip()).add(currentFlow);
                residual.getWeights().put(edge.flip(), newWeight);
            }
            else{
                residual.insertEdge(edge.flip(), currentFlow);
            }
        }

        maxFlow = maxFlow.add(currentFlow);
    }

    /**
     * Uses BFS to find a path from source to destination. If no path is found,
     * the method throws a NoPathE exception.
     */
    WeightedPath bfsPath (String start, String end) throws NoPathE{
        BFS bfs = new BFS(getResidual(), start);
        bfs.iterativeTraversal();
        HashMap<String, String> prevNodes = bfs.getPreviousNodes();

        if(!prevNodes.containsKey(end)){
            throw new NoPathE();
        }

        WeightedPath path = getPath(prevNodes, destination);

        if(!getResidual().getAllEdges().containsAll(path.edges())){
            throw new NoPathE();
        }

        return path;
    }

    WeightedPath getPath(HashMap<String, String> previousNodes, String destination){
        if(!previousNodes.containsKey(destination)){
            return new WeightedPath();
        }

        WeightedPath path = getPath(previousNodes, previousNodes.get(destination));

        Edge edge = new Edge(previousNodes.get(destination), destination);
        path.add(edge, graph.getWeights().get(edge));

        return path;
    }

    /**
     * Computes the maximum flow from source to destination. The algorithm
     * works by iterating over the residual graph.
     * <p>
     * We start with a residual graph which is a copy of the current graph.
     * We compute a path from the source to the destination in that
     * residual graph (any path is fine; we use BFS to find the path).
     * The "current flow" is the minimum weight along that path.
     * <p>
     * We then update the residual graph by subtracting the current flow
     * from the weight of each edge in the path and adding a flipped edge
     * with weight current flow.
     * <p>
     * We repeat until there is no possible path in the residual graph.
     */
    public void runMaxFlow() {
        while(true){
            WeightedPath path;
            try{
                path = bfsPath(source, destination);
            }
            catch(NoPathE e){
                break;
            }

            updateResidual(path, path.minWeight());
        }
    }

    public static class NoPathE extends Exception{}
}
