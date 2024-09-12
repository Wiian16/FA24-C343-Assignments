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
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public @NotNull E first() throws EmptyListE {
        throw new EmptyListE();
    }

    public @NotNull E last() throws EmptyListE {
        throw new EmptyListE();
    }

    public @NotNull PListI<E> addFirst(@NotNull E elem) {
        return new ConsPList<>(elem, new EmptyPList<>());
    }

    public @NotNull PListI<E> addLast(@NotNull E elem) {
        return addFirst(elem); //addFirst and addLast do the same thing since the list is empty
    }

    public @NotNull PListI<E> removeFirst() throws EmptyListE {
        throw new EmptyListE();
    }

    public @NotNull String toString() {
        return "[]";
    }

    public boolean equals(Object o) {
        return (o instanceof EmptyPList);
    }

    public int hashCode() { return 0; }
}
