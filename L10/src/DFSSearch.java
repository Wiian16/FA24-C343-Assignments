import java.util.ArrayList;

public class DFSSearch {

    // Method to get all possible neighbors of a given position
    static Pair[] getNeighbors(Pair input) {
        return new Pair[]{
                new Pair(input.x(), input.y() - 1), // Up
                new Pair(input.x(), input.y() + 1), // Down
                new Pair(input.x() - 1, input.y()), // Left
                new Pair(input.x() + 1, input.y())  // Right
        };
    }

    // DFS method to search the grid
    public static ArrayList<Pair> DFS(int[][] grid, ArrayList<Pair> visited, Pair start, Pair end) {
        ArrayList<Pair> path = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        // Base case: out of bounds or obstacle
        if (start.x() < 0 || start.x() >= rows || start.y() < 0 || start.y() >= cols || grid[start.x()][start.y()] == 0) {
            return path;
        }

        // Base case: already visited
        if (visited.contains(start)) {
            return path;
        }
        visited.add(start);
        path.add(start);

        // Check if we have reached the end position
        if (start.equals(end)) {
            return path; // Return the path if the end position is reached
        }

        //TODO: Explore all neighboring positions using the getNeighbors method
        // - Iterate through each neighbor from getNeighbors(start)
        // - For each neighbor:
        //   1. Recursively call DFS to continue the search from the neighbor
        //   2. If a non-empty path is returned from the recursive call, add all of the neighbor's path to the current path
        //   3. Return the combined path if a valid path is found
        //    If no valid path is found, backtrack
        // - Remove the current position from the visited list to allow other paths to explore it
        // - Remove the current position from the path to undo the current step

        // Return an empty path if no valid path is found

      return null; //Replace this line
    }
}

// Helper class to represent grid positions
record Pair(Integer x, Integer y) {}
