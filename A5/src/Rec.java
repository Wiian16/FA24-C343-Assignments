
public class Rec {

    /**
     * This is the solution to the minimum edit distance problem
     * from an early assignment.
     * <p>
     * The solution re-evaluates sub-problems over and over and has
     * poor performance.
     */
    public int minEditDistance(String s1, String s2) {
        if (s1.isEmpty()) return s2.length();
        else if (s2.isEmpty()) return s1.length();
        else if (s1.charAt(0) == s2.charAt(0))
            return minEditDistance(s1.substring(1), s2.substring(1));
        else {
            int insert = 1 + minEditDistance(s1, s2.substring(1));
            int delete = 1 + minEditDistance(s1.substring(1), s2);
            int replace = 1 + minEditDistance(s1.substring(1), s2.substring(1));
            return Math.min(insert, Math.min(delete, replace));
        }
    }

    // -----------------------------------------------------------------------------------

    /**
     * This is the solution to the longest common subsequence problem
     * from an early assignment.
     * <p>
     * The solution re-evaluates sub-problems over and over and has
     * poor performance.
     */
    public String longestCommonSubsequence(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty()) return "";
        else if (s1.charAt(0) == s2.charAt(0))
            return s1.charAt(0) + longestCommonSubsequence(s1.substring(1), s2.substring(1));
        else {
            String lcs1 = longestCommonSubsequence(s1.substring(1), s2);
            String lcs2 = longestCommonSubsequence(s1, s2.substring(1));
            return lcs1.length() > lcs2.length() ? lcs1 : lcs2;
        }
    }

    // -----------------------------------------------------------------------------------

    /**
     * This is a new problem for which you need to write a recursive solution.
     * <p>
     * You are given a 2D grid of pairs of integers. The first integer in the pair
     * is the weight of the treasure and the second integer is the value of the
     * treasure. The goal is to collect as much value as possible without exceeding
     * a given weight limit. You start at given position (row,col) and can only move
     * in three directions: down diagonal left, down, and down diagonal right. The
     * function should return the maximum value that can be collected.
     *
     */
    public int treasureCollector (Pair<Integer,Integer>[][] grid, int row, int col, int availableWeight) {
        // TODO: Implement this method
        return 0;
    }
}

