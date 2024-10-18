import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface BinaryTree<E> {
    int size();
    boolean isEmpty();

    boolean isRoot(@NotNull Node<E> node);
    boolean isLeaf(@NotNull Node<E> node);
    int height(@NotNull Node<E> node);
    int depth(@NotNull Node<E> node);

    @NotNull Node<E> root();
    @NotNull Node<E> parent(@NotNull Node<E> node) throws NodeNotFoundE;
    @NotNull Node<E> leftNode(@NotNull Node<E> node) throws NodeNotFoundE;
    @NotNull Node<E> rightNode(@NotNull Node<E> node) throws NodeNotFoundE;

    @NotNull List<E> preOrder();
    @NotNull List<E> inOrder();
    @NotNull List<E> postOrder();
    @NotNull List<E> levelOrder();
}
