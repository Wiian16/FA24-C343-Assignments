package interfaces;

import exceptions.EmptyQueueE;
import org.jetbrains.annotations.NotNull;

public interface QueueI<E> extends ResizableArray<E> {

    int size();

    boolean isEmpty();

    void enqueue(@NotNull E e);

    @NotNull E first() throws EmptyQueueE;

    @NotNull E dequeue() throws EmptyQueueE;
}
