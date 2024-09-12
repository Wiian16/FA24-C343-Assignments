package linear;

/*
  Here we implement one node of a singly linked list. Each node has
  a first element and a reference to the rest of the list.
 */

import exceptions.EmptyListE;
import interfaces.PListI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

@Unmodifiable
public class ConsPList<E> implements PListI<E> {
    private final @NotNull E first;
    private final @NotNull PListI<E> rest;

    public ConsPList(@NotNull E first, @NotNull PListI<E> rest) {
        this.first = first;
        this.rest = rest;
    }

    public int size() {
        // TODO: Implement this method
        return 0;
    }

    public boolean isEmpty() {
        // TODO: Implement this method
        return false;
    }

    public @NotNull E first() {
        // TODO: Implement this method
        return null;
    }

    public @NotNull E last() {
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

    public @NotNull PListI<E> removeFirst() {
        // TODO: Implement this method
        return null;
    }

    public @NotNull String toString() {
        return "[ " + first + " " + rest.toString().substring(1);
    }

    @SuppressWarnings("rawtypes")
    public boolean equals(Object o) {
        if (o instanceof ConsPList that) {
            return this.first.equals(that.first) && this.rest.equals(that.rest);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return 31 * first.hashCode() + rest.hashCode();
    }
}
