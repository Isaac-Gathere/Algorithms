/**
 * Proving that a binary tree with k leaves has height at least log k.
 *
 *
 **/

public class BinaryTreeHeightProof {

    // Function to calculate the height of a binary tree with k leaves
    public static int calculateHeight(int k) {
        // Base case: height of a tree with 1 leaf is 0
        if (k == 1) {
            return 0;
        }

        // Calculate the height using logarithm base 2
        return (int) Math.ceil(Math.log(k) / Math.log(2));
    }

    public static void main(String[] args) {
        // Test cases
        int[] leavesCounts = {1, 2, 3, 4, 5, 6, 7, 8, 16, 32, 64}; // Various values of k
        for (int k : leavesCounts) {
            int height = calculateHeight(k);
            System.out.println("A binary tree with " + k + " leaves has height at least " + height);
        }
    }
}
