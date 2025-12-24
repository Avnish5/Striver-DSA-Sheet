package DAY_24_GRAPH_2;

import java.util.Arrays;

public class Bellman_Ford_Directed {

        // Constant value used to mark unreachable vertices
        static final int INF = 100000000;

        /**
         * Bellman–Ford Algorithm to find shortest paths from a single source
         * in a graph that may contain negative weight edges.
         *
         * If a negative cycle is reachable from the source,
         * the function returns an array containing only {-1}.
         *
         * Time Complexity: O(V * E)
         * --------------------------------
         * - Outer loop runs V - 1 times
         * - Inner loop processes all E edges
         *
         * Space Complexity: O(V)
         * --------------------------------
         * - Distance array of size V
         * - No additional data structures used
         */
        public int[] bellmanFord(int V, int[][] edges, int src) {

            // Step 1: Initialize distance array
            // Set all distances to INF initially
            int[] result = new int[V];
            Arrays.fill(result, INF);

            // Distance from source to itself is always 0
            result[src] = 0;

            // Step 2: Relax all edges V - 1 times
            // This ensures shortest distances propagate across the graph
            for (int count = 1; count <= V - 1; count++) {
                for (int[] e : edges) {
                    int u = e[0];   // source vertex of edge
                    int v = e[1];   // destination vertex of edge
                    int w = e[2];   // weight of edge

                    // Relax the edge if a shorter path is found
                    if (result[u] != INF && result[u] + w < result[v]) {
                        result[v] = result[u] + w;
                    }
                }
            }

            // Step 3: Check for negative weight cycles
            // If we can still relax an edge, a negative cycle exists
            for (int[] e : edges) {
                int u = e[0];
                int v = e[1];
                int w = e[2];

                if (result[u] != INF && result[u] + w < result[v]) {
                    // Negative cycle detected
                    return new int[]{-1};
                }
            }

            // Step 4: Return shortest distances from source
            return result;
        }


}
