package linear;

/*
  This is the main task of the assignment.

  We implement a dequeue using an underlying array that can grow as needed.
  We then use this dequeue to implement a list, a stack, and a queue by
  hiding some of the methods.

  The implementation uses three interesting techniques:
  <ul>
  <li> When the array becomes full, it is resized by doubling its size.
  This is a common instance of amortized analysis. The doubling of the
  array is an O(n) operation, but it is done infrequently enough that
  the average cost of adding an element is O(1).
  <li> The array is treated as a circular array. This is because we
  cannot predict the pattern of insertions and deletions. By treating
  the array as circular, the "filled" part of the array can start and
  end at any point.
  <li> We use an array of Optional<E> to clearly and explicitly mark
  empty slots in the array. This way we never have to worry about
  null pointers.
  </ul>

  The general contract is the following:
  <ul>
  <li> We initially choose position 0 be the front of
  the dequeue and the last position to be the back of the
  dequeue. This is arbitrary and we could have chosen any
  other position. As the dequeue grows and shrinks, the
  front and back move around the array. When the array is
  resized we will restore the convention that the first
  position is the front and the last position is the back.
  <li> We maintain the first and last index as follows.
  The first index will point to the slot immediately preceding
  the index at which we insert at the front. The last index
  will point to the slot immediately following the index at
  which we insert at the back.
  <li> All arithmetic is done modulo the capacity of the array.
  So if we have an array of size 5 that is initially empty, the
  first index is 4 (indicating that the next insertion at the
  front will be at index 4+1 mod 5 = 0), and the last index is 0
  (indicating that the next insertion at the back will be at
  (0-1) mod 5 = 4). Note that % in Java is remainder, not modulo.
  </ul>
 */

import exceptions.EmptyDequeE;
import interfaces.DequeI;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

public class ADeque<E> implements DequeI<E> {
    private Optional<E>[] elements;
    private int capacity;
    private int first;
    private int last;
    private int size;

    @SuppressWarnings("unchecked")
    public ADeque(int capacity) {
        this.capacity = capacity;
        elements = (Optional<E>[]) Array.newInstance(Optional.class, capacity);
        Arrays.fill(elements, Optional.empty());
        first = this.capacity - 1;
        last = 0;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public @NotNull E first() throws EmptyDequeE {
        if(isEmpty()){
            throw new EmptyDequeE();
        }

        return elements[first].get(); //todo: check for present, what should it throw?
    }

    public @NotNull E last() throws EmptyDequeE {
        if(isEmpty()){
            throw new EmptyDequeE();
        }

        return elements[last].get(); //todo present check, throws?
    }

    public void addFirst(@NotNull E elem) {
        // Remember to resize the array when it is full
        if(size() == capacity){
            doubleCapacity();
        }

        elements[mod(first + 1, capacity)] = Optional.of(elem);
        size++;
        first = mod(first + 1, capacity);
    }

    public void addLast(@NotNull E elem) {
        // Remember to resize the array when it is full
        if(size() == capacity){
            doubleCapacity();
        }

        elements[mod(last - 1, capacity)] = Optional.of(elem);
        size++;
        last = mod(last - 1, capacity);
    }

    public @NotNull E removeFirst() throws EmptyDequeE {
        // Remember to mark the position as empty
        E elem = elements[first].get();
        elements[first] = Optional.empty();
        size--;
        first = mod(first - 1, capacity);
        return elem;
    }

    public @NotNull E removeLast() throws EmptyDequeE {
        // Remember to mark the position as empty
        E elem = elements[last].get();
        elements[last] = Optional.empty();
        size--;
        last = mod(last + 1, capacity);
        return elem;
    }

    @SuppressWarnings("unchecked")
    public void doubleCapacity () { //todo: elements are getting placed wrong on doubling, probably issue with first + i
        // No matter where the first and last are, we want to
        // copy the elements to a new array where the first
        // element is at index 0 and the last element is at
        // index size-1.
        Optional<E>[] temp = new Optional[capacity * 2];

        for(int i = 0; i < capacity; i++){
            temp[i] = elements[mod(first + i, capacity)];
        }

        elements = temp;

        capacity *= 2;
        first = capacity - 1;
        last = 0;
    }

    private static int mod(int a, int b){
        return (a % b + b) % b;
    }
}
