import java.util.*;

public class BFSSearch {



        // BFS method to search for a path in the grid
        public static ArrayList<Pair> BFS(int[][] grid, Pair start, Pair end) {
            ArrayList<Pair> path = new ArrayList<>();
            Queue<Pair> queue = new LinkedList<>();
            int rows = grid.length;
            int cols = grid[0].length;

            // Visited matrix to track visited nodes
            boolean[][] visited = new boolean[rows][cols];
            visited[start.x()][start.y()] = true; // Mark the start node as visited

            // Add the starting point to the queue
            queue.add(start);

            // To track the path, use a parent map
            Map<Pair, Pair> parentMap = new HashMap<>();
            parentMap.put(start, null); // Set the start node's parent as null

            // Directions for moving: right, down, left, up
            int[] rowDirections = {0, 1, 0, -1};
            int[] colDirections = {1, 0, -1, 0};

            // TODO: Implement the BFS loop
            //  Use a `while` loop to run until the queue is empty
            //  Dequeue the front element and store it in `current`
            //  If `current` is the target, build the path from end to start using the parent map and return it

            // TODO: Check and explore all neighbors
            //  Loop through the four possible directions
            //  Calculate new row and column positions for each neighbor
            //  Make sure the new position is:
            //  - Inside the grid boundaries
            //  - Not visited yet
            //  - A traversable cell (value is 1)
            //  If valid, mark the neighbor as visited, add it to the queue, and update the parent map

            // TODO: Return an empty path if no route is found
            //  If the queue is empty and we haven't reached the target, return an empty list like the one below.
            return new ArrayList<>();
        }
}


