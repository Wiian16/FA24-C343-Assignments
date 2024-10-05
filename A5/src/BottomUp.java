import java.util.Arrays;
import java.util.HashMap;

public class BottomUp extends Rec {
    public int minEditDistance(String s1, String s2) {
        // TODO: Implement this method
        int[][] results = new int[s1.length() + 1][s2.length() + 1];
        //adding known solutions
        //if one string is empty, the min distance is the length of the other (all inserts)
        for(int i = 0; i < results.length; i++){
            results[i][0] = i;
        }

        for(int i = 0; i < results[0].length; i++){
            results[0][i] = i;
        }

        //solving for other entries
        for(int i = 1; i < results.length; i++){
            for(int j = 1; j < results[i].length; j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    results[i][j] = results[i - 1][j - 1]; //characters are the same, no change from last solution
                }
                else{
                    //characters differ, add one change to best previous solution
                    int min = Math.min(results[i - 1][j], Math.min(results[i - 1][j - 1], results[i][j - 1]));
                    results[i][j] = 1 + min;
                }
            }
        }

        return results[s1.length()][s2.length()];
    }

    // -----------------------------------------------------------------------------------

    public String longestCommonSubsequence(String s1, String s2) {
        // TODO: Implement this method
        return "";
    }

    // -----------------------------------------------------------------------------------

    public int treasureCollector (Pair<Integer,Integer>[][] grid, int row, int col, int availableWeight) {
        //todo: add weight constraint
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] results = new int[rows][cols];

        for(int i = rows - 1; i >= 0; i--){
            for(int j = 0; j < cols; j++){
                Pair<Integer, Integer> current = grid[i][j];
                int[] options = new int[6]; //left, down, right w/ treasure, left, down, right w/o treasure

                options[0] = current.second() + ((i == rows - 1 || j == 0) ? 0 : results[i + 1][j - 1]);
                options[1] = current.second() + (i == rows - 1 ? 0 : results[i + 1][j]);
                options[2] = current.second() + ((i == rows - 1 || j == cols - 1) ? 0 : results[i + 1][j + 1]);
                options[3] = ((i == rows - 1 || j == 0) ? 0 : results[i + 1][j - 1]);
                options[4] = i == rows - 1 ? 0 : results[i + 1][j];
                options[5] = ((i == rows - 1 || j == cols - 1) ? 0 : results[i + 1][j + 1]);

                results[i][j] = Arrays.stream(options).max().getAsInt();
            }
        }

        return results[row][col];
    }
}
