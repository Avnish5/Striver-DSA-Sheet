package DAY_23_GRAPH;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cycle_Undirected_Graph_BFS {

    /**
     * Detects a cycle in an undirected graph using BFS.
     *
     * Time Complexity: O(V + E)
     * -------------------------
     * - Building adjacency list takes O(V + E).
     * - Each vertex is visited once → O(V).
     * - Each edge is examined once from both ends → O(E).
     * - Total time complexity = O(V + E).
     *
     * Space Complexity: O(V)
     * ----------------------
     * - Visited array uses O(V).
     * - Queue can store up to O(V) elements in the worst case.
     * - Adjacency list space is input-dependent and excluded.
     * - Auxiliary space = O(V).
     */
    public boolean isCycle(int V, int[][] edges) {

        // Step 1: Build adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u); // undirected
        }

        // Step 2: Visited array
        boolean[] visited = new boolean[V];

        // Step 3: BFS for each connected component
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (bfsDetectCycle(i, visited, adjList)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean bfsDetectCycle(int start, boolean[] visited, List<List<Integer>> adjList) {

        // Queue stores {currentNode, parentNode}
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, -1});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int node = pair[0];
            int parent = pair[1];

            for (int neighbour : adjList.get(node)) {

                // If neighbor not visited, mark and push to queue
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(new int[]{neighbour, node});
                }
                // If visited and not parent → cycle
                else if (neighbour != parent) {
                    return true;
                }
            }
        }

        return false;
    }
}
