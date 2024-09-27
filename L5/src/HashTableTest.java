import java.util.function.ToDoubleBiFunction;

//TODO : Completing the findSlot code and following the instructions in the comments below


public class HashTableTest {

    public static void main(String[] args) {
        // Create a hash table with a fixed capacity of 11
        HashTable<Integer, String> hashTable = new HashTable<>(11);

        // Insert values to simulate a partially filled hash table
        hashTable.put(22, "Value for 22");
        hashTable.put(1, "Value for 1");
        hashTable.put(5, "Value for 5");
        hashTable.put(15, "Value for 15");
        hashTable.put(12, "Value for 12");

        // Test cases using findSlot
        System.out.println("Slot for key 5 (should be found): " + hashTable.findSlot(hashTable.hash(5), 5));  // should find key 5
        System.out.println("Slot for key 15 (should be found): " + hashTable.findSlot(hashTable.hash(15), 15));  // should find key 15

        // Try to find an empty slot for a new key
        System.out.println("First available slot for key 7: " + hashTable.findSlot(hashTable.hash(7), 7));  // should return a negative index for first available slot
    }
}

class HashTable<K, V> {
    private Entry<K, V>[] table;   // the table array for storing entries
    private int capacity;          // table capacity

    public HashTable(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];  // initialize the table
    }

    // Simple hash function (modulo capacity)
    public int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }



    //TODO 1: Complete the findSlot method and run it against the input to see if you get the same output as your predicted output.
    /**
     * Returns index with key k, or -(a+1) such that k could be added at index a.
     */
    public int findSlot(int h, K k) {
          // Initialize: no available slot found so far (-1 means no slot found)
               // Start scanning the table from the hash index h (hash of the key k)

        // Start a cyclic search to find either an empty slot, a defunct slot, or the matching key

            // TODO: Fill in the code here
            // 1. Check if the slot at index j is available.
            // 2. Mark the first available slot (if it hasn't been marked already).
            // 3. Stop searching immediately if the slot is empty.
            // 4. Check if the key at index j matches the search key and return the index if true.
            // 5. Move to the next slot in a cyclic manner using modulus operation.

           // Continue searching until we return to the starting index h (End of do-while)

        // If the key wasn't found, return the first available slot using -(avail + 1)
          // Returning the first available slot as a negative value if key not found

        int j = h;
        int available = -1;

        do{
            if(isAvailable(j)){
                if(available == -1){
                    available = j;
                }
            }
            else if(table[j].getKey().equals(k)){
                return j;
            }

            j = mod(j + 1, capacity);
        }while(j != h);

        return -(available + 1);
    }


    int mod(int a, int b){
        return (a % b + b) % b;
    }


    // Method to check if the slot is available (empty or defunct)
    public boolean isAvailable(int j) {
        return table[j] == null;
    }

    // Put a new key-value pair in the table
    public void put(K key, V value) {
        int h = hash(key);
        int index = findSlot(h, key);
        if (index >= 0) {
            // If key found, replace its value
            table[index].setValue(value);
        } else {
            // If key not found, insert at the available slot
            int newIndex = -(index + 1);
            table[newIndex] = new Entry<>(key, value);
        }
    }

    // Entry class representing key-value pairs
    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}

// TODO 2 : Write and explain the outputs you got for this code

/**
 * My code found key 5 at slot 5, 15 at slot 4, and the first available slot for key 7 was -8.
 * key 5 was found at 5 because the capacity is 11 and 5 % 11 = 5, which was available when it was inserted
 * key 15 was found at 4 because 15 % 11 = 4 and 4 was available when it was inserted.
 * The first available slot for key 7 was -8 because 7 % 11 = 7, 7 was available, and -(7 + 1) = -8
 */

// TODO 3 :  For question 1 in the lab manual, write down the keys you got for each index. If key is null, type null. We have solved two spots for you.


//Index   Key

//0 - 22
//1 -    null
//2 - null
//3 - 3
//4 - 25
//5 - 5
//6 - 14
//7 - null
//8 - null
//9 -    null
//10 - 21

