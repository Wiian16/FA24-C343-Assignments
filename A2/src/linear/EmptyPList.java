package linear;

/*
    Here we implement the empty list. The empty list has no elements.
 */

import exceptions.EmptyListE;
import interfaces.PListI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

@Unmodifiable
public class EmptyPList<E> implements PListI<E> {

    public EmptyPList() {}

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

    public @NotNull PListI<E> addFirst(@NotNull E elem) {
        // TODO: Implement this method
        return null;
    }

    public @NotNull PListI<E> addLast(@NotNull E elem) {
        // TODO: Implement this method
        return null;
    }

    public @NotNull PListI<E> removeFirst() throws EmptyListE {
        // TODO: Implement this method
        return null;
    }

    public @NotNull String toString() {
        return "[]";
    }

    public boolean equals(Object o) {
        return (o instanceof EmptyPList);
    }

    public int hashCode() { return 0; }
}
