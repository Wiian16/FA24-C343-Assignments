import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NonEmptyNode<E> extends Node<E>{
    private final @NotNull E element;
    private final @NotNull Node<E> left, right;
    private final int height;
    private final int size;

    public NonEmptyNode(@NotNull E e, @NotNull Node<E> left, @NotNull Node<E> right) {
        this.element = e;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(left.height(), right.height());
        this.size = 1 + left.size() + right.size();
        left.setParent(this);
        right.setParent(this);
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height;
    }

    public @NotNull E element() {
        return element;
    }

    public @NotNull Node<E> leftNode() {
        return left;
    }

    public @NotNull Node<E> rightNode() {
        return right;
    }

    /*
     * The next three methods perform a pre-order, in-order, and post-order traversal of the tree.
     */

    public @NotNull List<E> preOrder() {
        List<E> result = new ArrayList<>();

        result.add(element);
        result.addAll(left.preOrder());
        result.addAll(right.preOrder());

        return result;
    }

    public @NotNull List<E> inOrder() {
        List<E> result = new ArrayList<>(left.inOrder());

        result.add(element);
        result.addAll(right.inOrder());

        return result;
    }

    public @NotNull List<E> postOrder() {
        List<E> result = new ArrayList<>(left.postOrder());

        result.addAll(right.postOrder());
        result.add(element);

        return result;
    }

    // Printable interface
    public TreePrinter.PrintableNode getLeft() { return left.isEmpty() ? null : left; }
    public TreePrinter.PrintableNode getRight() { return right.isEmpty() ? null : right; }
    public String getText() { return String.valueOf(element); }

}
