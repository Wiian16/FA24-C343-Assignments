public class TreeConverter {

    public static Trie convertTernaryTreeToTrie(TernaryTree ternaryTree) {
        Trie trie = new Trie();
        StringBuilder prefix = new StringBuilder();
        convertHelper(ternaryTree.getRoot(), trie, prefix);
        return trie;
    }

    private static void convertHelper(TernaryTree.TernaryTreeNode node, Trie trie, StringBuilder prefix) {
        if (node == null) return; // Base case


        convertHelper(node.left, trie, prefix);


        prefix.append(node.value);


        if (node.isEndOfWord) {
            trie.insert(prefix.toString()); // Insert the current word
        }


        convertHelper(node.middle, trie, prefix);


        prefix.deleteCharAt(prefix.length() - 1);


        convertHelper(node.right, trie, prefix);
    }
}
