package DAY_23_GRAPH;

import java.util.ArrayList;
import java.util.List;

public class Cycle_Undirected_Graph_DFS {

    public boolean dfs(int node, int parent, boolean[] visited, List<List<Integer>> adjList) {
        visited[node] = true;

        for (int neighbour : adjList.get(node)) {

            // Case 1: Neighbor is not visited → continue DFS
            if (!visited[neighbour]) {
                if (dfs(neighbour, node, visited, adjList)) {
                    return true;
                }
            }
            // Case 2: Neighbor is visited and is not the parent → cycle detected
            else if (neighbour != parent) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * Detects a cycle in an undirected graph using DFS.
     *
     * Time Complexity: O(V + E)
     * -------------------------
     * - Building the adjacency list takes O(V + E).
     * - Each vertex is visited once in DFS → O(V).
     * - Each edge is explored once from both ends → O(E).
     * - Therefore, total time complexity = O(V + E).
     *
     * Space Complexity: O(V)
     * ----------------------
     * - Visited array uses O(V) space.
     * - Adjacency list uses O(V + E) space (input-dependent).
     * - Recursive DFS call stack can go up to O(V) in the worst case.
     * - Excluding input storage, auxiliary space = O(V).
     */
    public boolean isCycle(int V, int[][] edges) {

        // Step 1: Create adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Step 2: Populate adjacency list (undirected graph)
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // Step 3: Visited array
        boolean[] visited = new boolean[V];

        // Step 4: DFS for each connected component
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, -1, visited, adjList)) {
                    return true;
                }
            }
        }

        return false;
    }
}
