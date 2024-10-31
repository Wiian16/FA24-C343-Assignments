import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

  /*
  The linked representation of a binary tree delegates almost everything to the nodes.
  The tree itself is just a wrapper around the root node.

  The only non-trivial method is levelOrder, which uses a queue to traverse the tree level by level.
  */

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> implements TreePrinter.PrintableNode {
    private final @NotNull Node<E> root;

    public LinkedBinaryTree() {
        root = new EmptyNode<>();
    }

    public LinkedBinaryTree(E e) {
        root = Node.makeLeaf(e);
    }

    public LinkedBinaryTree(@NotNull Node<E> node) {
        root = node;
    }

    public LinkedBinaryTree(E e, @NotNull LinkedBinaryTree<E> left, @NotNull LinkedBinaryTree<E> right) {
        root = new NonEmptyNode<>(e, left.root(), right.root());
    }

    public int size() {
        return root.size();
    }

    public @NotNull Node<E> root() {
        return root;
    }

    public @NotNull Node<E> parent(@NotNull Node<E> node) throws NodeNotFoundE {
        return node.parent();
    }

    public @NotNull Node<E> leftNode(@NotNull Node<E> node) throws NodeNotFoundE {
        return node.leftNode();
    }

    public @NotNull Node<E> rightNode(@NotNull Node<E> node) throws NodeNotFoundE {
        return node.rightNode();
    }

    public @NotNull List<E> preOrder() {
        return root.preOrder();
    }

    public @NotNull List<E> inOrder() {
        return root.inOrder();
    }

    public @NotNull List<E> postOrder() {
        return root.postOrder();
    }

    /**
     * Traverse the tree level by level.
     * The idea is to use a queue to keep track of the nodes at the current level.
     */
    public @NotNull List<E> levelOrder() {
        // TODO: Implement this method
        return null; // Placeholder
    }

    // Printable interface
    public TreePrinter.PrintableNode getLeft() { return root.getLeft(); }
    public TreePrinter.PrintableNode getRight() { return root.getRight(); }
    public String getText() { return root.getText(); }
}
