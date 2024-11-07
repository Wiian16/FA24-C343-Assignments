import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

class StudentTest {
//Test for GridSearch
    @Test
    void testFindWords() {
        // Given: A 3x3 grid of characters
        char[][] grid = {
                {'d', 'a', 't'},
                {'a', 't', 'a'},
                {'d', 'o', 'g'}
        };

        // Initialize GridSearch with the given grid
        GridSearch gridSearch = new GridSearch(grid);

        // When: Finding words from all positions using BFS
        gridSearch.findWords();
        HashSet<String> foundWords = gridSearch.getFoundWords();

        // Then: Verify that the foundWords set is not null and not empty
        assertNotNull(foundWords, "The foundWords set should not be null.");
        System.out.println();
        assertFalse(foundWords.isEmpty(), "The foundWords set should not be empty.");

        // Check for some expected words that can be formed without revisiting characters
        assertTrue(foundWords.contains("data"), "The foundWords set should contain 'data'.");
        assertTrue(foundWords.contains("tat"), "The foundWords set should contain 'tat'.");
        assertTrue(foundWords.contains("dog"), "The foundWords set should contain 'dog'.");

        // Check for some shorter words that can be formed
        assertTrue(foundWords.contains("da"), "The foundWords set should contain 'da'.");
        assertTrue(foundWords.contains("at"), "The foundWords set should contain 'at'.");
        assertTrue(foundWords.contains("to"), "The foundWords set should contain 'to'.");

        // Output the number of words found and the words for manual verification
        System.out.printf("Found %d words.%n", foundWords.size());
        foundWords.forEach(System.out::println);
    }
}
