public class TreeConverter {

    public static Trie convertTernaryTreeToTrie(TernaryTree ternaryTree) {
        Trie trie = new Trie();
        StringBuilder prefix = new StringBuilder();
        convertHelper(ternaryTree.getRoot(), trie, prefix);
        return trie;
    }

    private static void convertHelper(TernaryTree.TernaryTreeNode node, Trie trie, StringBuilder prefix) {
        //TODO:Fill out the rest of the convertHelper method to complete the conversion from a ternary tree to a trie.
        // If the current node marks the end of a word, insert the current prefix into the Trie.
        // Recursively call convertHelper on the left child without modifying the prefix.
        // For the middle child, append the current node’s value to the prefix before the recursive call.
        // After visiting the middle child, backtrack by removing the last character from the prefix to maintain the correct structure.
        // Finally, call convertHelper on the right child without modifying the prefix. Mind the base case too
        if(node == null){
            return;
        }

        convertHelper(node.left, trie, prefix);

        prefix.append(node.value);

        if(node.isEndOfWord){
            System.out.println("prefix = " + prefix);
            trie.insert(prefix.toString());
        }

        convertHelper(node.middle, trie, prefix);
        prefix.deleteCharAt(prefix.length() - 1);
        convertHelper(node.right, trie, prefix);
    }
}
