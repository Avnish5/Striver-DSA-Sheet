package DAY_23_GRAPH;

import java.util.ArrayList;
import java.util.List;

public class Cycle_Directed_Graph_DFS {


    public boolean dfs(boolean[] visited, boolean[] inRecursion,
                       int curr, List<List<Integer>> adjList) {

        // Mark current node as visited and add it to recursion stack
        visited[curr] = true;
        inRecursion[curr] = true;

        // Traverse all neighbors of current node
        for (int neighbour : adjList.get(curr)) {

            // If neighbor is not visited, perform DFS
            if (!visited[neighbour]) {
                if (dfs(visited, inRecursion, neighbour, adjList)) {
                    return true; // Cycle detected
                }
            }
            // If neighbor is already in recursion stack, cycle exists
            else if (inRecursion[neighbour]) {
                return true;
            }
        }

        // Remove current node from recursion stack (backtracking)
        inRecursion[curr] = false;
        return false; // No cycle found from this node
    }

    /**
     * Checks whether a directed graph contains a cycle.
     *
     * Time Complexity:
     *  - Building adjacency list: O(E)
     *  - DFS traversal: O(V + E)
     *  - Overall TC: O(V + E)
     *
     * Space Complexity:
     *  - Adjacency list: O(V + E)
     *  - visited[] and inRecursion[] arrays: O(V)
     *  - Recursive DFS stack: O(V)
     *  - Overall SC: O(V + E)
     */
    public boolean isCyclic(int V, int[][] edges) {

        boolean[] visited = new boolean[V];
        boolean[] inRecursion = new boolean[V];

        // Build adjacency list from edge list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Add directed edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
        }

        // Run DFS for all vertices (handles disconnected components)
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(visited, inRecursion, i, adjList)) {
                    return true; // Cycle detected
                }
            }
        }

        return false; // No cycle in graph
    }
}
