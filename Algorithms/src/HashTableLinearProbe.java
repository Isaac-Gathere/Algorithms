/**
 * Implement a commonly used hash table in a program that handles collision using linear
 * probing. Using (K mod 13) as the hash function, store the following elements in the table: {1, 5, 21,
 * 26, 39, 14, 15, 16, 17, 18, 19, 20, 111, 145, 146}.
 * HashTableLinearProbe class represents a hash table that handles collisions using linear probing.
 */

public class HashTableLinearProbe {
    private int[] table;
    private int size;

    /**
     * Constructs a hash table with a specified capacity.
     *
     * @param capacity The capacity of the hash table.
     */
    public HashTableLinearProbe(int capacity) {
        table = new int[capacity];
        size = 0;
    }

    /**
     * Inserts a key into the hash table using linear probing for collision resolution.
     *
     * @param key The key to insert.
     */
    public void insert(int key) {
        int index = hashFunction(key);
        while (table[index] != 0) {
            index = (index + 1) % table.length; // Linear probing
        }
        table[index] = key;
        size++;
    }

    /**
     * Searches for a key in the hash table.
     *
     * @param key The key to search for.
     * @return True if the key is found, false otherwise.
     */
    public boolean search(int key) {
        int index = hashFunction(key);
        while (table[index] != 0) {
            if (table[index] == key)
                return true;
            index = (index + 1) % table.length; // Linear probing
        }
        return false;
    }

    /**
     * Computes the hash value of a key using the hash function h(k) = k mod 13.
     *
     * @param key The key for which to compute the hash value.
     * @return The hash value of the key.
     */
    private int hashFunction(int key) {
        return key % 13;
    }

    public void printTable() {
        System.out.print("Hash Table with Linear Probing: ");
        for (int i = 0; i < table.length; i++) {
            if (table[i] != 0) {
                System.out.print(table[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] elements = {1, 5, 21, 26, 39, 14, 15, 16, 17, 18, 19, 20, 111, 145, 146};
        HashTableLinearProbe hashTable = new HashTableLinearProbe(13);

        // Insert elements into the hash table
        for (int element : elements) {
            hashTable.insert(element);
        }

        // Print the hash table
        hashTable.printTable();
    }
}
