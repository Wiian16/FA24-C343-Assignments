package interfaces;

import exceptions.EmptyStackE;
import org.jetbrains.annotations.NotNull;

public interface StackI<E> {

    int size();

    boolean isEmpty();

    void push(@NotNull E e);

    @NotNull E top() throws EmptyStackE;

    @NotNull E pop() throws EmptyStackE;
}
