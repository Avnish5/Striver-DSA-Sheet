package DAY_24_GRAPH_2;

public class Floyd_Warshall_With_Cycle_Detect {

        /**
         * Floyd–Warshall Algorithm with Negative Cycle Detection
         *
         * The graph is represented using an adjacency matrix `dist`
         * where:
         * - dist[i][j] = weight of edge from i to j
         * - dist[i][j] = 10^8 if no direct edge exists
         *
         * Time Complexity: O(n^3)
         * --------------------------------
         * - Three nested loops over all vertices
         *
         * Space Complexity: O(1)
         * --------------------------------
         * - In-place modification of dist matrix
         */
        public void floydWarshall(int[][] dist) {

            int n = dist.length;
            int INF = 100000000; // 10^8 represents infinity

            // Step 1: Run Floyd–Warshall relaxation
            for (int via = 0; via < n; via++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {

                        // Relax path i -> k -> j if possible
                        if (dist[i][via] != INF && dist[via][j] != INF) {
                            dist[i][j] = Math.min(
                                    dist[i][j],
                                    dist[i][via] + dist[via][j]
                            );
                        }
                    }
                }
            }

            // Step 2: Detect negative cycle
            // If dist[i][i] < 0, then a negative cycle exists
            for (int i = 0; i < n; i++) {
                if (dist[i][i] < 0) {
                    // Negative cycle detected
                    System.out.println("Negative Cycle Exists");
                    return;
                }
            }

            System.out.println("No Negative Cycle");
        }

}
