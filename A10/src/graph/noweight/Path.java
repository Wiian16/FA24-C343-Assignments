package graph.noweight;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * TIER I
 * <p>
 * A path is simply a sequence of edges.
 */
public class Path implements Iterable<Edge> {
    private final List<Edge> edges;

    public Path() {
        this.edges = new ArrayList<>();
    }

    public boolean isEmpty() {
        return edges.isEmpty();
    }
    public List<Edge> edges() {
        return edges;
    }

    /**
     * Reverses the order of the edges in the path. The edges themselves are not
     * flipped, just the order of the edges in the list.
     */
    public void reverse() {
        throw new Error("TODO");
    }

    public void add(Edge edge) {
        edges.add(edge);
    }

    public @NotNull Iterator<Edge> iterator() {
        return edges.iterator();
    }

    public boolean equals(Object other) {
        if (other instanceof Path otherPath) {
            return edges.equals(otherPath.edges);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return edges.hashCode();
    }

    public String toString() {
        return edges.toString();
    }
}
