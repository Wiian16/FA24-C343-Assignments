import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchTest {
    //Test For BFS and DFS Searches
    @Test
    void testBFS_FirstGrid() {
        // Grid setup
        int[][] grid = {
                {1, 1, 1, 0},
                {1, 0, 1, 1},
                {1, 1, 1, 0},
                {1, 0, 0, 0}
        };

        // Expected shortest path using BFS from (0, 0) to (1, 3)
        ArrayList<Pair> expectedPath = new ArrayList<>(Arrays.asList(
                new Pair(0, 0), new Pair(0, 1), new Pair(0, 2), new Pair(1, 2), new Pair(1, 3)
        ));

        // Execute BFS and get the result
        ArrayList<Pair> result = BFSSearch.BFS(grid, new Pair(0, 0), new Pair(1, 3));

        // Assert that the result matches the expected shortest path
        assertEquals(expectedPath, result, "BFS path does not match from (0, 0) to (1, 3).");
    }

    @Test
    void testBFS_SecondGrid() {
        // Second Grid
        int[][] grid = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 1}
        };

        // Expected shortest path from (0, 0) to (2, 3) using BFS
        ArrayList<Pair> expectedPath = new ArrayList<>(Arrays.asList(
                new Pair(0, 0), new Pair(1, 0), new Pair(2, 0), new Pair(2, 1),
                new Pair(2, 2), new Pair(2, 3)
        ));

        // Execute BFS and get the result
        ArrayList<Pair> result = BFSSearch.BFS(grid, new Pair(0, 0), new Pair(2, 3));

        // Assert that the result matches the expected shortest path
        assertEquals(expectedPath, result, "BFS path does not match for the second grid.");
    }



    @Test
    void testDFS_FirstGrid() {
        // First Grid
        int[][] grid = {
                {1, 1, 1, 0},
                {1, 0, 1, 1},
                {1, 1, 1, 0},
                {1, 0, 0, 0}
        };
        ArrayList<Pair> expectedPath = new ArrayList<>(Arrays.asList(
                new Pair(3, 0), new Pair(2, 0), new Pair(2, 1), new Pair(2, 2),
                new Pair(1, 2), new Pair(0, 2), new Pair(0, 1), new Pair(0, 0)
        ));

        // Execute DFS and get the result
        ArrayList<Pair> visited = new ArrayList<>();
        ArrayList<Pair> result = DFSSearch.DFS(grid, visited, new Pair(3, 0), new Pair(0, 0));

        // Assert that the paths match
        assertEquals(expectedPath, result, "DFS path does not match for the first grid.");
    }


    @Test
    void testDFS_SecondGrid() {
        // Second Grid
        int[][] grid = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 1, 1, 1}
        };

        // Initialize an empty list to keep track of visited cells
        ArrayList<Pair> visited = new ArrayList<>();

        // Corrected expected path from (4, 4) to (0, 0) using DFS traversal
        ArrayList<Pair> expectedPath = new ArrayList<>(Arrays.asList(
                new Pair(4, 4), new Pair(4, 3), new Pair(4, 2), new Pair(4, 1),
                new Pair(3, 1), new Pair(2, 1), new Pair(2, 0), new Pair(1, 0), new Pair(0, 0)
        ));

        // Execute DFS and get the result
        ArrayList<Pair> result = DFSSearch.DFS(grid, visited, new Pair(4, 4), new Pair(0, 0));

        // Assert that the result matches the expected path
        assertEquals(expectedPath, result, "DFS path does not match for the second grid.");
    }
}
