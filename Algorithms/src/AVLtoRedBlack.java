/** 9. Illustrating that the nodes of any AVL tree T can be colored “red” and “black” so that T
 becomes a red-black tree
 *
 **/


class TreeNode {
    int val;
    TreeNode left, right;
    int height; // Height of the subtree rooted at this node
    boolean isRed; // Color of the node, true if red, false if black

    public TreeNode(int val) {
        this.val = val;
        this.height = 1;
        this.isRed = false; // Initially color all nodes as black
        this.left = this.right = null;
    }
}

public class AVLtoRedBlack {

    // Function to calculate the height of a node
    int height(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Function to perform right rotation
    TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Function to perform left rotation
    TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Function to get balance factor of a node
    int getBalance(TreeNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // Function to insert a node into AVL tree
    TreeNode insert(TreeNode node, int val) {
        // Perform standard BST insertion
        if (node == null)
            return new TreeNode(val);

        if (val < node.val)
            node.left = insert(node.left, val);
        else if (val > node.val)
            node.right = insert(node.right, val);
        else // Duplicate keys not allowed
            return node;

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node
        int balance = getBalance(node);

        // If this node becomes unbalanced, perform rotations
        // Right Right Case
        if (balance > 1 && val < node.left.val)
            return rightRotate(node);

        // Left Left Case
        if (balance < -1 && val > node.right.val)
            return leftRotate(node);

        // Right Left Case
        if (balance > 1 && val > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Left Right Case
        if (balance < -1 && val < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // return the (unchanged) node pointer
        return node;
    }

    // Function to color the nodes of AVL tree to transform it into red-black tree
    void colorNodes(TreeNode node) {
        if (node == null)
            return;

        // Color the node based on balance factor
        if (getBalance(node) < 0)
            node.isRed = true;
        else
            node.isRed = false;

        // Recursively color left and right subtrees
        colorNodes(node.left);
        colorNodes(node.right);
    }

    // Function to print the tree nodes inorder
    void inorder(TreeNode node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print(node.val + "(" + (node.isRed ? "Red" : "Black") + ") ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        AVLtoRedBlack avlToRedBlack = new AVLtoRedBlack();
        TreeNode root = null;
        int[] keys = {10, 20, 30, 40, 50, 25};

        // Insert nodes into AVL tree
        for (int key : keys) {
            root = avlToRedBlack.insert(root, key);
        }

        // Color the nodes to transform AVL tree into red-black tree
        avlToRedBlack.colorNodes(root);

        // Print inorder traversal of red-black tree
        System.out.println("Inorder traversal of red-black tree:");
        avlToRedBlack.inorder(root);
    }
}
