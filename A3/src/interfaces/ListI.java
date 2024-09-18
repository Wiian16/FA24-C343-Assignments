package interfaces;

import exceptions.EmptyListE;
import org.jetbrains.annotations.NotNull;

public interface ListI<E> extends ResizableArray<E> {
    int size();

    boolean isEmpty();

    @NotNull E first() throws EmptyListE;

    @NotNull E last() throws EmptyListE;

    void addFirst(@NotNull E elem);

    void addLast(@NotNull E elem);

    @NotNull E removeFirst() throws EmptyListE;
}
