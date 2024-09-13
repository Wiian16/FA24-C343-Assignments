package linear;

/*
    Here we implement a stack using a singly linked list. The stack is a
    last-in, first-out (LIFO) data structure. You can choose which end
    of the list to use as the top of the stack.

 */

import exceptions.EmptyListE;
import exceptions.EmptyStackE;
import interfaces.PListI;
import interfaces.StackI;
import org.jetbrains.annotations.NotNull;

public class PStack<E> implements StackI<E> {
    private @NotNull PListI<E> elements;

    public PStack() {
        this.elements = new EmptyPList<>();
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void push(@NotNull E e) {
        elements = elements.addFirst(e);
    }

    public @NotNull E top() throws EmptyStackE {
        try{
            return elements.first();
        }
        catch(EmptyListE e){
            throw new EmptyStackE();
        }
    }

    public @NotNull E pop() throws EmptyStackE {
        try{
            E temp = elements.first();
            elements = elements.removeFirst();
            return temp;
        }
        catch (EmptyListE e){
            throw new EmptyStackE();
        }
    }

    public @NotNull String toString() {
        return elements.toString();
    }

}
