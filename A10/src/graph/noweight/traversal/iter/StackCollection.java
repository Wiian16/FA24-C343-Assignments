package graph.noweight.traversal.iter;

import java.util.Stack;

/**
 * TIER I
 * <p>
 * Implement a class that extends NodeCollection and uses a Stack to store the
 * nodes. The constructor should take a String and add it to the stack.
 * The methods add and get should push and pop respectively.
 *
 */
public class StackCollection extends NodeCollection {
    private final Stack<String> stack;
    public StackCollection(String start) {
        stack = new Stack<>();
        stack.push(start);
    }
    public boolean isEmpty() { throw new Error("TODO"); }
    public String get() { throw new Error("TODO"); }
    public void add(String node) { throw new Error("TODO"); }
}
