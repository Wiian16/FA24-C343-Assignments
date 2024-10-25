import org.jetbrains.annotations.NotNull;

class Empty<E extends Comparable<E>> extends BinTree<E> {

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public String getText() {
        return "";
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    @NotNull E getData() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    @NotNull BinTree<E> getLeftT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    @NotNull BinTree<E> getRightT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    boolean isEmpty() {
        return true;
    }

    int getHeight() {
        return 0;
    }

    boolean isBalanced() {
        return true;
    }

    //--------------------------
    // search assuming tree respects BST order
    //--------------------------

    boolean find(@NotNull E key) {
        return false;
    }

    //--------------------------
    // BST insert/delete (and helper) without balancing
    //--------------------------

    @NotNull BinTree<E> insert(@NotNull E key) {
        return makeLeaf(key);
    }

    @NotNull BinTree<E> delete(@NotNull E key) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    @NotNull Pair<E, BinTree<E>> deleteLeftMostLeaf() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    //--------------------------
    // BST insert/delete (and helpers) with balancing
    //--------------------------

    @NotNull BinTree<E> insertB(@NotNull E key) {
        return makeLeaf(key);
    }

    @NotNull BinTree<E> deleteB(@NotNull E key) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    @NotNull Pair<E, BinTree<E>> deleteRightMostLeafB() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    @NotNull BinTree<E> easyRight() {
        throw new Error("Internal bug: should never call easyRight on empty tree");
    }

    @NotNull BinTree<E> rotateRight() {
        throw new Error("Internal bug: should never call rotateRight on empty tree");
    }

    @NotNull BinTree<E> easyLeft() {
        throw new Error("Internal bug: should never call easyLeft on empty tree");
    }

    @NotNull BinTree<E> rotateLeft() {
        throw new Error("Internal bug: should never call rotateLeft on empty tree");
    }

}
