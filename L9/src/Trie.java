public class Trie {

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode current = root;
<<<<<<< HEAD
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
=======
        //TODO: Implement the insert method to store words in the Trie.
        // For each character in the word, create a new child node if it doesn't already exist.
        // Traverse down the path dictated by the characters, and once all characters are inserted, mark the final node as the end of the word.
        // This method ensures that words are efficiently stored for quick lookups.
>>>>>>> 0b96c8270f9790b1c9f7b1d1b5303cba5e1454ca
    }

    // Search for a word in the trie
    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }

    // Node class for the trie
    private static class TrieNode {
        private final TrieNode[] children = new TrieNode[26];
        private boolean isEndOfWord;
    }
}
