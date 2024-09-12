import java.util.*;

public class TimingRemoveDuplicates {

    // Method to remove duplicates from an Object array
    public static List<Integer> RemoveDuplicates1(List<Integer> input) {
        int n = input.size();

        // Make a copy of the input list
        List<Integer> temp = new ArrayList<>(input);

        // Sort the list
        Collections.sort(temp);

        // Use a List to store the result without duplicates
        List<Integer> output = new ArrayList<>();

        // Iterate over the sorted list and add unique elements to the output list
        for (int i = 0; i < n - 1; i++) {
            if (!temp.get(i).equals(temp.get(i + 1))) {
                output.add(temp.get(i));
            }
        }

        // Add the last element as it's not checked in the loop
        output.add(temp.get(n - 1));

        return output;
    }


    public static List<Integer> RemoveDuplicates2(List<Integer> input) {
        List<Integer> output = new ArrayList<>();
        boolean[] mark = new boolean[input.size()];

        // Initialize the mark array
        Arrays.fill(mark, true);

        // Iterate and mark duplicates
        for (int i = 0; i < input.size(); i++) {
            if (mark[i]) {
                for (int j = i + 1; j < input.size(); j++) {
                    if (input.get(i).equals(input.get(j))) {
                        mark[j] = false;
                    }
                }
            }
        }

        // Add only non-marked elements to the output
        for (int i = 0; i < input.size(); i++) {
            if (mark[i]) {
                output.add(input.get(i));
            }
        }

        return output;
    }

    public static List<Integer> RemoveDuplicates3(List<Integer> arr) {
        Set<Integer> seen = new HashSet<>();  // Set to store unique elements
        List<Integer> result = new ArrayList<>();  // List to store the result without duplicates

        for (Integer num : arr) {
            if (!seen.contains(num)) {
                seen.add(num);
                result.add(num);
            }
        }

        return result;
    }

    // Main method to test the RemoveDuplicates function
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            input.add(i % 10);  // Randomized input of 100000 values
        }

        // TODO: Time the 3 implementations of RemoveDuplicates. Comment them below in <> space

        // All should print a list from 0 to 9
        // Time1 : <time elapsed by execution of RemoveDuplicates1 on your local machine>
        List<Integer> result1 = RemoveDuplicates1(input);
        System.out.println(result1);
        // Time2 : <time elapsed by execution of RemoveDuplicates2 on your local machine>
        List<Integer> result2 = RemoveDuplicates2(input);
        System.out.println(result2);
        // Time3 : <time elapsed by execution of RemoveDuplicates3 on your local machine>
        List<Integer> result3 = RemoveDuplicates3(input);
        System.out.println(result3);
        
        // TODO: Write comments here in code on what analysis you made on their time complexities

    }
}
