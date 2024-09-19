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
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void enqueue(@NotNull E e) {
        elements.addLast(e);
    }


    public @NotNull E first() throws EmptyQueueE {
        try {
            return elements.first();
        }
        catch(EmptyDequeE e) {
            throw new EmptyQueueE();
        }
    }

    public @NotNull E dequeue() throws EmptyQueueE {
        try {
            return elements.removeFirst();
        }
        catch(EmptyDequeE e) {
            throw new EmptyQueueE();
        }
    }

    public void doubleCapacity() {
        elements.doubleCapacity();
    }
}
