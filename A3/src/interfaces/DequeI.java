package interfaces;

import exceptions.EmptyDequeE;
import org.jetbrains.annotations.NotNull;

public interface DequeI<E> extends ResizableArray<E> {
    int size();

    boolean isEmpty();

    @NotNull E first() throws EmptyDequeE;

    @NotNull E last() throws EmptyDequeE;

    void addFirst(@NotNull E e);

    void addLast(@NotNull E e);

    @NotNull E removeFirst() throws EmptyDequeE;

    @NotNull E removeLast() throws EmptyDequeE;
}
