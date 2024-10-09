import java.util.Arrays;

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
