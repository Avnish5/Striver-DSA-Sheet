package DAY_24_GRAPH_2;

import java.util.Arrays;

public class Bellman_Ford_Undirected {

        // Constant used to represent unreachable vertices
        static final int INF = 100000000; // 10^8

        /**
         * Bellman–Ford Algorithm for an Undirected Graph
         *
         * Note:
         * - Each undirected edge (u — v) is treated as two directed edges:
         *   u → v and v → u
         *
         * Time Complexity: O(V * E)
         * --------------------------------
         * - Outer loop runs V - 1 times
         * - Inner loop processes all E edges
         * - For undirected graph, each edge is relaxed in both directions
         *
         * Space Complexity: O(V)
         * --------------------------------
         * - Distance array of size V
         * - No extra data structures used
         */
        public int[] bellmanFord(int V, int[][] edges, int src) {

            // Step 1: Initialize distance array
            int[] dist = new int[V];
            Arrays.fill(dist, INF);

            // Distance from source to itself is 0
            dist[src] = 0;

            // Step 2: Relax all edges V - 1 times
            for (int i = 1; i <= V - 1; i++) {
                for (int[] edge : edges) {

                    int u = edge[0];
                    int v = edge[1];
                    int w = edge[2];

                    // Relax edge u -> v
                    if (dist[u] != INF && dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                    }

                    // Relax edge v -> u (because graph is undirected)
                    if (dist[v] != INF && dist[v] + w < dist[u]) {
                        dist[u] = dist[v] + w;
                    }
                }
            }

            // Step 3: Check for negative weight cycle
            for (int[] edge : edges) {

                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if ((dist[u] != INF && dist[u] + w < dist[v]) ||
                        (dist[v] != INF && dist[v] + w < dist[u])) {

                    // Negative cycle detected
                    return new int[]{-1};
                }
            }

            // Step 4: Return shortest distances from source
            return dist;
        }

}
