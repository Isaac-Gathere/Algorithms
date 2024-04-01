/**
 *Illustrating that via AVL single rotation, any binary search tree T1 can be
 * transformed into another search tree T2 (with the same items)
 *
 * **/

class TreeNodeAVL {
    int val;
    TreeNodeAVL left, right;

    public TreeNodeAVL(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class BSTtoBalancedBST {

    // Function to perform left rotation
    TreeNodeAVL leftRotate(TreeNodeAVL y) {
        TreeNodeAVL x = y.right;
        TreeNodeAVL T2 = x.left;

        // Perform rotation
        x.left = y;
        y.right = T2;

        // Return new root
        return x;
    }

    // Function to perform right rotation
    TreeNodeAVL rightRotate(TreeNodeAVL x) {
        TreeNodeAVL y = x.left;
        TreeNodeAVL T2 = y.right;

        // Perform rotation
        y.right = x;
        x.left = T2;

        // Return new root
        return y;
    }

    // Function to insert a node into BST
    TreeNodeAVL insert(TreeNodeAVL node, int val) {
        // If the tree is empty, return a new node
        if (node == null)
            return new TreeNodeAVL(val);

        // Otherwise, recur down the tree
        if (val < node.val)
            node.left = insert(node.left, val);
        else if (val > node.val)
            node.right = insert(node.right, val);

        // Return the (unchanged) node pointer
        return node;
    }

    // Function to transform T1 into T2 using a single AVL rotation
    TreeNodeAVL transformTree(TreeNodeAVL root) {
        // Base case: if the tree is empty or has only one node, return root
        if (root == null || (root.left == null && root.right == null))
            return root;

        // Recursively transform the left subtree
        root.left = transformTree(root.left);

        // If the current node is unbalanced, perform single rotation
        if (getBalance(root) > 1) {
            if (getBalance(root.left) >= 0) // Left-Left case
                root = rightRotate(root);
            else { // Left-Right case
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        }
        else if (getBalance(root) < -1) {
            if (getBalance(root.right) <= 0) // Right-Right case
                root = leftRotate(root);
            else { // Right-Left case
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        }

        // Recursively transform the right subtree
        root.right = transformTree(root.right);

        return root;
    }

    // Function to get the balance factor of a node
    int getBalance(TreeNodeAVL node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // Function to calculate the height of a node
    int height(TreeNodeAVL node) {
        if (node == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // Function to perform inorder traversal of a tree
    void inorder(TreeNodeAVL node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        BSTtoBalancedBST bstToBalancedBst = new BSTtoBalancedBST();
        TreeNodeAVL root = null;
        int[] keys = {30, 20, 40, 10, 25, 35, 50};

        // Insert nodes into the initial BST
        for (int key : keys) {
            root = bstToBalancedBst.insert(root, key);
        }

        // Print the inorder traversal of the original tree
        System.out.println("Inorder traversal of the original tree:");
        bstToBalancedBst.inorder(root);
        System.out.println();

        // Transform the initial BST into a balanced BST using single AVL rotations
        root = bstToBalancedBst.transformTree(root);

        // Print the inorder traversal of the resulting balanced BST
        System.out.println("\nInorder traversal of the transformed tree:");
        bstToBalancedBst.inorder(root);

    }}