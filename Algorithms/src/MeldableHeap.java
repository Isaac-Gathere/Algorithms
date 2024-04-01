/** Implementing the remove(u) method, that removes the node u from a
        MeldableHeap. This method should run in O(log n) expected time
 *
 *
 *
**/
import java.util.Random;

public class MeldableHeap {
    private static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private Random random;

    public MeldableHeap() {
        this.root = null;
        this.random = new Random();
    }

    // Merge two heaps
    private Node merge(Node h1, Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        if (h1.key < h2.key) {
            h1.right = merge(h1.right, h2);
            return h1;
        } else {
            h2.right = merge(h2.right, h1);
            return h2;
        }
    }

    // Remove the minimum key node from the heap
    private Node removeMin(Node root) {
        return merge(root.left, root.right);
    }

    // Find and remove a specific node from the heap
    private Node remove(Node root, Node u) {
        if (root == null) return null;

        if (root == u) {
            return merge(root.left, root.right);
        } else if (u.key < root.key) {
            root.left = remove(root.left, u);
        } else {
            root.right = remove(root.right, u);
        }
        return root;
    }

    // Remove a node with key 'key' from the heap
    public void remove(int key) {
        Node u = find(root, key);
        if (u != null) {
            root = remove(root, u);
        }
    }

    // Find a node with key 'key' in the heap
    private Node find(Node root, int key) {
        if (root == null) return null;

        if (root.key == key) return root;

        Node foundInLeft = find(root.left, key);
        if (foundInLeft != null) return foundInLeft;

        return find(root.right, key);
    }

    // Insert a new key into the heap
    public void insert(int key) {
        Node newNode = new Node(key);
        root = merge(root, newNode);
    }

    // Get the minimum key from the heap
    public int findMin() {
        if (root == null) throw new IllegalStateException("Heap is empty");
        return root.key;
    }

    // Display the heap keys using in-order traversal
    public void inorder() {
        inorder(root);
    }

    private void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        MeldableHeap heap = new MeldableHeap();

        // Insert keys into the heap
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(2);
        heap.insert(8);
        heap.insert(6);

        System.out.println("Heap keys before removal:");
        heap.inorder();
        System.out.println();

        // Remove a node with key 3
        heap.remove(3);

        System.out.println("Heap keys after removing node with key 3:");
        heap.inorder();
    }
}
