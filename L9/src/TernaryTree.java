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
