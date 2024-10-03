import org.junit.jupiter.api.Test;

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

}