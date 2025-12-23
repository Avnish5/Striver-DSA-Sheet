package DAY_24_GRAPH_2;

import java.util.Arrays;

public class MST_Using_KRUSKAL {

    /*
     * Kruskal’s Algorithm to find the weight of Minimum Spanning Tree (MST)
     *
     * TC: O(E log E)
     *   - Sorting edges takes O(E log E)
     *   - DSU operations are nearly O(1) (O(α(V)))
     *
     * SC: O(V)
     *   - Parent and rank arrays for DSU
     */
    public int spanningTree(int V, int[][] edges) {

        // Step 1: Sort all edges by weight (ascending order)
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        // Step 2: Initialize Disjoint Set Union (DSU)
        int[] parent = new int[V];
        int[] rank = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;   // each node is its own parent initially
            rank[i] = 0;
        }

        int mstWeight = 0;
        int edgesUsed = 0;

        // Step 3: Process edges in increasing order of weight
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            int pu = find(parent, u);
            int pv = find(parent, v);

            // If u and v are in different components, include this edge
            if (pu != pv) {
                mstWeight += wt;
                union(parent, rank, pu, pv);
                edgesUsed++;

                // MST will have exactly V - 1 edges
                if (edgesUsed == V - 1) break;
            }
        }

        return mstWeight;
    }

    // Find with path compression
    private int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }

    // Union by rank
    private void union(int[] parent, int[] rank, int x, int y) {

        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else if (rank[x] < rank[y]) {
            parent[x] = y;
        } else {
            parent[x] = y;
            rank[y]++;
        }
    }
}
