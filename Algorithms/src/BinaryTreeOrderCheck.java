/**
 * BinaryTreeValidator class represents a node in a binary tree.
 * Design a recursive linear-time algorithm that tests whether a binary tree satisfies the
 * search tree order property at every node.
 */
class BinaryTreeValid {
    int data;
    BinaryTreeValid left, right;

    public BinaryTreeValid(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

/**
 * BinaryTreeOrderChecker class provides methods to check whether a binary tree satisfies
 * the search tree order property at every node.
 */
public class BinaryTreeOrderCheck {

    /**
     * Checks whether the given binary tree satisfies the search tree order property at every node.
     *
     * @param root The root node of the binary tree.
     * @return True if the binary tree satisfies the search tree order property at every node, false otherwise.
     */
    public static boolean isBinaryTreeOrdered(BinaryTreeValid root) {
        return isSubtreeOrdered(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Helper method to recursively check whether a subtree satisfies the search tree order property.
     *
     * @param node The root node of the current subtree.
     * @param min  The minimum allowed value for nodes in the subtree.
     * @param max  The maximum allowed value for nodes in the subtree.
     * @return True if the subtree rooted at 'node' satisfies the search tree order property, false otherwise.
     */
    private static boolean isSubtreeOrdered(BinaryTreeValid node, int min, int max) {
        // Base case: If the node is null, it is vacuously ordered.
        if (node == null)
            return true;

        // Check if the current node violates the search tree order property.
        if (node.data < min || node.data > max)
            return false;

        // Recursively check the left and right subtrees.
        // In the left subtree, the maximum allowed value becomes the value of the current node.
        // In the right subtree, the minimum allowed value becomes the value of the current node.
        return isSubtreeOrdered(node.left, min, node.data - 1) &&
                isSubtreeOrdered(node.right, node.data + 1, max);
    }

    /**
     * Prints the binary tree in inorder traversal.
     *
     * @param root The root node of the binary tree.
     */
    public static void printBinaryTree(BinaryTreeValid root) {
        if (root == null)
            return;

        printBinaryTree(root.left);
        System.out.print(root.data + " ");
        printBinaryTree(root.right);
    }

    /**
     * Test method to demonstrate the functionality of isBinaryTreeOrdered.
     */
    public static void main(String[] args) {
        // Ordered Binary Tree
        BinaryTreeValid orderedRoot = new BinaryTreeValid(5);
        orderedRoot.left = new BinaryTreeValid(3);
        orderedRoot.right = new BinaryTreeValid(7);
        orderedRoot.left.left = new BinaryTreeValid(2);
        orderedRoot.left.right = new BinaryTreeValid(4);
        orderedRoot.right.left = new BinaryTreeValid(6);
        orderedRoot.right.right = new BinaryTreeValid(8);

        // Unordered Binary Tree
        BinaryTreeValid unorderedRoot = new BinaryTreeValid(5);
        unorderedRoot.left = new BinaryTreeValid(3);
        unorderedRoot.right = new BinaryTreeValid(7);
        unorderedRoot.left.left = new BinaryTreeValid(6); // Violates order property
        unorderedRoot.left.right = new BinaryTreeValid(4);
        unorderedRoot.right.left = new BinaryTreeValid(2);
        unorderedRoot.right.right = new BinaryTreeValid(8);

        // Test ordered binary tree
        System.out.println("Ordered Binary Tree:");
        printBinaryTree(orderedRoot);
        System.out.println("\nIs the binary tree ordered? " + isBinaryTreeOrdered(orderedRoot));

        // Test unordered binary tree
        System.out.println("\nUnordered Binary Tree:");
        printBinaryTree(unorderedRoot);
        System.out.println("\nIs the binary tree ordered? " + isBinaryTreeOrdered(unorderedRoot));
    }
}
