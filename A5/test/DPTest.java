import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class DPTest {

    long timeIt (Runnable r) {
        long start = System.currentTimeMillis();
        r.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    @Test
    void testMED() {
        String s1, s2;
        int r1, r2, r3;

        Rec rec = new Rec();
        TopDown td = new TopDown();
        BottomUp bu = new BottomUp();

        s1 = "horse";
        s2 = "ros";
        r1 = rec.minEditDistance(s1,s2);
        r2 = td.minEditDistance(s1,s2);
        r3 = bu.minEditDistance(s1,s2);
        assertTrue(r1 == 3 && r2 == 3 && r3 == 3);

        td.clearHashes();

        s1 = "a".repeat(12);
        s2 = "b".repeat(12);
        r1 = rec.minEditDistance(s1,s2);
        r2 = td.minEditDistance(s1,s2);
        r3 = bu.minEditDistance(s1,s2);
        assertTrue(r1 == 12 && r2 == 12 && r3 == 12);
    }

    @Test
    void testMEDSpeed() {
        String s1, s2;
        long r1, r2, r3;

        Rec rec = new Rec();
        TopDown td = new TopDown();
        BottomUp bu = new BottomUp();

        s1 = "a".repeat(12);
        s2 = "b".repeat(12);
        r1 = timeIt(() -> rec.minEditDistance(s1,s2));
        r2 = timeIt(() -> td.minEditDistance(s1,s2));
        r3 = timeIt(() -> bu.minEditDistance(s1,s2));

        System.out.printf("MED Rec: %dms, TopDown: %dms, BottomUp: %dms\n", r1, r2, r3);
        assertTrue(r1 > r2);
        assertTrue(r1 > r3);
    }

    @Test
    void testLCS() {
        String s1, s2;
        String r1, r2, r3;

        Rec rec = new Rec();
        TopDown td = new TopDown();
        BottomUp bu = new BottomUp();

        s1 = "abcde";
        s2 = "ace";
        r1 = rec.longestCommonSubsequence(s1,s2);
        r2 = td.longestCommonSubsequence(s1,s2);
        r3 = bu.longestCommonSubsequence(s1,s2);
        assertTrue(r1.equals("ace") && r2.equals("ace") && r3.equals("ace"));

        td.clearHashes();

        s1 = "abc";
        s2 = "def";
        r1 = rec.longestCommonSubsequence(s1,s2);
        r2 = td.longestCommonSubsequence(s1,s2);
        r3 = bu.longestCommonSubsequence(s1,s2);
        assertTrue(r1.isEmpty() && r2.isEmpty() && r3.isEmpty());
    }

    @Test
    void testLCSSpeed () {
        String s1, s2;
        long r1, r2, r3;

        Rec rec = new Rec();
        TopDown td = new TopDown();
        BottomUp bu = new BottomUp();

        s1 = "abcde".repeat(7);
        s2 = "ace".repeat(7);

        r1 = timeIt(() -> rec.longestCommonSubsequence(s1,s2));
        r2 = timeIt(() -> td.longestCommonSubsequence(s1,s2));
        r3 = timeIt(() -> bu.longestCommonSubsequence(s1,s2));

        System.out.printf("LCS Rec: %dms, TopDown: %dms, BottomUp: %dms\n", r1, r2, r3);
        assertTrue(r1 > r2);
        assertTrue(r1 > r3);
    }

    @SuppressWarnings("unchecked")
    @Test
    void treasureCollector() {
        Pair<Integer,Integer>[][] grid = new Pair[][] {
                {new Pair<>(1,1), new Pair<>(1,2), new Pair<>(1,3)},
                {new Pair<>(1,2), new Pair<>(1,3), new Pair<>(1,4)},
                {new Pair<>(1,3), new Pair<>(1,4), new Pair<>(1,5)}
        };

        Rec rec = new Rec();
        TopDown td = new TopDown();
        BottomUp bu = new BottomUp();

        int r1 = rec.treasureCollector(grid, 0, 1, 2);
        int r2 = td.treasureCollector(grid, 0, 1, 2);
        int r3 = bu.treasureCollector(grid, 0, 1, 2);
        assertTrue(r1 == 9 && r2 == 9 && r3 == 9);
    }

    @SuppressWarnings("unchecked")
    @Test
    void treasureCollectorSpeed() {
        Pair<Integer,Integer>[][] grid = new Pair[14][14];
        for (int i = 0; i < 14; i++)
            for (int j = 0; j < 14; j++)
                grid[i][j] = new Pair<>(1,1);

        Rec rec = new Rec();
        TopDown td = new TopDown();
        BottomUp bu = new BottomUp();

        long r1 = timeIt(() -> rec.treasureCollector(grid, 0, 4, 4));
        long r2 = timeIt(() -> td.treasureCollector(grid, 0, 4, 4));
        long r3 = timeIt(() -> bu.treasureCollector(grid, 0, 4, 4));

        System.out.printf("TC Rec: %dms, TopDown: %dms, BottomUp: %dms\n", r1, r2, r3);
        assertTrue(r1 > r2);
        assertTrue(r1 > r3);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testTreasureCollector2(){
        Rec rec = new Rec();
        TopDown td = new TopDown();
        BottomUp bu = new BottomUp();

        Pair<Integer, Integer>[][] grid = new Pair[0][0];

        assertEquals(0, rec.treasureCollector(grid, 0, 0, 1));
        assertEquals(0, td.treasureCollector(grid, 0, 0, 1));
        assertEquals(0, bu.treasureCollector(grid, 0, 0, 1));

        grid = new Pair[][]{
                {new Pair<>(1, 1), new Pair<>(1, 1), new Pair<>(1, 1)},
                {new Pair<>(1, 1), new Pair<>(1, 1), new Pair<>(1, 1)},
                {new Pair<>(1, 1), new Pair<>(1, 1), new Pair<>(1, 1)}
        };

        td.clearHashes();

        assertEquals(0, rec.treasureCollector(grid, 0, 1, 0));
        assertEquals(0, td.treasureCollector(grid, 0, 1, 0));
        assertEquals(0, bu.treasureCollector(grid, 0, 1, 0));

        grid = new Pair[][]{
                {new Pair<>(5, 10)}
        };

        td.clearHashes();

        assertEquals(10, rec.treasureCollector(grid, 0, 0, 5));
        assertEquals(10, td.treasureCollector(grid, 0, 0, 5));
        assertEquals(10, bu.treasureCollector(grid, 0, 0, 5));

        grid = new Pair[][]{
                {new Pair<>(10, 100), new Pair<>(10, 100)},
                {new Pair<>(10, 100), new Pair<>(10, 100)}
        };

        td.clearHashes();

        assertEquals(0, rec.treasureCollector(grid, 0, 0, 5));
        assertEquals(0, td.treasureCollector(grid, 0, 0, 5));
        assertEquals(0, bu.treasureCollector(grid, 0, 0, 5));

        grid = new Pair[][]{
                {new Pair<>(1, 100), new Pair<>(1, 100), new Pair<>(1, 100)},
                {new Pair<>(1, 100), new Pair<>(1, 100), new Pair<>(1, 100)},
                {new Pair<>(1, 100), new Pair<>(1, 1), new Pair<>(1, 100)}
        };

        td.clearHashes();

        assertEquals(1, rec.treasureCollector(grid, 2, 1, 5));
        assertEquals(1, td.treasureCollector(grid, 2, 1, 5));
        assertEquals(1, bu.treasureCollector(grid, 2, 1, 5));

        grid = new Pair[][]{
                {new Pair<>(1, 0), new Pair<>(2, 0)},
                {new Pair<>(3, 0), new Pair<>(4, 0)}
        };

        td.clearHashes();

        assertEquals(0, rec.treasureCollector(grid, 0, 0, 5));
        assertEquals(0, td.treasureCollector(grid, 0, 0, 5));
        assertEquals(0, bu.treasureCollector(grid, 0, 0, 5));

        Random rand = new Random();

        grid = new Pair[10][10];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                grid[i][j] = new Pair<>(rand.nextInt(1, 10), rand.nextInt(1, 10));
            }
        }

        td.clearHashes();

        assertTrue(rec.treasureCollector(grid, 0, 5, 10) > 0);
        assertTrue(td.treasureCollector(grid, 0, 5, 10) > 0);
        assertTrue(bu.treasureCollector(grid, 0, 5, 10) > 0);
    }

    @Test
    void testLCS2(){
        String s1, s2;

        Rec rec = new Rec();
        TopDown td = new TopDown();
        BottomUp bu = new BottomUp();

        s1 = "";
        s2 = "";

        td.clearHashes();

        assertEquals(0, rec.minEditDistance(s1, s2));
        assertEquals(0, td.minEditDistance(s1, s2));
        assertEquals(0, bu.minEditDistance(s1, s2));

        s1 = "";
        s2 = "abc";

        td.clearHashes();

        assertEquals(3, rec.minEditDistance(s1, s2));
        assertEquals(3, td.minEditDistance(s1, s2));
        assertEquals(3, bu.minEditDistance(s1, s2));

        s1 = "abc";
        s2 = "abc";

        td.clearHashes();

        assertEquals(0, rec.minEditDistance(s1, s2));
        assertEquals(0, td.minEditDistance(s1, s2));
        assertEquals(0, bu.minEditDistance(s1, s2));

        s1 = "a";
        s2 = "b";

        td.clearHashes();

        assertEquals(1, rec.minEditDistance(s1, s2));
        assertEquals(1, td.minEditDistance(s1, s2));
        assertEquals(1, bu.minEditDistance(s1, s2));

        s1 = "abc";
        s2 = "abcd";

        td.clearHashes();

        assertEquals(1, rec.minEditDistance(s1, s2));
        assertEquals(1, td.minEditDistance(s1, s2));
        assertEquals(1, bu.minEditDistance(s1, s2));

        s1 = "abcd";
        s2 = "bcd";

        td.clearHashes();

        assertEquals(1, rec.minEditDistance(s1, s2));
        assertEquals(1, td.minEditDistance(s1, s2));
        assertEquals(1, bu.minEditDistance(s1, s2));

        s1 = "abc";
        s2 = "cba";

        td.clearHashes();

        assertEquals(2, rec.minEditDistance(s1, s2));
        assertEquals(2, td.minEditDistance(s1, s2));
        assertEquals(2, bu.minEditDistance(s1, s2));

        s1 = "abc";
        s2 = "xyz";

        td.clearHashes();

        assertEquals(3, rec.minEditDistance(s1, s2));
        assertEquals(3, td.minEditDistance(s1, s2));
        assertEquals(3, bu.minEditDistance(s1, s2));

        s1 = "abcdefg";
        s2 = "abcdeXg";

        td.clearHashes();

        assertEquals(1, rec.minEditDistance(s1, s2));
        assertEquals(1, td.minEditDistance(s1, s2));
        assertEquals(1, bu.minEditDistance(s1, s2));

        s1 = "aaaa";
        s2 = "bbbb";

        td.clearHashes();

        assertEquals(4, rec.minEditDistance(s1, s2));
        assertEquals(4, td.minEditDistance(s1, s2));
        assertEquals(4, bu.minEditDistance(s1, s2));

        s1 = "abc";
        s2 = "ab";

        td.clearHashes();

        assertEquals(1, rec.minEditDistance(s1, s2));
        assertEquals(1, td.minEditDistance(s1, s2));
        assertEquals(1, bu.minEditDistance(s1, s2));

        s1 = "listen";
        s2 = "silent";

        td.clearHashes();

        assertEquals(4, rec.minEditDistance(s1, s2));
        assertEquals(4, td.minEditDistance(s1, s2));
        assertEquals(4, bu.minEditDistance(s1, s2));

        s1 = "a";
        s2 = "a";

        td.clearHashes();

        assertEquals(0, rec.minEditDistance(s1, s2));
        assertEquals(0, td.minEditDistance(s1, s2));
        assertEquals(0, bu.minEditDistance(s1, s2));

        s1 = "a";
        s2 = "z";

        td.clearHashes();

        assertEquals(1, rec.minEditDistance(s1, s2));
        assertEquals(1, td.minEditDistance(s1, s2));
        assertEquals(1, bu.minEditDistance(s1, s2));

        s1 = "a";
        s2 = "A";

        td.clearHashes();

        assertEquals(1, rec.minEditDistance(s1, s2));
        assertEquals(1, td.minEditDistance(s1, s2));
        assertEquals(1, bu.minEditDistance(s1, s2));

        s1 = "abc";
        s2 = "abcdef";

        td.clearHashes();

        assertEquals(3, rec.minEditDistance(s1, s2));
        assertEquals(3, td.minEditDistance(s1, s2));
        assertEquals(3, bu.minEditDistance(s1, s2));

    }

    @Test
    void testMED2(){
        String s1, s2;

        Rec rec = new Rec();
        TopDown td = new TopDown();
        BottomUp bu = new BottomUp();

        s1 = "";
        s2 = "";

        td.clearHashes();

        assertEquals("", rec.longestCommonSubsequence(s1, s2));
        assertEquals("", td.longestCommonSubsequence(s1, s2));
        assertEquals("", bu.longestCommonSubsequence(s1, s2));

        s1 = "abc";
        s2 = "";

        td.clearHashes();

        assertEquals("", rec.longestCommonSubsequence(s1, s2));
        assertEquals("", td.longestCommonSubsequence(s1, s2));
        assertEquals("", bu.longestCommonSubsequence(s1, s2));

        s1 = "abc";
        s2 = "def";

        td.clearHashes();

        assertEquals("", rec.longestCommonSubsequence(s1, s2));
        assertEquals("", td.longestCommonSubsequence(s1, s2));
        assertEquals("", bu.longestCommonSubsequence(s1, s2));

        s1 = "abc";
        s2 = "abc";

        td.clearHashes();

        assertEquals("abc", rec.longestCommonSubsequence(s1, s2));
        assertEquals("abc", td.longestCommonSubsequence(s1, s2));
        assertEquals("abc", bu.longestCommonSubsequence(s1, s2));

        s1 = "abc";
        s2 = "abcd";

        td.clearHashes();

        assertEquals("abc", rec.longestCommonSubsequence(s1, s2));
        assertEquals("abc", td.longestCommonSubsequence(s1, s2));
        assertEquals("abc", bu.longestCommonSubsequence(s1, s2));

        s1 = "abc";
        s2 = "cba";

        td.clearHashes();

        assertEquals(1, rec.longestCommonSubsequence(s1, s2).length());
        assertEquals(1, td.longestCommonSubsequence(s1, s2).length());
        assertEquals(1, bu.longestCommonSubsequence(s1, s2).length());

        s1 = "aebdc";
        s2 = "abc";

        td.clearHashes();

        assertEquals("abc", rec.longestCommonSubsequence(s1, s2));
        assertEquals("abc", td.longestCommonSubsequence(s1, s2));
        assertEquals("abc", bu.longestCommonSubsequence(s1, s2));

        s1 = "aaa";
        s2 = "a";

        td.clearHashes();

        assertEquals("a", rec.longestCommonSubsequence(s1, s2));
        assertEquals("a", td.longestCommonSubsequence(s1, s2));
        assertEquals("a", bu.longestCommonSubsequence(s1, s2));

        s1 = "ABC";
        s2 = "abc";

        td.clearHashes();

        assertEquals("", rec.longestCommonSubsequence(s1, s2));
        assertEquals("", td.longestCommonSubsequence(s1, s2));
        assertEquals("", bu.longestCommonSubsequence(s1, s2));

        s1 = "a";
        s2 = "a";

        td.clearHashes();

        assertEquals("a", rec.longestCommonSubsequence(s1, s2));
        assertEquals("a", td.longestCommonSubsequence(s1, s2));
        assertEquals("a", bu.longestCommonSubsequence(s1, s2));

        s1 = "abc";
        s2 = "xyz";

        td.clearHashes();

        assertEquals("", rec.longestCommonSubsequence(s1, s2));
        assertEquals("", td.longestCommonSubsequence(s1, s2));
        assertEquals("", bu.longestCommonSubsequence(s1, s2));

        s1 = "abc123";
        s2 = "xyz123";

        td.clearHashes();

        assertEquals("123", rec.longestCommonSubsequence(s1, s2));
        assertEquals("123", td.longestCommonSubsequence(s1, s2));
        assertEquals("123", bu.longestCommonSubsequence(s1, s2));

    }
}