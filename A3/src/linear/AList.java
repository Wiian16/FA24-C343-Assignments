package linear;

/*
    We implement a resizable list using a resizable dequeue.

    The dequeue has a richer set of operations than a list so we just need to
    select the appropriate operations.

    Additionally, we want to hide the fact that we are using a dequeue internally
    so we catch dequeue-exceptions and rethrow them as list-exceptions.
 */

import exceptions.EmptyDequeE;
import exceptions.EmptyListE;
import interfaces.DequeI;
import interfaces.ListI;
import org.jetbrains.annotations.NotNull;

public class AList<E> implements ListI<E> {
    private final @NotNull DequeI<E> elements;

    public AList(int capacity) {
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

    public @NotNull E first() throws EmptyListE {
        // TODO: Implement this method
        return null;
    }

    public @NotNull E last() throws EmptyListE {
        // TODO: Implement this method
        return null;
    }

    public void addFirst(@NotNull E elem) {
        // TODO: Implement this method
    }

    public void addLast(@NotNull E elem) {
        // TODO: Implement this method
    }

    public @NotNull E removeFirst() throws EmptyListE {
        // TODO: Implement this method
        return null;
    }

    public void doubleCapacity() {
        // TODO: Implement this method
    }
}
