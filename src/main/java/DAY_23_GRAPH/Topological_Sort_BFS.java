package DAY_23_GRAPH;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Topological_Sort_BFS {
    /**
     * Generates a topological ordering of a Directed Acyclic Graph (DAG)
     * using BFS (Kahn’s Algorithm).
     *
     * Time Complexity: O(V + E)
     * -------------------------
     * - Building adjacency list and indegree array takes O(V + E).
     * - Each vertex is added to the queue once → O(V).
     * - Each edge is processed once → O(E).
     * - Total time complexity = O(V + E).
     *
     * Space Complexity: O(V)
     * ----------------------
     * - Indegree array uses O(V).
     * - Queue can store up to O(V) vertices.
     * - Result list uses O(V).
     * - Excluding input graph storage, auxiliary space = O(V).
     */
    public ArrayList<Integer> topoSort(int V, int[][] edges) {

        // Step 1: Build adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Step 2: Indegree array
        int[] indegree = new int[V];

        // Step 3: Populate graph and indegree
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            indegree[v]++;
        }

        // Step 4: Initialize queue with nodes having indegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 5: BFS traversal
        ArrayList<Integer> topoOrder = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder.add(node);

            // Reduce indegree of neighbors
            for (int neighbour : adjList.get(node)) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

        return topoOrder;
    }

}
