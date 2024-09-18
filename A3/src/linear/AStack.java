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
        // TODO: Implement this method
        return 0;
    }

    public boolean isEmpty() {
        // TODO: Implement this method
        return false;
    }

    public void push(@NotNull E e) {
        // TODO: Implement this method
    }

    public @NotNull E top() throws EmptyStackE {
       // TODO: Implement this method
        return null;
    }

    public @NotNull E pop() throws EmptyStackE {
        // TODO: Implement this method
        return null;
    }

    public void doubleCapacity() {
        // TODO: Implement this method
    }
}
