import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testAllSubsequences () {
        Set<String> result = Main.allSubsequences("abc");
        assertEquals(8, result.size());
        assertTrue(result.contains(""));
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
        assertTrue(result.contains("ab"));
        assertTrue(result.contains("bc"));
        assertTrue(result.contains("ac"));
        assertTrue(result.contains("abc"));
    }

    @Test
    void testMinDistance () {
        assertEquals(3, Main.minEditDistance("abc", "def"));
        assertEquals(1, Main.minEditDistance("abc", "dbc"));
        assertEquals(1, Main.minEditDistance("abc", "ab"));
        assertEquals(1, Main.minEditDistance("abc", "abcd"));
        assertEquals(0, Main.minEditDistance("abc", "abc"));
    }

    @Test
    void testIsSubsequence() {
        assertTrue(Main.isSubsequence("abc", "abc"));
        assertFalse(Main.isSubsequence("abc", "a"));
        assertTrue(Main.isSubsequence("abc", "xaxbxxcxxdxxx"));
    }

    @Test
    void testLongestCommonSubsequence () {
        assertEquals("", Main.longestCommonSubsequence("abc", "def"));
        assertEquals("bc", Main.longestCommonSubsequence("abc", "dbc"));
        assertEquals("ab", Main.longestCommonSubsequence("abc", "ab"));
        assertEquals("abc", Main.longestCommonSubsequence("abc", "abcd"));
        assertEquals("abc", Main.longestCommonSubsequence("abc", "abc"));
        assertEquals("abc", Main.longestCommonSubsequence("abc", "axbxxcxxdxxx"));
        assertEquals("abc", Main.longestCommonSubsequence("yyyayyybyyyyyyc", "axbxxcxxdxxx"));
    }

    @Test
    void testMergeStrings () {
        assertEquals("abc", Main.mergeStrings("a", "bc"));
        assertEquals("abc", Main.mergeStrings("ab", "c"));
        assertEquals("abc", Main.mergeStrings("ac", "b"));
        assertEquals("abc", Main.mergeStrings("b", "ac"));
        assertEquals("abc", Main.mergeStrings("bc", "a"));
        assertEquals("abc", Main.mergeStrings("c", "ab"));
    }

    @Test
    void testMergeSort () {
        assertEquals("abc", Main.mergeSort("abc"));
        assertEquals("abc", Main.mergeSort("bac"));
        assertEquals("abc", Main.mergeSort("bca"));
        assertEquals("abc", Main.mergeSort("cab"));
        assertEquals("abc", Main.mergeSort("cba"));
    }

    @Test
    void testPermutations () {
        Set<String> result = Main.allPermutations("abc");
        assertEquals(6, result.size());
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("acb"));
        assertTrue(result.contains("bac"));
        assertTrue(result.contains("bca"));
        assertTrue(result.contains("cab"));
        assertTrue(result.contains("cba"));
    }

    @Test
    void testLineWrap () {
        List<String> words = List.of("quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog");
        String result = "The" + Main.lineWrap(words, 15, 15);
        assertEquals("The quick brown\nfox jumps over\nthe lazy dog", result);
    }
}