/**
 *Given the graph shown below, answer the following questions:
 * a. Illustrating the sequence of vertices of this graph visited using depth-first search traversal
 * starting at vertex g.
 * b. Illustrating the sequence of vertices of this graph visited using breadth-first search
 * traversal starting at vertex b.
 * c.Illustrate adjacency list representation and adjacency matrix representation,
 * respectively, for this graph. What are the advantages and disadvantages of those two
 * representations?
 * d. Describe an algorithm to find in the graph a path illustrated below that goes through
 * every edge exactly once in each direction
 *
 *
 * **/
import java.util.*;

public class GraphTraversal13AtoD {
    // Define the graph as an adjacency list
    static Map<Character, List<Character>> graph = new HashMap<>();

    // Function to add edges to the graph
    static void addEdge(char src, char dest) {
        graph.computeIfAbsent(src, k -> new ArrayList<>()).add(dest);
    }

    // Depth-First Search traversal
    static void DFS(char start, Set<Character> visited) {
        visited.add(start);
        System.out.print(start + " ");

        List<Character> neighbors = graph.get(start);
        if (neighbors != null) {
            for (char neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    DFS(neighbor, visited);
                }
            }
        }
    }

    // Breadth-First Search traversal
    static void BFS(char start) {
        Set<Character> visited = new HashSet<>();
        Queue<Character> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            char current = queue.poll();
            System.out.print(current + " ");

            List<Character> neighbors = graph.get(current);
            if (neighbors != null) {
                for (char neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    // Algorithm to find a path that goes through every edge exactly once in each direction
    /**
     * To find a path that goes through every edge exactly once in each direction,
     * we can use an Eulerian Path algorithm. An Eulerian Path is a path in a graph
     * that visits every edge exactly once.
     *
     * Algorithm:
     * 1. Check if the graph has an Eulerian Path. For a directed graph, it must satisfy
     *    the condition that at most one vertex has out-degree - in-degree = 1, and at
     *    most one vertex has in-degree - out-degree = 1. All other vertices must have
     *    equal in-degree and out-degree.
     * 2. If the graph satisfies the condition, find the path using DFS or BFS traversal.
     *    During traversal, mark visited edges to ensure each edge is traversed exactly once
     *    in each direction.
     * 3. Construct the path based on the traversal results.
     * 4. Return the Eulerian Path.
     */

    public static void main(String[] args) {
        // Add edges to the graph
        addEdge('a', 'b');
        addEdge('a', 'e');
        addEdge('a', 'f');
        addEdge('b', 'c');
        addEdge('f', 'c');
        addEdge('c', 'd');
        addEdge('d', 'g');
        addEdge('e', 'i');
        addEdge('f', 'j');
        addEdge('i', 'm');
        addEdge('i', 'j');
        addEdge('i', 'n');
        addEdge('j', 'g');
        addEdge('g', 'k');
        addEdge('h', 'o');
        addEdge('i', 'm');
        addEdge('k', 'o');
        addEdge('n', 'o');
        addEdge('o', 'p');
        addEdge('p', 'l');

        // Start DFS traversal from vertex 'g'
        System.out.println("DFS Traversal starting at vertex g:");
        Set<Character> visitedDFS = new HashSet<>();
        DFS('g', visitedDFS);

        // Start BFS traversal from vertex 'b'
        System.out.println("\nBFS Traversal starting at vertex b:");
        BFS('b');

        // Print adjacency list representation
        System.out.println("\n\nAdjacency List Representation:");
        for (Map.Entry<Character, List<Character>> entry : graph.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            System.out.println(entry.getValue());
        }

        // Print adjacency matrix representation
        System.out.println("\nAdjacency Matrix Representation:");
        int[][] adjacencyMatrix = new int[26][26]; // Assuming vertices are labeled from 'a' to 'z'
        for (Map.Entry<Character, List<Character>> entry : graph.entrySet()) {
            char src = entry.getKey();
            List<Character> neighbors = entry.getValue();
            for (char dest : neighbors) {
                adjacencyMatrix[src - 'a'][dest - 'a'] = 1;
            }
        }
        for (int[] row : adjacencyMatrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
