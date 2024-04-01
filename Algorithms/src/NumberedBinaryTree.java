/**
 * Subclass of BinaryTree with additional fields for preorder, postorder, and inorder numbers.
 */
class NumberedBinaryTreeNode extends BinaryTreeNode {
    int preorderNumber;
    int inorderNumber;
    int postorderNumber;

    public NumberedBinaryTreeNode(int data) {
        super(data);
        this.preorderNumber = -1;
        this.inorderNumber = -1;
        this.postorderNumber = -1;
    }
}

/**
 * Subclass of BinaryTree with methods to assign preorder, inorder, and postorder numbers to each node.
 */
public class NumberedBinaryTree extends BinaryTree {

    /**
     * Assigns preorder numbers to each node in the tree.
     * Runs in O(n) time.
     */
    public void preOrderNumber() {
        preOrderNumber(root, 1);
    }

    private int preOrderNumber(BinaryTreeNode node, int count) {
        if (node == null)
            return count;

        ((NumberedBinaryTreeNode) node).preorderNumber = count++;
        count = preOrderNumber(node.left, count);
        count = preOrderNumber(node.right, count);
        return count;
    }

    /**
     * Assigns inorder numbers to each node in the tree.
     * Runs in O(n) time.
     */
    public void inOrderNumber() {
        inOrderNumber(root);
    }

    private int inOrderNumber(BinaryTreeNode node) {
        if (node == null)
            return 1;

        int count = inOrderNumber(node.left);
        ((NumberedBinaryTreeNode) node).inorderNumber = count++;
        count = inOrderNumber(node.right);
        return count;
    }

    /**
     * Assigns postorder numbers to each node in the tree.
     * Runs in O(n) time.
     */
    public void postOrderNumber() {
        postOrderNumber(root, 1);
    }

    private int postOrderNumber(BinaryTreeNode node, int count) {
        if (node == null)
            return count;

        count = postOrderNumber(node.left, count);
        count = postOrderNumber(node.right, count);
        ((NumberedBinaryTreeNode) node).postorderNumber = count++;
        return count;
    }

    /**
     * Test method to demonstrate the functionality of preorder, inorder, and postorder number assignments.
     */
    public static void main(String[] args) {
        NumberedBinaryTree tree = new NumberedBinaryTree();

        tree.root = new NumberedBinaryTreeNode(1);
        tree.root.left = new NumberedBinaryTreeNode(2);
        tree.root.right = new NumberedBinaryTreeNode(3);
        tree.root.left.left = new NumberedBinaryTreeNode(4);
        tree.root.left.right = new NumberedBinaryTreeNode(5);
        tree.root.right.left = new NumberedBinaryTreeNode(6);
        tree.root.right.right = new NumberedBinaryTreeNode(7);

        tree.preOrderNumber();
        tree.inOrderNumber();
        tree.postOrderNumber();

        System.out.println("Preorder numbers:");
        tree.printPreorderNumbers();
        System.out.println("Inorder numbers:");
        tree.printInorderNumbers();
        System.out.println("Postorder numbers:");
        tree.printPostorderNumbers();
    }

    /**
     * Utility method to print preorder numbers of nodes.
     */
    public void printPreorderNumbers() {
        printPreorderNumbers(root);
    }

    private void printPreorderNumbers(BinaryTreeNode node) {
        if (node != null) {
            System.out.print(((NumberedBinaryTreeNode) node).preorderNumber + " ");
            printPreorderNumbers(node.left);
            printPreorderNumbers(node.right);
        }
    }

    /**
     * Utility method to print inorder numbers of nodes.
     */
    public void printInorderNumbers() {
        printInorderNumbers(root);
    }

    private void printInorderNumbers(BinaryTreeNode node) {
        if (node != null) {
            printInorderNumbers(node.left);
            System.out.print(((NumberedBinaryTreeNode) node).inorderNumber + " ");
            printInorderNumbers(node.right);
        }
    }

    /**
     * Utility method to print postorder numbers of nodes.
     */
    public void printPostorderNumbers() {
        printPostorderNumbers(root);
    }

    private void printPostorderNumbers(BinaryTreeNode node) {
        if (node != null) {
            printPostorderNumbers(node.left);
            printPostorderNumbers(node.right);
            System.out.print(((NumberedBinaryTreeNode) node).postorderNumber + " ");
        }
    }
}
