import java.util.Arrays;

public class CoinChange {
    public static int[] denominations = new int[]{1, 5, 10, 25};

    public static int minChange(int amount) {
        //TODO - This function should return the minimum number of coins needed to make change for the given amount
        return 0;
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

//