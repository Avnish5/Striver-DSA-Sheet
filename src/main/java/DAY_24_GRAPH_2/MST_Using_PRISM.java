package DAY_24_GRAPH_2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MST_Using_PRISM {

    /**
     * Function to find the weight of the Minimum Spanning Tree (MST)
     * using Prim’s Algorithm (Greedy approach)
     *
     * TC: O(E log V)
     *   - Each edge can be inserted into the priority queue
     *   - Priority queue operations take log V time
     *
     * SC: O(V + E)
     *   - Adjacency list stores all vertices and edges
     *   - Priority queue can store up to E elements
     *   - inMST array stores V elements
     */
    public int spanningTree(int V, int[][] edges) {

        // Step 1: Create adjacency list representation of the graph
        // Each list contains {neighbor, weight}
        List<List<int[]>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Populate adjacency list (undirected graph)
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }

        // Step 3: Array to track whether a vertex is already included in MST
        boolean[] inMST = new boolean[V];

        // Step 4: Min-heap priority queue storing {weight, node}
        // Always picks the edge with minimum weight
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]
        );

        // Start from node 0 with weight 0
        pq.add(new int[]{0, 0});

        int sum = 0; // Total weight of MST

        // Step 5: Process nodes using Prim’s Algorithm
        while (!pq.isEmpty()) {

            int[] pair = pq.poll();
            int wt = pair[0];
            int node = pair[1];

            // If node is already included in MST, skip it
            if (inMST[node]) continue;

            // Include current node in MST
            inMST[node] = true;
            sum += wt;

            // Step 6: Add all adjacent edges of the current node
            for (int[] neighbour : adj.get(node)) {
                int nextNode = neighbour[0];
                int weight = neighbour[1];

                // Add edge only if the neighbor is not in MST
                if (!inMST[nextNode]) {
                    pq.add(new int[]{weight, nextNode});
                }
            }
        }

        // Step 7: Return total weight of the Minimum Spanning Tree
        return sum;
    }
}
