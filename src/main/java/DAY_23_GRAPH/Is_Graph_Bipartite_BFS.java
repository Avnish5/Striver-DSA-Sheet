package DAY_23_GRAPH;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Is_Graph_Bipartite_BFS {

    public boolean bfs(int[] colors, int curr, int currColor, int[][] graph) {

        // Assign color to the starting node
        colors[curr] = currColor;

        // Queue for BFS traversal
        Queue<Integer> q = new LinkedList<>();
        q.add(curr);

        while (!q.isEmpty()) {
            int node = q.poll();

            // Traverse all neighbors of the current node
            for (int neighbour : graph[node]) {

                // If neighbor has the same color → not bipartite
                if (colors[neighbour] == colors[node]) {
                    return false;
                }

                // If neighbor is uncolored, assign opposite color
                if (colors[neighbour] == -1) {
                    colors[neighbour] = 1 - colors[node];
                    q.add(neighbour);
                }
            }
        }

        return true;
    }

    /**
     *
     * Determines whether the given graph is bipartite using BFS.
     *
     * Time Complexity: O(V + E)
     * -------------------------
     * - Each vertex is enqueued and processed at most once → O(V).
     * - Each edge is examined once during BFS → O(E).
     * - Total time complexity = O(V + E).
     *
     * Space Complexity: O(V)
     * ----------------------
     * - Colors array uses O(V) space.
     * - Queue can store up to O(V) nodes in the worst case.
     * - No extra adjacency list is created.
     */
    public boolean isBipartite(int[][] graph) {

        int V = graph.length;

        // Initialize all nodes as uncolored
        int[] colors = new int[V];
        Arrays.fill(colors, -1);

        // Check each connected component
        for (int i = 0; i < V; i++) {
            if (colors[i] == -1) {
                if (!bfs(colors, i, 1, graph)) {
                    return false;
                }
            }
        }

        return true;
    }
}
