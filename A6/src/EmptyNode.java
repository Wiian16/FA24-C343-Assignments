import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EmptyNode<E> extends Node<E> {

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return 0;
    }

    public int height() {
        return 0;
    }

    public @NotNull E element() throws NodeNotFoundE {
        throw new NodeNotFoundE();
    }

    public @NotNull Node<E> leftNode() throws NodeNotFoundE {
        throw new NodeNotFoundE();
    }

    public @NotNull Node<E> rightNode() throws NodeNotFoundE {
        throw new NodeNotFoundE();
    }

    public @NotNull List<E> preOrder() {
        return new ArrayList<>();
    }

    public @NotNull List<E> inOrder() {
        return new ArrayList<>();
    }

    public @NotNull List<E> postOrder() {
        return new ArrayList<>();
    }

    // Printable interface
    public TreePrinter.PrintableNode getLeft() { return null; }
    public TreePrinter.PrintableNode getRight() { return null; }
    public String getText() { return ""; }
}
