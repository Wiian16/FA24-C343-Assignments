import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test1 {

    private TernaryTree ternaryTree;
    private Trie trie;

    @BeforeEach
    public void setUp() {
        ternaryTree = new TernaryTree();
        trie = new Trie();
    }

    @Test
    public void shouldInsertAndSearchInTernaryTree() {
        ternaryTree.insert("cat");
        ternaryTree.insert("car");
        ternaryTree.insert("dog");

        assertTrue(ternaryTree.search("cat"), "Search for 'cat' should return true");
        assertTrue(ternaryTree.search("car"), "Search for 'car' should return true");
        assertTrue(ternaryTree.search("dog"), "Search for 'dog' should return true");
        assertFalse(ternaryTree.search("bat"), "Search for 'bat' should return false");
    }

    @Test
    public void shouldInsertAndSearchInTrie() {
        trie.insert("apple");
        trie.insert("bat");
        trie.insert("ball");

        assertTrue(trie.search("apple"), "Search for 'apple' should return true");
        assertTrue(trie.search("bat"), "Search for 'bat' should return true");
        assertTrue(trie.search("ball"), "Search for 'ball' should return true");
        assertFalse(trie.search("app"), "Search for 'app' should return false");
    }

    @Test
    public void shouldConvertTernaryTreeToTrieAndSearch() {
        ternaryTree.insert("cat");
        ternaryTree.insert("car");
        ternaryTree.insert("dog");

        Trie convertedTrie = TreeConverter.convertTernaryTreeToTrie(ternaryTree);

        assertTrue(convertedTrie.search("cat"), "Converted Trie: 'cat' should be found");
        assertTrue(convertedTrie.search("car"), "Converted Trie: 'car' should be found");
        assertTrue(convertedTrie.search("dog"), "Converted Trie: 'dog' should be found");
        assertFalse(convertedTrie.search("bat"), "Converted Trie: 'bat' should not be found");
    }
}
