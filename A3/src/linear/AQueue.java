package linear;

/*
    We implement a resizable queue using a resizable dequeue.

    The dequeue has a richer set of operations than a queue so we just need to
    select the appropriate operations.

    Additionally, we want to hide the fact that we are using a dequeue internally
    so we catch dequeue-exceptions and rethrow them as queue-exceptions.
 */

import exceptions.EmptyDequeE;
import exceptions.EmptyQueueE;
import interfaces.DequeI;
import interfaces.QueueI;
import org.jetbrains.annotations.NotNull;

public class AQueue<E> implements QueueI<E> {
    private final @NotNull DequeI<E> elements;

    public AQueue(int capacity) {
        this.elements = new ADeque<>(capacity);
    }

    public int size() {
        // TODO: Implement this method
        return 0;
    }

    public boolean isEmpty() {
        // TODO: Implement this method
        return false;
    }

    public void enqueue(@NotNull E e) {
        // TODO: Implement this method
    }

    public @NotNull E first() throws EmptyQueueE {
        // TODO: Implement this method
        return null;
    }

    public @NotNull E dequeue() throws EmptyQueueE {
        // TODO: Implement this method
        return null;
    }

    public void doubleCapacity() {
        // TODO: Implement this method
    }
}
