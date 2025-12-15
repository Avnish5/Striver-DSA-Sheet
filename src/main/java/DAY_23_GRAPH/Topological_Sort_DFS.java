package DAY_23_GRAPH;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Topological_Sort_DFS {

    public void dfs(int node, boolean[] visited,
                    List<List<Integer>> adjList, Stack<Integer> st) {

        // Mark the current node as visited
        visited[node] = true;

        // Visit all unvisited neighbors
        for (int neighbour : adjList.get(node)) {
            if (!visited[neighbour]) {
                dfs(neighbour, visited, adjList, st);
            }
        }

        // Push node to stack after all its neighbors are processed
        st.push(node);
    }

    /**
     *
     * Generates a topological ordering of a Directed Acyclic Graph (DAG)
     * using Depth First Search (DFS).
     *
     * Time Complexity: O(V + E)
     * -------------------------
     * - Each vertex is visited exactly once → O(V)
     * - Each directed edge is explored once → O(E)
     * - Therefore, total time complexity = O(V + E)
     *
     * Space Complexity: O(V)
     * ----------------------
     * - Visited array uses O(V) space
     * - Recursion stack can go up to O(V) in the worst case
     * - Stack used to store topological order takes O(V)
     * - Excluding input graph storage, auxiliary space = O(V)
     */
    public ArrayList<Integer> topoSort(int V, int[][] edges) {

        // Step 1: Build adjacency list for directed graph
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Step 2: Add directed edges
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
        }

        // Step 3: Initialize visited array and stack
        boolean[] visited = new boolean[V];
        Stack<Integer> st = new Stack<>();

        // Step 4: Perform DFS for all vertices
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, adjList, st);
            }
        }

        // Step 5: Pop stack to get topological order
        ArrayList<Integer> topoOrder = new ArrayList<>();
        while (!st.isEmpty()) {
            topoOrder.add(st.pop());
        }

        return topoOrder;
    }
}
