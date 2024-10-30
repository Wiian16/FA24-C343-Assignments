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
            if(left.isEmpty() && right.isEmpty()){
                return new Empty<>();
            }

            if(left.isEmpty() || right.isEmpty()){
                return left.isEmpty() ? right : left;
            }

            else{
                Pair<E, BinTree<E>> deleted = right.deleteLeftMostLeaf();

                return new Node<>(deleted.first(), left, deleted.second());
            }
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
        if(key.compareTo(data) < 0){
            return new Node<>(data, left.insertB(key), right).easyRight().easyLeft().rotateRight().rotateLeft();
        }

        return new Node<>(data, left, right.insertB(key)).easyRight().easyLeft().rotateRight().rotateLeft();
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
        if(!find(key)){
            return this;
        }

        BinTree<E> result;

        if(key.equals(data)){
            if(left.isEmpty() && right.isEmpty()){
                //Node is a leaf node, just delete
                return new Empty<>();
            }

            if(left.isEmpty() || right.isEmpty()){
                //Node only has one child, replace with child
                return left.isEmpty() ? right : left;
            }

            //Node has two children, replace with the right most node in left tree
            Pair<E, BinTree<E>> deleted = left.deleteRightMostLeafB();
            result = new Node<>(deleted.first(), deleted.second(), right);
        }
        else if(key.compareTo(data) < 0){
            result =  new Node<>(data, left.deleteB(key), right);
        }
        else{
            result = new Node<>(data, left, right.deleteB(key));
        }

        if(result.isBalanced()){
            return result;
        }

        return result.easyRight().easyLeft().rotateRight().rotateLeft();
    }

    /**
     * Similar to the corresponding non-balancing method, but this one
     * will ensure the tree is balanced by performing the
     * necessary AVL rotations.
     */
    @NotNull Pair<E, BinTree<E>> deleteRightMostLeafB() {
        if(right.isEmpty()) {
            return new Pair<>(data, left);
        }

        try {
            Pair<E, BinTree<E>> deleted = right.deleteRightMostLeafB();
            return new Pair<>(deleted.first(), new Node<>(data, left, deleted.second()).easyRight().easyLeft().rotateRight().rotateLeft());
        }
        catch(EmptyTreeE e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * The next four methods are the AVL rotations.
     */
    // Rotate right
    @NotNull BinTree<E> easyRight() {
        try {
            int balanceFactor = left.getHeight() - right.getHeight();
            int leftBalanceFactor = left.isEmpty() ? -1 : left.getLeftT().getHeight() - left.getRightT().getHeight();

            if(balanceFactor == 2 && (leftBalanceFactor == 0 || leftBalanceFactor == 1)){
                BinTree<E> newRight = new Node<>(data, left.getRightT(), right);

                return new Node<>(left.getData(), left.getLeftT(), newRight);
            }
        }
        catch(EmptyTreeE e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    // Rotate left
    @NotNull BinTree<E> easyLeft() {
        try {
            int balanceFactor = left.getHeight() - right.getHeight();
            int rightBalanceFactor = right.isEmpty() ? 1 : right.getLeftT().getHeight() - right.getRightT().getHeight();

            if(balanceFactor == -2 && (rightBalanceFactor == 0 || rightBalanceFactor == -1)){
                BinTree<E> newLeft = new Node<>(data, left, right.getLeftT());

                return new Node<>(right.getData(), newLeft, right.getRightT());
            }
        }
        catch(EmptyTreeE e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    // Rotate left-right
    @NotNull BinTree<E> rotateRight() {
        try {
            int balanceFactor = left.getHeight() - right.getHeight();
            int leftBalanceFactor = left.isEmpty() ? 0 : left.getLeftT().getHeight() - left.getRightT().getHeight();

            if(balanceFactor == 2 && leftBalanceFactor == -1){
                Node<E> leftChild = new Node<>(left.getData(), left.getLeftT(), left.getRightT().getLeftT());
                Node<E> newLeft = new Node<>(left.getRightT().getData(), leftChild, left.getRightT().getRightT());
                Node<E> temp = new Node<>(data, newLeft, right);

                return temp.easyRight();
            }
        }
        catch(EmptyTreeE e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    // Rotate right-left
    @NotNull BinTree<E> rotateLeft() {
        try {
            int balanceFactor = left.getHeight() - right.getHeight();
            int rightBalanceFactor = right.isEmpty() ? 0 : right.getLeftT().getHeight() - right.getRightT().getHeight();

            if(balanceFactor == -2 && rightBalanceFactor == 1){
                Node<E> rightChild = new Node<>(right.getData(), right.getLeftT().getRightT(), right.getRightT());
                Node<E> newRight = new Node<>(right.getLeftT().getData(), right.getLeftT().getLeftT(), rightChild);
                Node<E> temp = new Node<>(data, left, newRight);

                return temp.easyLeft();
            }
        }
        catch(EmptyTreeE e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public static void main(String[] args) {
        BinTree<Integer> tr = makeLeaf(40).insertB(20).insertB(50).insertB(10).insertB(30);
        TreePrinter.print(tr);

        tr = tr.insertB(25);

        TreePrinter.print(tr);
    }

}
