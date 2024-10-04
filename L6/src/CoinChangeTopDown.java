import java.util.HashMap;

public class CoinChangeTopDown {
    public static HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
    public static int[] denominations = {1, 5, 10, 25};

    public static int minChangeMemo(int amount) {
        /*
        This is the memorized solution. It is much more efficient than the recursive solution because it uses a hashmap
        to store each recursive result. This method will be very similar to minChangeReg, but will use a hashmap
        to store each recursive result, and then retrieve it if it has already been calculated.
        */
        if(amount == 0){
            return 0;
        }

        int[] options = new int[denominations.length];

        if(hm.containsKey(amount)){
            return hm.get(amount);
        }
        else {
            for(int i = 0; i < options.length; i++) {
                if(amount >= denominations[i]) {
                    options[i] = minChangeReg(amount - denominations[i]);
                } else {
                    options[i] = -1;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int i = 0; i < options.length; i++){
            if(options[i] == -1){
                continue;
            }
            if(options[i] < min){
                minIndex = i;
            }
        }

        hm.put(amount, options[minIndex]);
        return options[minIndex] + 1;
    }
    public static int minChangeReg(int amount) {
        /*
        This is your standard recursive solution. It is very inefficient because it does not use memoization,
        but I would recommend tackling this one first as this is what you are most used to.
        */
        if(amount == 0){
            return 0;
        }

        int[] options = new int[denominations.length];

        for(int i = 0; i < options.length; i++){
            if(amount >= denominations[i]) {
                options[i] = minChangeReg(amount - denominations[i]);
            }
            else{
                options[i] = -1;
            }
        }

        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int i = 0; i < options.length; i++){
            if(options[i] == -1){
                continue;
            }
            if(options[i] < min){
                minIndex = i;
            }
        }

        return options[minIndex] + 1;
    }
    public static int add(int a, int b) {
        return a == Integer.MAX_VALUE || b == Integer.MAX_VALUE ? Integer.MAX_VALUE : a + b;
    }

    public static void main(String[] args) {
        for (int i = 23; i < 70; i += 23) {
            long start = System.currentTimeMillis();
            System.out.println("The minimum number of coins needed to make " + i + " cents is: " + minChangeMemo(i));
            long end = System.currentTimeMillis();
            System.out.println("Time taken WITH memoization: " + (end - start) + "ms");
        }
        System.out.println("--------------------------------------------------");
        for (int i = 23; i < 70; i += 23) {
            long start = System.currentTimeMillis();
            System.out.println("The minimum number of coins needed to make " + i + " cents is: " + minChangeReg(i));
            long end = System.currentTimeMillis();
            System.out.println("Time taken WITHOUT memoization: " + (end - start) + "ms");
        }

        System.out.println("--------------------------------------------------");

        long start = System.currentTimeMillis();
        System.out.println("The minimum number of coins needed to make 10000 cents is: " + minChangeMemo(10000));
        long end = System.currentTimeMillis();
        System.out.println("Time taken WITH memoization: " + (end - start) + "ms");
    }
}
