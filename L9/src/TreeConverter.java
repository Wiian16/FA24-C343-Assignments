public class TreeConverter {

    public static Trie convertTernaryTreeToTrie(TernaryTree ternaryTree) {
        Trie trie = new Trie();
        StringBuilder prefix = new StringBuilder();
        convertHelper(ternaryTree.getRoot(), trie, prefix);
        return trie;
    }

    private static void convertHelper(TernaryTree.TernaryTreeNode node, Trie trie, StringBuilder prefix) {
        if (node == null) return; // Base case

<<<<<<< HEAD

        convertHelper(node.left, trie, prefix);


        prefix.append(node.value);


        if (node.isEndOfWord) {
            trie.insert(prefix.toString()); // Insert the current word
        }


        convertHelper(node.middle, trie, prefix);


        prefix.deleteCharAt(prefix.length() - 1);


        convertHelper(node.right, trie, prefix);
=======
        //TODO:Fill out the rest of the convertHelper method to complete the conversion from a ternary tree to a trie.
        // Recursively call convertHelper on the left child without modifying the prefix. (for lexicographically smaller values)
        // Traverse the main path (middle child), adding current character to the prefix (Track length before adding character)
        // If the current node marks the end of a word, insert the current prefix into the Trie.
        // For the middle child, append the current node’s value to the prefix before the recursive call.
        // After visiting the middle child, backtrack by removing the last character from the prefix to maintain the correct structure.
        // Finally, call convertHelper on the right child without modifying the prefix. (for lexicographically greater values)

>>>>>>> 0b96c8270f9790b1c9f7b1d1b5303cba5e1454ca
    }
}
