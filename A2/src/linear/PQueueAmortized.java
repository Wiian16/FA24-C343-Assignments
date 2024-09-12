package linear;

/*
    Here we implement a queue using two stacks. The queue is a
    first-in, first-out (FIFO) data structure. Each stack is a
    last-in, first-out (LIFO) data structure.

    To simulate a queue, we use two stacks: 'in' and 'out'. When we
    enqueue an element, we push it onto 'in'. When we dequeue an element,
    we pop it from 'out'. If 'out' is empty, we transfer all elements
    from 'in' to 'out'. This way, the oldest element in the queue is
    always at the top of 'out'.

 */

import exceptions.EmptyQueueE;
import exceptions.EmptyStackE;
import interfaces.QueueI;
import org.jetbrains.annotations.NotNull;

public class PQueueAmortized<E> implements QueueI<E> {
    private final @NotNull PStack<E> in;
    private final @NotNull PStack<E> out;

    public PQueueAmortized() {
        this.in = new PStack<>();
        this.out = new PStack<>();
    }

    public int size() {
        // TODO: Implement this method
        return 0;
    }

    public boolean isEmpty() {
        // TODO: Implement this method
        return false;
    }

    public void enqueue(@NotNull E e) {
        // TODO: Implement this method
    }

    public @NotNull E first() throws EmptyQueueE {
        // TODO: Implement this method
        return null;
    }

    public @NotNull E dequeue() throws EmptyQueueE {
        // TODO: Implement this method
        return null;
    }
}
