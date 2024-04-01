/**
 * This code implements algorithms for various operations on a binary tree, including:
 * 1. preorderNext(x): return the node visited after node x in a pre-order traversal of BT.
 * 2. postorderNext(x): return the node visited after node x in a post-order traversal of BT.
 * 3. inorderNext(x): return the node visited after node x in an in-order traversal of BT.
 *
 * The worst-case running times for each implementation are as follows:
 * - preorderNext: O(h), where h is the height of the binary tree.
 * - postorderNext: O(h), where h is the height of the binary tree.
 * - inorderNext: O(h), where h is the height of the binary tree.
 */

import java.util.Stack;

/**
 * BinaryTreeNode class represents a node in a binary tree.
 */
class BinaryTreeNode {
    int data;
    BinaryTreeNode left, right;

    public BinaryTreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

/**
 * BinaryTree class represents a binary tree and provides methods for
 * preorder, inorder, and postorder traversal, along with methods to find
 * the next node in each traversal after a given node.
 */
public class BinaryTree {
    protected BinaryTreeNode root;

    /**
     * Constructs an empty binary tree.
     */
    public BinaryTree() {
        this.root = null;
    }

    /**
     * Returns the node visited after node x in a pre-order traversal of BT.
     * If no such node exists, returns null.
     *
     * @param x The node for which next node in pre-order traversal is to be found.
     * @return The next node in pre-order traversal after node x, or null if no such node exists.
     */
    public BinaryTreeNode preorderNext(BinaryTreeNode x) {
        if (x == null)
            return null;
        // If x has a left child, return left child
        if (x.left != null)
            return x.left;
        // If x has a right child, return right child
        if (x.right != null)
            return x.right;
        // Traverse up the tree until reaching a node that has a right sibling or is null
        BinaryTreeNode parent = x;
        while (parent != null && (parent.right == null || (parent.right == x && parent.left == null))) {
            x = parent;
            parent = parent.left;
            while (parent == null && x != null) {
                parent = x.right;
                x = x.right;
            }
        }
        // If reached root without finding a node with a right sibling, return null
        if (parent == null)
            return null;
        // If found a node with a right sibling, return the leftmost node of the right sibling's subtree
        x = parent.right;
        while (x.left != null)
            x = x.left;
        return x;
    }

    /**
     * Returns the node visited after node x in a post-order traversal of BT.
     * If no such node exists, returns null.
     *
     * @param x The node for which next node in post-order traversal is to be found.
     * @return The next node in post-order traversal after node x, or null if no such node exists.
     */
    public BinaryTreeNode postorderNext(BinaryTreeNode x) {
        if (x == null)
            return null;
        BinaryTreeNode parent = x;
        BinaryTreeNode sibling;
        // Traverse up the tree until reaching root or a node that is the right child of its parent
        while (parent != null && parent.left != x && (parent.right == null || parent.right == x)) {
            x = parent;
            parent = parent.left != null ? parent.left : parent.right;
        }
        // If reached root, return null (last node in post-order traversal)
        if (parent == null)
            return null;
        // If parent has a right child, traverse to the right subtree
        if (parent.right != null) {
            x = parent.right;
            // Traverse down the leftmost path of the right subtree
            while (x.left != null)
                x = x.left;
            return x;
        }
        // If parent has no right child, return the parent
        return parent;
    }

    /**
     * Returns the node visited after node x in an in-order traversal of BT.
     * If no such node exists, returns null.
     *
     * @param x The node for which next node in in-order traversal is to be found.
     * @return The next node in in-order traversal after node x, or null if no such node exists.
     */
    public BinaryTreeNode inorderNext(BinaryTreeNode x) {
        if (x == null)
            return null;
        // If x has a right child, find and return the leftmost node in its right subtree
        if (x.right != null) {
            x = x.right;
            while (x.left != null)
                x = x.left;
            return x;
        }
        // If x doesn't have a right child, traverse up the tree until reaching a node that is a left child
        // Return the parent of that node if it exists, else return null
        BinaryTreeNode parent = x;
        while (parent != null && parent.right == x) {
            x = parent;
            parent = parent.left;
        }
        return parent;
    }

    /**
     * A utility method to perform an in-order traversal of the binary tree.
     * This method is used for testing purposes.
     */
    public void inorderTraversal() {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.print(current.data + " ");
            current = current.right;
        }
        System.out.println();
    }

    /**
     * Test method to demonstrate the functionality of the preorderNext, postorderNext, and inorderNext methods.
     */
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.root = new BinaryTreeNode(1);
        tree.root.left = new BinaryTreeNode(2);
        tree.root.right = new BinaryTreeNode(3);
        tree.root.left.left = new BinaryTreeNode(4);
        tree.root.left.right = new BinaryTreeNode(5);
        tree.root.right.left = new BinaryTreeNode(6);
        tree.root.right.right = new BinaryTreeNode(7);

        System.out.println("Inorder traversal:");
        tree.inorderTraversal();

        System.out.println("Next node after node 2 in preorder traversal: " + tree.preorderNext(tree.root.left).data);
        System.out.println("Next node after node 2 in postorder traversal: " + tree.postorderNext(tree.root.left).data);
        System.out.println("Next node after node 2 in inorder traversal: " + tree.inorderNext(tree.root.left).data);
    }
}
