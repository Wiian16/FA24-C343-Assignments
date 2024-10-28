import org.jetbrains.annotations.NotNull;

abstract class BinTree<E extends Comparable<E>> implements TreePrinter.PrintableNode {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static <E extends Comparable<E>> @NotNull BinTree<E> makeLeaf(@NotNull E elem) {
        return new Node<>(elem, new Empty<>(), new Empty<>());
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract @NotNull E getData() throws EmptyTreeE;

    abstract @NotNull BinTree<E> getLeftT() throws EmptyTreeE;

    abstract @NotNull BinTree<E> getRightT() throws EmptyTreeE;

    abstract boolean isEmpty();

    abstract int getHeight();

    abstract boolean isBalanced();

    //--------------------------
    // search assuming tree respects BST order
    //--------------------------

    abstract boolean find (@NotNull E key);

    //--------------------------
    // BST insert/delete (and helper) without balancing
    //--------------------------

    abstract @NotNull BinTree<E> insert(@NotNull E key);

    abstract @NotNull BinTree<E> delete(@NotNull E key) throws EmptyTreeE;

    abstract @NotNull Pair<E, BinTree<E>> deleteLeftMostLeaf() throws EmptyTreeE;

    //--------------------------
    // BST insert/delete (and helpers) with balancing
    //--------------------------

    abstract @NotNull BinTree<E> insertB(@NotNull E key);

    abstract @NotNull BinTree<E> deleteB(@NotNull E key) throws EmptyTreeE;

    abstract @NotNull Pair<E, BinTree<E>> deleteRightMostLeafB() throws EmptyTreeE;

    abstract @NotNull BinTree<E> easyRight();

    abstract @NotNull BinTree<E> rotateRight();

    abstract @NotNull BinTree<E> easyLeft();

    abstract @NotNull BinTree<E> rotateLeft();

}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
