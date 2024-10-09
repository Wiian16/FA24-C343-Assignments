import java.util.Arrays;

public class BottomUp extends Rec {
    public int minEditDistance(String s1, String s2) {
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
        String[][] solutions = new String[s1.length() + 1][s2.length() + 1];

        for(String[] solutionsRow : solutions) {
            Arrays.fill(solutionsRow, "");
        }

        //base case: s1 or s2 are empty, LCS is "", leave first row and col as empty strings
        for(int i = 1; i < solutions.length; i++){
            for(int j = 1; j < solutions[i].length; j++){
                String max = "";
                //Check pre-computed neighbors for best previous solution
                if(solutions[i - 1][j - 1].length() > max.length()){
                    max = solutions[i -1][j - 1];
                }
                if(solutions[i - 1][j].length() > max.length()){
                    max = solutions[i - 1][j];
                }
                if(solutions[i][j - 1].length() > max.length()){
                    max = solutions[i][j - 1];
                }
                //if current characters match, add to current LCS
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    max += s1.charAt(i - 1);
                }
                //store current LCS
                solutions[i][j] = max;
            }
        }

        return solutions[s1.length()][s2.length()];
    }

    // -----------------------------------------------------------------------------------

    public int treasureCollector (Pair<Integer,Integer>[][] grid, int row, int col, int availableWeight) {
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int[][][] treasureSolutions = new int[grid.length + 1][grid[0].length][availableWeight + 1];

        for(int r = treasureSolutions.length - 2; r >= 0; r--){
            for(int c = treasureSolutions[r].length - 1; c >= 0; c--){
                for(int w = treasureSolutions[r][c].length - 1; w >= 1; w--){
                    int currentValue = grid[r][c].second();
                    int currentWeight = grid[r][c].first();

                    int[] options = new int[6];

                    if(w >= currentWeight) {
                        //bottom left taking current
                        options[0] = c - 1 >= 0 ? currentValue + treasureSolutions[r + 1][c - 1][w - currentWeight] : -1;
                        //bottom taking current
                        options[1] = currentValue + treasureSolutions[r + 1][c][w - currentWeight];
                        //bottom right taking current
                        options[2] = c + 1 < grid[r].length ? currentValue + treasureSolutions[r + 1][c + 1][w - currentWeight] : -1;
                    }
                    else{
                        options[0] = -1;
                        options[1] = -1;
                        options[2] = -1;
                    }

                    //bottom left leaving current
                    options[3] = c - 1 >= 0 ? treasureSolutions[r + 1][c - 1][w] : -1;
                    //bottom leaving current
                    options[4] = treasureSolutions[r + 1][c][w];
                    options[5] = c + 1 < treasureSolutions[r].length ? treasureSolutions[r + 1][c + 1][w] : -1;

                    treasureSolutions[r][c][w] = Arrays.stream(options).max().getAsInt();
                }
            }
        }
        return treasureSolutions[row][col][availableWeight];
    }
}
