package linear;

/*
    Here we implement a queue using a singly linked list. The queue is a
    first-in, first-out (FIFO) data structure. You can choose which end
    of the list to use as the front of the queue.
 */

import exceptions.EmptyListE;
import exceptions.EmptyQueueE;
import interfaces.PListI;
import interfaces.QueueI;
import org.jetbrains.annotations.NotNull;

public class PQueue<E> implements QueueI<E> {
    private @NotNull PListI<E> elements;

    public PQueue() { elements = new EmptyPList<>(); }

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
}
