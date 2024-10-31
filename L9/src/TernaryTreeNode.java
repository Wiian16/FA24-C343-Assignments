class TernaryTreeNode {
    char value;
    boolean isEndOfWord;
    TernaryTreeNode left, middle, right;

    public TernaryTreeNode(char value) {
        this.value = value;
        this.isEndOfWord = false;
        this.left = this.middle = this.right = null;
    }
}
