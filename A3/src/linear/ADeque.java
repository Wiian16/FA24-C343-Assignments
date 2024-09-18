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
        // TODO: Implement this method
        return 0;
    }

    public boolean isEmpty() {
        // TODO: Implement this method
        return false;
    }

    public @NotNull E first() throws EmptyDequeE {
        // TODO: Implement this method
        return null;
    }

    public @NotNull E last() throws EmptyDequeE {
        // TODO: Implement this method
        return null;
    }

    public void addFirst(@NotNull E elem) {
        // TODO: Implement this method
        // Remember to resize the array when it is full
    }

    public void addLast(@NotNull E elem) {
        // TODO: Implement this method
        // Remember to resize the array when it is full
    }

    public @NotNull E removeFirst() throws EmptyDequeE {
        // TODO: Implement this method
        // Remember to mark the position as empty
        return null;
    }

    public @NotNull E removeLast() throws EmptyDequeE {
        // TODO: Implement this method
        // Remember to mark the position as empty
        return null;
    }

    @SuppressWarnings("unchecked")
    public void doubleCapacity () {
        // TODO: Implement this method
        // No matter where the first and last are, we want to
        // copy the elements to a new array where the first
        // element is at index 0 and the last element is at
        // index size-1.
    }
}
