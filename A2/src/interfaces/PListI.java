package interfaces;

import exceptions.EmptyListE;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface PListI<E> {
    int size();

    boolean isEmpty();

    @NotNull E first() throws EmptyListE;

    @NotNull E last() throws EmptyListE;

    @Contract("_ -> new")
    @NotNull PListI<E> addFirst(@NotNull E elem);

    @Contract("_ -> new")
    @NotNull PListI<E> addLast(@NotNull E elem);

    @NotNull PListI<E> removeFirst() throws EmptyListE;
}
