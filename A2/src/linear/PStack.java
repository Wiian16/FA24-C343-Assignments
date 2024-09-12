package linear;

/*
    Here we implement a stack using a singly linked list. The stack is a
    last-in, first-out (LIFO) data structure. You can choose which end
    of the list to use as the top of the stack.

 */

import exceptions.EmptyListE;
import exceptions.EmptyStackE;
import interfaces.PListI;
import interfaces.StackI;
import org.jetbrains.annotations.NotNull;

public class PStack<E> implements StackI<E> {
    private @NotNull PListI<E> elements;

    public PStack() {
        this.elements = new EmptyPList<>();
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

    public @NotNull String toString() {
        return elements.toString();
    }

}
