package graph.noweight.traversal.iter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TIER I
 * <p>
 * Implement a class that extends NodeCollection and uses a Queue to store the
 * nodes. The constructor should take a String and add it to the queue.
 * The methods add and get should enqueue and dequeue respectively.
 *
 */
public class QueueCollection extends NodeCollection {
    private final Queue<String> queue;
    public QueueCollection(String start) {
        this.queue = new LinkedList<>();
        queue.add(start);
    }
    public boolean isEmpty() { throw new Error("TODO"); }
    public String get() { throw new Error("TODO"); }
    public void add(String node) { throw new Error("TODO"); }
}
