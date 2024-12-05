package graph.withweight.traversal;

import graph.noweight.Edge;
import graph.noweight.traversal.iter.IterativeGraphTraversal;
import graph.withweight.Weight;
import graph.withweight.WeightedDirectedGraph;

import java.util.HashMap;

/**
 * This is a version of IterativeGraphTraversal that also holds information
 * about the weights of the edges.
 */
public abstract class WeightedIterativeGraphTraversal extends IterativeGraphTraversal {
    protected final HashMap<Edge, Weight> weights;
    protected final WeightedNodeCollection collection;

    public WeightedIterativeGraphTraversal(WeightedDirectedGraph graph, WeightedNodeCollection collection) {
        super(graph, collection);
        this.weights = graph.getWeights();
        this.collection = collection;
    }

}
