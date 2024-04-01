/**
 *(20 marks) Exercise 8.2. Illustrate what happens when the sequence 1, 5, 2, 4, 3 is added to an empty
 * ScapegoatTree, and show where the credits described in the proof of Lemma 8.3 go, and how they
 * are used during this sequence of addition
 *
 * ScapegoatTreeNode represents a node in a Scapegoat Tree.
 */
class ScapegoatTreeNode {
    int key;
    ScapegoatTreeNode left, right, parent;

    /**
     * Constructs a new ScapegoatTreeNode with the given key.
     *
     * @param key The key value of the node.
     */
    public ScapegoatTreeNode(int key) {
        this.key = key;
        left = right = parent = null;
    }
}

/**
 * ScapegoatTree is a self-balancing binary search tree that maintains balance using a logarithmic rebalance
 * operation known as rebuilding. It maintains a balance factor alpha (usually set to 2/3) and rebuilds
 * subtrees that become unbalanced according to this factor.
 */
public class ScapegoatTree {
    private ScapegoatTreeNode root;
    private double alpha = 0.667; // log(3/2)

    /**
     * Constructs an empty Scapegoat Tree.
     */
    public ScapegoatTree() {
        root = null;
    }

    /**
     * Inserts a key into the Scapegoat Tree.
     *
     * @param key The key to insert.
     */
    public void insert(int key) {
        root = insertRec(root, key);
    }

    /**
     * Recursively inserts a key into the Scapegoat Tree.
     * If the subtree rooted at the current node becomes unbalanced after insertion,
     * it triggers a rebuild operation to restore balance.
     *
     * @param root The root of the subtree.
     * @param key  The key to insert.
     * @return The root of the modified subtree.
     */
    private ScapegoatTreeNode insertRec(ScapegoatTreeNode root, int key) {
        if (root == null)
            return new ScapegoatTreeNode(key);

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        // Check if the subtree rooted at 'root' is unbalanced
        if (isUnbalanced(root))
            root = rebuild(root);

        return root;
    }

    /**
     * Checks if the subtree rooted at a given node is unbalanced.
     * A subtree is considered unbalanced if the height of the taller subtree
     * is greater than alpha times the height of the shorter subtree plus one.
     *
     * @param node The root of the subtree to check.
     * @return True if the subtree is unbalanced, false otherwise.
     */
    private boolean isUnbalanced(ScapegoatTreeNode node) {
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        int maxHeight = Math.max(leftHeight, rightHeight);
        int minHeight = Math.min(leftHeight, rightHeight);
        return (maxHeight > alpha * (minHeight + 1));
    }

    /**
     * Rebuilds an unbalanced subtree to restore balance.
     * This operation constructs a new subtree with the same keys
     * but balanced structure by performing an inorder traversal
     * to collect the nodes and then recursively building the tree.
     *
     * @param node The root of the unbalanced subtree.
     * @return The root of the rebuilt subtree.
     */
    private ScapegoatTreeNode rebuild(ScapegoatTreeNode node) {
        ScapegoatTreeNode[] nodes = new ScapegoatTreeNode[size(node)];
        storeInOrder(node, nodes, 0);
        return buildTree(nodes, 0, nodes.length - 1);
    }

    // Method to store nodes of the subtree rooted at 'node' in an array
    private int storeInOrder(ScapegoatTreeNode node, ScapegoatTreeNode[] nodes, int index) {
        if (node == null)
            return index;
        index = storeInOrder(node.left, nodes, index);
        nodes[index++] = node;
        return storeInOrder(node.right, nodes, index);
    }

    // Method to build a balanced subtree from the array of nodes
    private ScapegoatTreeNode buildTree(ScapegoatTreeNode[] nodes, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) / 2;
        ScapegoatTreeNode node = nodes[mid];
        node.left = buildTree(nodes, start, mid - 1);
        if (node.left != null)
            node.left.parent = node;
        node.right = buildTree(nodes, mid + 1, end);
        if (node.right != null)
            node.right.parent = node;
        return node;
    }

    // Method to calculate the height of the tree
    private int height(ScapegoatTreeNode node) {
        if (node == null)
            return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Method to calculate the size of the subtree rooted at 'node'
    private int size(ScapegoatTreeNode node) {
        if (node == null)
            return 0;
        return size(node.left) + 1 + size(node.right);
    }

    // Inorder traversal to print the tree
    public void inorderTraversal(ScapegoatTreeNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }

    public static void main(String[] args) {
        ScapegoatTree tree = new ScapegoatTree();
        int[] sequence = {1, 5, 2, 4, 3};

        System.out.println("Inserting sequence: ");
        for (int key : sequence) {
            tree.insert(key);
            System.out.print(key + " ");
        }

        System.out.println("\nInorder traversal of the Scapegoat Tree:");
        tree.inorderTraversal(tree.root);
    }
}
