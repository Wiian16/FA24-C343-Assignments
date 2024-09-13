package linear;

/*
    Here we implement a queue using a singly linked list. The queue is a
    first-in, first-out (FIFO) data structure. You can choose which end
    of the list to use as the front of the queue.
 */

import exceptions.EmptyListE;
import exceptions.EmptyQueueE;
import interfaces.PListI;
import interfaces.QueueI;
import org.jetbrains.annotations.NotNull;

public class PQueue<E> implements QueueI<E> {
    private @NotNull PListI<E> elements;

    public PQueue() { elements = new EmptyPList<>(); }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void enqueue(@NotNull E e) {
        elements = elements.addLast(e);
    }

    public @NotNull E first() throws EmptyQueueE {
        try{
            return elements.first();
        }
        catch(EmptyListE e){
            throw new EmptyQueueE();
        }
    }

    public @NotNull E dequeue() throws EmptyQueueE {
        try{
            E temp = elements.first();
            elements = elements.removeFirst();
            return temp;
        }
        catch(EmptyListE e){
            throw new EmptyQueueE();
        }
    }
}
