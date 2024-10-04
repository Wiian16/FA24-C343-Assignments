import java.util.Arrays;

public class CoinChange {
    public static int[] denominations = new int[]{1, 5, 10, 25};

    public static int minChange(int amount) {
        int[] dp = new int[amount + 1];

        dp[0] = 0;

        for(int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for(int i = 1; i < dp.length; i++){

            for(int coin : denominations){
                if(coin <= i){
                    if(dp[i] > 1 + dp[i - coin]){
                        dp[i] = 1 + dp[i - coin];
                    }
                }
            }
        }
        if(dp[amount] != Integer.MAX_VALUE){
            return dp[amount];
        }
        return -1;
    }

    /*
    This is a helper function that you may find useful for
    adding numbers while avoiding the MAX_VALUE overflow problem
     */
    public static int add(int x, int y) {
        if (x == Integer.MAX_VALUE || y == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return x + y;
    }


    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println("The minimum number of coins to make change for " + i + " is: " + minChange(i));
        }
    }
}

//TODO: Write a small description on what is different between the two approaches here:
/*
The main difference is that in bottom up, everything is calculated, even if we don't need that value, where in top down,
only the values that we need will be calculated
 */
//