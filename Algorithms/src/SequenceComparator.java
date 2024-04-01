/**
 * Determines if two sequences contain the same set of elements.
 *
 * This method compares two sequences for set equality. It first checks if the lengths of the sequences are equal.
 * If not, it returns false, as sets with different numbers of elements cannot be the same. Then, it sorts both
 * sequences in ascending order using the Arrays.sort() method. Finally, it iterates through both sorted sequences
 * simultaneously. If at any point corresponding elements differ, it returns false. Otherwise, if all elements
 * match, it returns true.
 *
 * @param s1 The first sequence to compare.
 * @param s2 The second sequence to compare.
 * @return True if both sequences contain the same set of elements, false otherwise.
 */

import java.util.Arrays;
public class SequenceComparator {

    public static boolean sameSet(int[] s1, int[] s2) {
        if (s1.length != s2.length) {
            return false; // If lengths are different, sets cannot be the same
        }

        Arrays.sort(s1); // Sort both sequences
        Arrays.sort(s2);

        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != s2[i]) {
                return false; // If any elements differ, sets are different
            }
        }

        return true; // If all elements match, sets are the same
    }

    /**
     * Main method for testing the sameSet function.
     *
     * This method demonstrates the usage of the sameSet function by providing sample input sequences and printing out
     * whether they have the same set of elements or not.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] sequence1 = {1, 2, 3, 4, 5};
        int[] sequence2 = {5, 4, 3, 2, 1};
        int[] sequence3 = {1, 2, 3, 4, 5, 6};

        System.out.println("Sequence 1 and Sequence 2 have the same set of elements: " + sameSet(sequence1, sequence2));
        System.out.println("Sequence 1 and Sequence 3 have the same set of elements: " + sameSet(sequence1, sequence3));
    }
}
