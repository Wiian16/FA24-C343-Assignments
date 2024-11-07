public class TernaryTree {

    private TernaryTreeNode root;

    public TernaryTree() {
        this.root = null;
    }


    public void insert(String word) {
        root = insertRecursive(root, word, 0);
    }


    private TernaryTreeNode insertRecursive(TernaryTreeNode node, String word, int index) {
        if (index >= word.length()) return node;

        char c = word.charAt(index);

        if (node == null) {
            node = new TernaryTreeNode(c);
        }

        if (c < node.value) {
            node.left = insertRecursive(node.left, word, index);
        } else if (c > node.value) {
            node.right = insertRecursive(node.right, word, index);
        } else {
            if (index == word.length() - 1) {
                node.isEndOfWord = true;
            } else {
                node.middle = insertRecursive(node.middle, word, index + 1);
            }
        }
        return node;
    }


    public boolean search(String word) {
        return searchRecursive(root, word, 0);
    }


    private boolean searchRecursive(TernaryTreeNode node, String word, int index) {
        if (node == null) return false;

<<<<<<< HEAD
        char c = word.charAt(index);

        if (c < node.value) {
            return searchRecursive(node.left, word, index);
        } else if (c > node.value) {
            return searchRecursive(node.right, word, index);
        } else {
            if (index == word.length() - 1) {
                return node.isEndOfWord;
            }
            return searchRecursive(node.middle, word, index + 1);
        }
=======
         // TODO: Implement the search method to check if a word exists in the Ternary Tree.
         //  For each character in the word, traverse the tree by comparing it with the current node's value.
         //  If the character is smaller, move to the left child; if greater, move to the right child.
         //  If the character matches, proceed to the middle child. C
         //  Continue this process until the entire word is traversed.
         //  If the final node marks the end of a word, return true; otherwise, return false. Handle edge cases where nodes or paths do not exist.

        return true; //Replace this line
>>>>>>> 0b96c8270f9790b1c9f7b1d1b5303cba5e1454ca
    }


    public TernaryTreeNode getRoot() {
        return root;
    }


    public static class TernaryTreeNode {
        char value;
        boolean isEndOfWord;
        TernaryTreeNode left, middle, right;

        public TernaryTreeNode(char value) {
            this.value = value;
            this.isEndOfWord = false;
        }
    }
}
