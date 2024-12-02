package graph.withweight;

import graph.noweight.Edge;
import graph.noweight.Path;

/**
 * Tier I
 * <p>
 * This class represents a path in a weighted graph. The path is represented as
 * a list of edges. The class also keeps track of the total weight of the path
 * and the minimum weight of any edge in the path.
 */

public class WeightedPath extends Path {
    private Weight totalWeight;
    private Weight minWeight;

    public WeightedPath() {
        super();
        this.totalWeight = Weight.ZERO;
        this.minWeight = Weight.INFINITY;
    }

    public Weight totalWeight() { return totalWeight; }
    public Weight minWeight() { return minWeight; }

    /**
     * Adds the given edge to the end of the path. The total weight is updated
     * by adding the given weight. The minWeight is also updated to the minimum
     * of the existing minWeight and the given weight.
     */
    public void add(Edge edge, Weight w) {
        throw new Error("TODO");
    }

}
