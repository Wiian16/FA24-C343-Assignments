import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Read Ch. 3 on Recursion.
 * <p>
 * Write recursive solutions for the following methods.
 * <p>
 * For each method, there is one test case provided in <code>MainTest.java</code>.
 * Consult the test case to understand the expected behavior. Either before
 * or after writing the recursive method, add more test cases of your own.
 */

public class Main {

    /**
     * Compute the set of all subsequences of a given string. A subsequence
     * of a string is any string that can be obtained by deleting zero or more
     * characters from the original string while maintaining the order of the
     * remaining characters.
     */
    public static Set<String> allSubsequences(String str) {
        Set<String> result = new HashSet<>();

        if(str.isEmpty()) {
            result.add(str);
            return result;
        }

        Set<String> recResult = allSubsequences(str.substring(1));
        for(String temp : recResult) {
            result.add(str.charAt(0) + temp);
            result.add(temp);
        }

        return result;
    }

    /**
     * Compute the minimum edit distance between two strings. The minimum edit
     * distance is the minimum number of insertions, deletions, and replacements
     * required to transform one string into another. Each insertion, deletion,
     * or replacement has a cost of 1.
     */
    public static int minEditDistance(String s1, String s2) {
        if(s1.equals(s2)) {
            return 0;
        }

        if(s1.isEmpty()) {
            return s2.length();
        }

        if(s2.isEmpty()) {
            return s1.length();
        }

        if(s1.charAt(0) == s2.charAt(0)) {
            return minEditDistance(s1.substring(1), s2.substring(1));
        }

        int replace = minEditDistance(s1.substring(1), s2.substring(1));
        int insert = minEditDistance(s1, s2.substring(1));
        int delete = minEditDistance(s1.substring(1), s2);

        return 1 + Math.min(replace, Math.min(insert, delete));
    }

    /**
     * Check if a given string is a subsequence of another.
     */
    public static boolean isSubsequence(String s1, String s2) {
        if(s1.isEmpty()) {
            return true;
        }

        int index = s2.indexOf(s1.charAt(0));

        if(index == -1) {
            return false;
        }

        return isSubsequence(s1.substring(1), s2.substring(index + 1));
    }

    /**
     * Compute the longest common subsequence of two strings.
     */
    public static String longestCommonSubsequence(String s1, String s2) {
        if(s1.isEmpty() || s2.isEmpty()) {
            return "";
        }

        if(s1.charAt(0) == s2.charAt(0)) {
            return s1.charAt(0) + longestCommonSubsequence(s1.substring(1), s2.substring(1));
        }

        String case1 = longestCommonSubsequence(s1.substring(1), s2);
        String case2 = longestCommonSubsequence(s1, s2.substring(1));

        if(case1.length() > case2.length()) {
            return case1;
        }

        return case2;
    }

    /**
     * Merge two strings in lexicographical order.
     */
    public static String mergeStrings(String s1, String s2) {
        if(s1.isEmpty()) {
            return s2;
        }

        if(s2.isEmpty()) {
            return s1;
        }

        return s1.charAt(0) < s2.charAt(0) ? s1.charAt(0) + mergeStrings(s1.substring(1), s2) : s2.charAt(0) + mergeStrings(s1, s2.substring(1));
    }

    /**
     * Sort the characters in a string using merge sort.
     */
    public static String mergeSort (String s) {
        if(s.length() == 1) {
            return s;
        }

        int split = s.length() / 2;

        String left = mergeSort(s.substring(0, split));
        String right = mergeSort(s.substring(split));

        return mergeStrings(left, right);
    }

    /**
     * Compute the set of all permutations of a string.
     */
    public static Set<String> allPermutations(String str) {
        Set<String> result = new HashSet<>();

        if(str.isEmpty()) {
            result.add(str);
            return result;
        }

        for(int i = 0; i < str.length(); i++) {
            String next = swap(str, 0, i);

            for(String temp : allPermutations(next.substring(1))) {
                result.add(next.charAt(0) + temp);
            }
        }

        return result;
    }

    private static String swap(String str, int a, int b) {
        char[] charArray = str.toCharArray();
        char temp = charArray[a];
        charArray[a] = charArray[b];
        charArray[b] = temp;
        return String.valueOf(charArray);
    }

    /**
     * Line wrapping. We are given a list of words, a maximum line width, and the
     * number of spaces left on the current line. We want to wrap the words onto
     * lines such that no line exceeds the maximum width. Words should be separated
     * by a single space. If a word and its preceding space both fit on the current
     * line, they are just added to the resulting string. Otherwise, the current
     * word should be placed on the next line preceded by '\n'.
     */
    public static String lineWrap(List<String> words, int width, int space) {
        ArrayList<String> wordsList = new ArrayList<>(words);

        if(wordsList.isEmpty()) {
            return "";
        }

        if(wordsList.getFirst().length() + 1 <= space) {
            String temp = wordsList.removeFirst();
            return " " + temp + lineWrap(wordsList, width, space - temp.length() - 1);
        }

        String temp = wordsList.removeFirst();
        return "\n" + temp + lineWrap(wordsList, width, width - temp.length());
    }
}
