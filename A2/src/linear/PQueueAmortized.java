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
        return in.size() + out.size();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

    public void enqueue(@NotNull E e) {
        in.push(e);
    }

    public @NotNull E first() throws EmptyQueueE {
        if(isEmpty()){ //guard for empty queue
            throw new EmptyQueueE();
        }

        try {
            if(!out.isEmpty()){
                return out.top();
            }

            transfer();
            return out.top();
        }
        catch(EmptyStackE e) {
            throw new EmptyQueueE();
        }
    }

    public @NotNull E dequeue() throws EmptyQueueE {
        if(isEmpty()){
            throw new EmptyQueueE();
        }

        try{
            if(!out.isEmpty()){
                return out.pop();
            }

            transfer();
            return out.pop();
        }
        catch(EmptyStackE e) {
            throw new EmptyQueueE();
        }
    }

    private void transfer() throws EmptyQueueE {
        if(isEmpty()){ //guard for empty queue
            throw new EmptyQueueE();
        }

        try {
            while(!in.isEmpty()){
                out.push(in.pop());
            }
        }
        catch(EmptyStackE e) {
            throw new EmptyQueueE();
        }
    }
}
