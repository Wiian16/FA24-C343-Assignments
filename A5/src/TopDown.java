import java.util.HashMap;

public class TopDown extends Rec {
    private final HashMap<Pair<String,String>,Integer> memoMED;
    private final HashMap<Pair<String,String>,String> memoLCS;
    private final HashMap<Pair<Pair<Integer,Integer>,Integer>,Integer> memoTC;

    TopDown () {
        memoMED = new HashMap<>();
        memoLCS = new HashMap<>();
        memoTC = new HashMap<>();
    }

    void clearHashes() {
        memoMED.clear();
        memoLCS.clear();
        memoTC.clear();
    }

    public int minEditDistance(String s1, String s2) {
        Pair<String,String> key = new Pair<>(s1,s2);
        if (memoMED.containsKey(key)) return memoMED.get(key);

        int result = super.minEditDistance(s1, s2);

        memoMED.put(key, result);
        return result;
    }

    // -----------------------------------------------------------------------------------

        public String longestCommonSubsequence(String s1, String s2) {
        Pair<String,String> key = new Pair<>(s1,s2);
        if (memoLCS.containsKey(key)) return memoLCS.get(key);

        String result = super.longestCommonSubsequence(s1, s2);

        memoLCS.put(key, result);
        return result;
    }

    // -----------------------------------------------------------------------------------

    public int treasureCollector (Pair<Integer,Integer>[][] grid, int row, int col, int availableWeight) {
        Pair<Pair<Integer,Integer>,Integer> key = new Pair<>(new Pair<>(row,col),availableWeight);
        if (memoTC.containsKey(key)) return memoTC.get(key);

        int result = super.treasureCollector(grid, row, col, availableWeight);

        memoTC.put(key, result);
        return result;
    }
}
