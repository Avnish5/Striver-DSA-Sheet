package DAY_23_GRAPH;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cycle_Directed_Graph_BFS {



    /**
     * Detects a cycle in a directed graph using BFS (Kahn's Algorithm).
     * Time Complexity:
     *  - Building adjacency list and indegree array: O(E)
     *  - BFS traversal (each vertex and edge processed once): O(V + E)
     *  - Overall TC: O(V + E)
     *
     * Space Complexity:
     *  - Adjacency list: O(V + E)
     *  - Indegree array: O(V)
     *  - Queue for BFS: O(V)
     *  - Overall SC: O(V + E)
     */
    public boolean isCyclic(int V, int[][] edges) {

        Queue<Integer> q = new LinkedList<>();
        int count = 0;                  // Number of vertices processed
        int[] indegree = new int[V];    // Stores indegree of each vertex

        // Step 1: Create adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Step 2: Build adjacency list and indegree array
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            indegree[v]++;
        }

        // Step 3: Add all vertices with indegree 0 to the queue
        // These vertices can be processed immediately
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                count++;               // Count vertices that are initially processable
            }
        }

        // Step 4: BFS traversal
        while (!q.isEmpty()) {
            int node = q.poll();

            // Reduce indegree of neighboring vertices
            for (int neighbour : adjList.get(node)) {
                indegree[neighbour]--;

                // If indegree becomes 0, add to queue
                if (indegree[neighbour] == 0) {
                    count++;
                    q.add(neighbour);
                }
            }
        }

        // Step 5: If all vertices are processed, no cycle exists
        // Otherwise, a cycle is present
        return count != V;
    }
}
