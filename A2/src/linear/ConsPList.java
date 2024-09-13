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

    private final int size;

    public ConsPList(@NotNull E first, @NotNull PListI<E> rest) {
        this.first = first;
        this.rest = rest;

        this.size = 1 + rest.size();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return false;
    }

    public @NotNull E first() {
        return first;
    }

    public @NotNull E last() {
        try{
            return rest.last();
        }
        catch(EmptyListE e){
            return first;
        }
    }

    public @NotNull PListI<E> addFirst(@NotNull E elem) {
        return new ConsPList<>(elem, this);
    }

    public @NotNull PListI<E> addLast(@NotNull E elem) {
        return new ConsPList<>(first, rest.addLast(elem));
    }

    public @NotNull PListI<E> removeFirst() {
        return rest;
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
