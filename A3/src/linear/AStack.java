package linear;

/*
  We implement a resizable stack using a resizable dequeue.

  The dequeue has a richer set of operations than a stack so we just need to
  select the appropriate operations.

  Additionally, we want to hide the fact that we are using a dequeue internally
  so we catch dequeue-exceptions and rethrow them as stack-exceptions.
 */

import exceptions.EmptyDequeE;
import exceptions.EmptyStackE;
import interfaces.DequeI;
import interfaces.StackI;
import org.jetbrains.annotations.NotNull;

public class AStack<E> implements StackI<E> {
    private @NotNull final DequeI<E> elements;

    public AStack(int capacity) {
        elements = new ADeque<>(capacity);
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void push(@NotNull E e) {
        elements.addFirst(e);
    }

    public @NotNull E top() throws EmptyStackE {
        try {
            return elements.first();
        }
        catch(EmptyDequeE e) {
            throw new EmptyStackE();
        }
    }

    public @NotNull E pop() throws EmptyStackE {
        try {
            return elements.removeFirst();
        }
        catch(EmptyDequeE e) {
            throw new EmptyStackE();
        }
    }

    public void doubleCapacity() {
        elements.doubleCapacity();
    }
}
