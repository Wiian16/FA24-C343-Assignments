import org.jetbrains.annotations.NotNull;

class Node<E extends Comparable<E>> extends BinTree<E> {
    private final @NotNull E data;
    private final @NotNull BinTree<E> left, right;
    private final int height;

    Node(@NotNull E data, @NotNull BinTree<E> left, @NotNull BinTree<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(left.getHeight(), right.getHeight());
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return left.isEmpty() ? null : left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right.isEmpty() ? null : right;
    }

    public String getText() {
        return String.valueOf(getData());
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    @NotNull E getData() {
        return data;
    }

    @NotNull BinTree<E> getLeftT() {
        return left;
    }

    @NotNull BinTree<E> getRightT() {
        return right;
    }

    boolean isEmpty() {
        return false;
    }

    int getHeight() {
        return height;
    }

    /**
      A tree is balanced if the difference in height between the left and right subtrees is at most 1,
      and both subtrees are balanced.
     */
    boolean isBalanced() {
        // TODO: implement this method
        return false;
    }

    //--------------------------
    // search assuming tree respects BST order
    //--------------------------

    /**
     * Find a key in the tree assuming it respects the BST order.
     */
    boolean find(@NotNull E key) {
        // TODO: implement this method
        return false;
    }

    //--------------------------
    // BST insert/delete (and helper) without balancing
    //--------------------------

    /**
     * Insert a key in the tree assuming it respects the BST order.
     * We will allow duplicate keys in the tree. To maintain consistency
     * with our test cases, we will insert the new key in the right subtree
     * if it is equal to the current node's data.
     */
    @NotNull BinTree<E> insert(@NotNull E key) {
        // TODO: implement this method
        return null;
    }

    /**
     * Delete a key in the tree assuming it respects the BST order. If the
     * key to be deleted is equal to the current node's data, we will replace
     * the current node's data with the leftmost leaf of the right subtree.
     */
    @NotNull BinTree<E> delete(@NotNull E key) throws EmptyTreeE {
        // TODO: implement this method
        return null;
    }

    /**
     * Traverses the tree looking for the leftmost leaf and returns
     * a pair consisting of that leaf's data and the tree with that
     * leaf removed.
     */
    @NotNull Pair<E, BinTree<E>> deleteLeftMostLeaf() {
        // TODO: implement this method
        return null;
    }

    //--------------------------
    // BST insert/delete (and helpers) with balancing
    //--------------------------

    /**
     * Same as the corresponding non-balancing method, but this one
     * will ensure the tree is balanced by performing the
     * necessary AVL rotations.
     */
    @NotNull BinTree<E> insertB(@NotNull E key) {
        // TODO: implement this method
        return null;
    }

    /**
     * Same as the corresponding non-balancing method except for
     * two differences:
     * <ul>
     *     <li>We delete the rightmost leaf in the left subtree instead of the dual situation
     *     <li>We ensure the tree is balanced by performing the necessary AVL rotations
     * </ul>
     */
    @NotNull BinTree<E> deleteB(@NotNull E key) throws EmptyTreeE {
        // TODO: implement this method
        return null;
    }

    /**
     * Similar to the corresponding non-balancing method, but this one
     * will ensure the tree is balanced by performing the
     * necessary AVL rotations.
     */
    @NotNull Pair<E, BinTree<E>> deleteRightMostLeafB() {
        // TODO: implement this method
        return null;
    }


    /**
     * The next four methods are the AVL rotations.
     */

    @NotNull BinTree<E> easyRight() {
        // TODO: implement this method
        return null;
    }

    @NotNull BinTree<E> rotateRight() {
        // TODO: implement this method
        return null;
    }

    @NotNull BinTree<E> easyLeft() {
        // TODO: implement this method
        return null;
    }

    @NotNull BinTree<E> rotateLeft() {
        // TODO: implement this method
        return null;
    }

}
