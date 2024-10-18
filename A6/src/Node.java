import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

  /*
   * The class represents an abstract node in a binary tree. It has two subclasses
   * one for an empty node and one for a node with two children.
   * <p>
   * The abstract class maintains information about the parent of each node
   * and provides methods to access the parent and the depth of the node.
   * Every node must have one parent except the root node. The parent of the root node is
   * represented by Optional.empty().
   *
   */
public abstract class Node<E> implements TreePrinter.PrintableNode {
    public static <E> @NotNull Node<E> makeLeaf (@NotNull E e) {
        return new NonEmptyNode<>(e, new EmptyNode<>(), new EmptyNode<>());
    }

    protected @NotNull Optional<Node<E>> parent;

    protected Node() {
        parent = Optional.empty();
    }

    public void setParent(@NotNull Node<E> parent) {
        this.parent = Optional.of(parent);
    }

    public @NotNull Node<E> parent() throws NodeNotFoundE {
        return parent.orElseThrow(NodeNotFoundE::new);
    }

      /**
       * Returns the depth of the node in the tree. The depth of the root node is 0.
       */
    public int depth() {
        // TODO: Implement this method
        return 0; // This is a placeholder
    }

    public abstract boolean isEmpty ();
    public abstract int size();
    public abstract int height();
    public abstract @NotNull E element() throws NodeNotFoundE;
    public abstract @NotNull Node<E> leftNode() throws NodeNotFoundE;
    public abstract @NotNull Node<E> rightNode() throws NodeNotFoundE;
    public abstract @NotNull List<E> preOrder();
    public abstract @NotNull List<E> inOrder();
    public abstract @NotNull List<E> postOrder();
}
