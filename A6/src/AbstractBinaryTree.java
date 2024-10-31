import org.jetbrains.annotations.NotNull;

public  abstract class AbstractBinaryTree<E> implements BinaryTree<E> {

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isRoot(@NotNull Node<E> node) {
        return node.depth() == 0;
    }

    public boolean isLeaf(@NotNull Node<E> node) {
        try {
            return node.leftNode().isEmpty() && node.rightNode().isEmpty();
        }
        catch (NodeNotFoundE e) {
            return false;
        }
    }

    public int height(@NotNull Node<E> node) {
        return node.height();
    }

    public int depth(@NotNull Node<E> node) {
        return node.depth();
    }

}
