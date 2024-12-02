package graph.withweight.traversal;

import graph.withweight.Weight;

import java.util.*;

/**
 * TIER III
 * <p>
 * An implementation of a heap using the arraylist representation of a binary tree.
 * In addition to maintaining the nodes in a list, we maintain an inverse map
 * from nodes to their indices in the list. This allows us to find the parent
 * of a node, the children of a node, and to swap two nodes in the list. We also
 * maintain a map from nodes to their weights which is used for comparisons.
 * <p>
 */
public class HeapCollection extends WeightedNodeCollection {
    private int size;
    private final List<String> nodes;
    private final HashMap<String, Weight> weights;
    private final HashMap<String,Integer> indices;

    /**
     * Initially all the weights are infinity. So the elements can be in
     * any order in the array.
     */
    public HeapCollection(Set<String> nodes) {
        this.size = nodes.size();
        this.nodes = new ArrayList<>(nodes);
        this.weights = new HashMap<>();
        this.indices = new HashMap<>();
        for (String node : nodes) this.weights.put(node, Weight.INFINITY);
        for (int i = 0; i < size; i++) indices.put(this.nodes.get(i), i);
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public String get() {
        return extractMin();
    }
    public void add(String node) {
        insert(node, Weight.INFINITY);
    }
    public Weight getWeight(String node) {
        return weights.get(node);
    }

    /**
     * The caller should guarantee that the new weight is less than the current one.
     * So the only thing we need to do is to move the node up in the heap.
     */
    public void setWeight(String node, Weight w) {
        assert w.compareTo(weights.get(node)) < 0;
        weights.put(node, w);
        moveUp(node);
    }

    public Optional<String> getParent(String n) {
        int parentIndex = (indices.get(n) - 1) / 2;
        if (parentIndex <= 0) return Optional.empty();
        return Optional.of(nodes.get(parentIndex));
    }

    public Optional<String> getLeftChild(String n) {
        int childIndex = 2 * indices.get(n) + 1;
        if (childIndex >= size) return Optional.empty();
        return Optional.of(nodes.get(childIndex));
    }

    public Optional<String> getRightChild(String n) {
        int childIndex = 2 * indices.get(n) + 2;
        if (childIndex >= size) return Optional.empty();
        return Optional.of(nodes.get(childIndex));
    }

    public Optional<String> getMinChild(String n) {
        Optional<String> leftChild = getLeftChild(n);
        Optional<String> rightChild = getRightChild(n);
        if (rightChild.isEmpty()) return leftChild;
        assert leftChild.isPresent();
        Weight leftWeight = weights.get(leftChild.get());
        Weight rightWeight = weights.get(rightChild.get());
        return leftWeight.compareTo(rightWeight) < 0 ? leftChild : rightChild;
    }

    public void swap(String n1, String n2) {
        int p1 = indices.get(n1);
        int p2 = indices.get(n2);
        nodes.set(p1, n2);
        indices.put(n2, p1);
        nodes.set(p2, n1);
        indices.put(n1, p2);
    }

    public void moveDown(String n) {
        throw new Error("TODO");
    }

    public void moveUp(String n) {
        throw new Error("TODO");
    }

    public void insert(String n, Weight w) {
        nodes.add(n);
        weights.put(n, w);
        indices.put(n, size++);
        moveUp(n);
    }

    public String extractMin() {
        throw new Error("TODO");
    }
}
