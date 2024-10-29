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
        return Math.abs(left.getHeight() - right.getHeight()) <= 1 && left.isBalanced() && right.isBalanced();
    }

    //--------------------------
    // search assuming tree respects BST order
    //--------------------------

    /**
     * Find a key in the tree assuming it respects the BST order.
     */
    boolean find(@NotNull E key) {
        if(key.equals(data)){
            return true;
        }

        if(height == 0){
            return false;
        }

        if(key.compareTo(data) < 0){
            return left.find(key);
        }

        return right.find(key);
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
        if(key.compareTo(data) < 0){
            return new Node<>(data, left.insert(key), right);
        }

        return new Node<>(data, left, right.insert(key));
    }

    /**
     * Delete a key in the tree assuming it respects the BST order. If the
     * key to be deleted is equal to the current node's data, we will replace
     * the current node's data with the leftmost leaf of the right subtree.
     */
    @NotNull BinTree<E> delete(@NotNull E key) throws EmptyTreeE {
        if(!find(key)){
            return this;
        }

        if(key.equals(data)){
            Pair<E, BinTree<E>> deleted = right.deleteLeftMostLeaf();

            return new Node<>(deleted.first(), left, deleted.second());
        }

        if(key.compareTo(data) < 0){
            return new Node<>(data, left.delete(key), right);
        }

        return new Node<>(data, left, right.delete(key));
    }

    /**
     * Traverses the tree looking for the leftmost leaf and returns
     * a pair consisting of that leaf's data and the tree with that
     * leaf removed.
     */
    @NotNull Pair<E, BinTree<E>> deleteLeftMostLeaf() {
        if(left.isEmpty()){
            return new Pair<>(data, right);
        }

        try {
            if(left.getLeftT().isEmpty()) {
                return new Pair<>(left.getData(), new Node<>(data, left.getRightT(), right));
            }

            Pair<E, BinTree<E>> pair = left.deleteLeftMostLeaf();
            return new Pair<>(pair.first(), new Node<>(data, pair.second(), right));
        }
        catch(EmptyTreeE e){
            throw new RuntimeException(e);
        }
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
        if(key.compareTo(data) < 0){
            return new Node<>(data, left.insertB(key), right).rotateLeft().rotateRight().easyLeft().easyRight();
        }

        return new Node<>(data, left, right.insertB(key)).rotateLeft().rotateRight().easyLeft().easyRight();
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
        if(!find(key)){
            return this;
        }

        if(key.equals(data)){
            Pair<E, BinTree<E>> deleted = right.deleteRightMostLeafB();

            return new Node<>(deleted.first(), deleted.second(), right.getRightT()).easyRight().easyLeft().rotateRight().rotateLeft();
        }

        if(key.compareTo(data) < 0){
            return new Node<>(data, left.delete(key), right).easyRight().easyLeft().rotateRight().rotateLeft();
        }

        return new Node<>(data, left, right.deleteB(key)).easyRight().easyLeft().rotateRight().rotateLeft();
    }

    /**
     * Similar to the corresponding non-balancing method, but this one
     * will ensure the tree is balanced by performing the
     * necessary AVL rotations.
     */
    @NotNull Pair<E, BinTree<E>> deleteRightMostLeafB() {
        // TODO: implement this method

        if(right.isEmpty()) {
            return new Pair<>(data, left.easyRight().easyLeft().rotateRight().rotateLeft());
        }
        try{
            if(right.getLeftT().isEmpty()){
                return new Pair<>(right.getData(), new Node<>(data, left, right.getRightT()).easyRight().easyLeft().rotateRight().rotateLeft());
            }

            Pair<E, BinTree<E>> pair = left.deleteRightMostLeafB();
            return new Pair<>(pair.first(), new Node<>(data, right, pair.second()).easyRight().easyLeft().rotateRight().rotateLeft());
        }
        catch(EmptyTreeE e){
            throw new RuntimeException(e);
        }

    }


    /**
     * The next four methods are the AVL rotations.
     */

    //Rotate right
    @NotNull BinTree<E> easyRight() {
        try{
            if(left.getHeight() - right.getHeight() == 2 && !left.getLeftT().isEmpty()){
                BinTree<E> A = this;
                BinTree<E> B = left;
                BinTree<E> C = left.getLeftT();

                BinTree<E> left = C;
                BinTree<E> right = new Node<>(A.getData(), B.getRightT(), A.getRightT());

                return new Node<>(B.getData(), left, right);
            }
        }
        catch(EmptyTreeE e){
            throw new RuntimeException(e);
        }

        return this;
    }

    //Rotate left-right
    @NotNull BinTree<E> rotateRight() {
        try{
            if(left.getHeight() - right.getHeight() == 2 && !left.getRightT().isEmpty()){
                BinTree<E> C = this;
                BinTree<E> A = C.getLeftT();
                BinTree<E> B = A.getRightT();

                BinTree<E> right = new Node<>(C.getData(), B.getRightT(), C.getRightT());
                BinTree<E> left = new Node<>(A.getData(), A.getLeftT(), B.getLeftT());

                return new Node<>(B.getData(), left, right);
            }
        }
        catch(EmptyTreeE e){
            throw new RuntimeException(e);
        }

        return this;
    }

    //Rotate left
    @NotNull BinTree<E> easyLeft() {
        try {
            if(right.getHeight() - left.getHeight() == 2 && !right.getRightT().isEmpty()) {
                BinTree<E> A = this;
                BinTree<E> B = right;
                BinTree<E> C = right.getRightT();

                BinTree<E> left = new Node<>(A.getData(), A.getLeftT(), B.getLeftT());
                BinTree<E> right = C;

                return new Node<>(B.getData(), left, right);
            }
        }
        catch(EmptyTreeE e){
            throw new RuntimeException(e);
        }

        return this;
    }

    //Rotate right-left
    @NotNull BinTree<E> rotateLeft() {
        try{
            if(right.getHeight() - left.getHeight() == 2 && !right.getLeftT().isEmpty()){
                BinTree<E> A = this;
                BinTree<E> C = A.getRightT();
                BinTree<E> B = C.getLeftT();

                BinTree<E> right = new Node<>(C.getData(), B.getRightT(), C.getRightT());
                BinTree<E> left = new Node<>(A.getData(), A.getLeftT(), B.getLeftT());

                return new Node<>(B.getData(), left, right);
            }
        }
        catch(EmptyTreeE e){
            throw new RuntimeException(e);
        }
        return this;
    }
}
