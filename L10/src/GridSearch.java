import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class GridSearch {
    private final char[][] grid;
    private final HashSet<String> foundWords = new HashSet<>();
    private final int rows;
    private final int cols;
    private boolean[][] visited;

    // Constructor to initialize the grid
    public GridSearch(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    // BFS method inspired by the original BFS code
    public void findWordsFromPos(int startRow, int startCol) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startRow, startCol, String.valueOf(grid[startRow][startCol])));



        visited = new boolean[rows][cols]; // Initialize the visited array
        visited[startRow][startCol] = true;

        //TODO: Complete the function with the help of the starter code and the instructions below:
        //The findWordsFromPos function performs a Breadth-First Search (BFS) starting from a given position on a grid.
        // It explores all possible paths to form words by appending characters from adjacent cells.
        // If a word meets the length requirement (Here, the length has to be more than or equal to 2) , it adds the word to the set of found words.


        // 1. Use a `while` loop to run as long as the queue is not empty.
        // 2. Dequeue the front element using `poll()` and extract `row`, `col`, and `word`.
        // 3. If `word` has 2 or more characters, add it to `foundWords`.
        // 4. Check four directions (down, right, up, left):
        //    - Ensure the new position is within bounds and not visited.
        //    - If valid, form a new word, add the new position to the queue, and mark it visited.


    }

    // Method to initiate the search from every position in the grid
    public void findWords() {
        foundWords.clear();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                findWordsFromPos(r, c);
            }
        }
    }

    // Getter for the found words
    public HashSet<String> getFoundWords() {
        return foundWords;
    }

    // Helper class to represent a position in the grid and the word formed so far
    private static class Position {
        int row, col;
        String word;

        Position(int row, int col, String word) {
            this.row = row;
            this.col = col;
            this.word = word;
        }
    }
}
