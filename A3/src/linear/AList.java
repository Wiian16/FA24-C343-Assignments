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
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public @NotNull E first() throws EmptyListE {
        try {
            return elements.first();
        }
        catch(EmptyDequeE e) {
            throw new EmptyListE();
        }
    }

    public @NotNull E last() throws EmptyListE {
        try {
            return elements.last();
        }
        catch(EmptyDequeE e) {
            throw new EmptyListE();
        }
    }

    public void addFirst(@NotNull E elem) {
        elements.addFirst(elem);
    }

    public void addLast(@NotNull E elem) {
        elements.addLast(elem);
    }

    public @NotNull E removeFirst() throws EmptyListE {
        try {
            return elements.removeFirst();
        }
        catch(EmptyDequeE e) {
            throw new EmptyListE();
        }
    }

    public void doubleCapacity() {
        elements.doubleCapacity();
    }
}
