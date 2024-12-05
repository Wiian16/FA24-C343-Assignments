package graph.withweight.traversal;

import graph.noweight.traversal.iter.NodeCollection;
import graph.withweight.Weight;

public abstract class WeightedNodeCollection extends NodeCollection {
    public abstract Weight getWeight(String node);
    public abstract void setWeight(String node, Weight weight);
}
