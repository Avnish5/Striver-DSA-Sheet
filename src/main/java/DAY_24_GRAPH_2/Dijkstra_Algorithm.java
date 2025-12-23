package DAY_24_GRAPH_2;

import java.util.*;

public class Dijkstra_Algorithm {

    /**
     * Q: Why do we use a Set (TreeSet) instead of a PriorityQueue in Dijkstra’s Algorithm?
     *
     * A:
     * The main difference comes from how both data structures handle
     * "updating the distance of a node" (also called decrease-key).
     *
     * ------------------------------------------------------------
     * 1) Problem with PriorityQueue
     * ------------------------------------------------------------
     * In Java, PriorityQueue does NOT support decrease-key.
     *
     * What happens in Dijkstra with PriorityQueue:
     * - When a shorter distance to a node is found, we CANNOT update
     *   the existing entry inside the PriorityQueue.
     * - Instead, we INSERT a NEW entry with the updated distance.
     * - The old (larger distance) entry remains in the queue.
     *
     * Example:
     *   PQ contains: (10, node=2)
     *   Later we find a better path:
     *   Insert: (5, node=2)
     *
     *   PQ now contains:
     *   (5, node=2), (10, node=2)
     *
     * Because of this:
     * - Duplicate entries exist
     * - We must IGNORE outdated entries using a check like:
     *
     *     if (currDist > dist[node]) continue;
     *
     * PriorityQueue approach:
     * - Simpler
     * - Faster in practice
     * - But allows duplicate/outdated entries
     *
     * ------------------------------------------------------------
     * 2) Why TreeSet solves this problem
     * ------------------------------------------------------------
     * TreeSet maintains UNIQUE and SORTED elements.
     *
     * In TreeSet-based Dijkstra:
     * - Each node appears ONLY ONCE with its current best distance
     * - When a better distance is found:
     *     1) Remove the old (distance, node) pair from the set
     *     2) Insert the new (smaller distance, node) pair
     *
     * This SIMULATES the "decrease-key" operation.
     *
     * Example:
     *   Set contains: (10, node=2)
     *   Better path found:
     *   Remove: (10, node=2)
     *   Add   : (5, node=2)
     *
     * Set now contains ONLY:
     *   (5, node=2)
     *
     * TreeSet approach:
     * - No duplicate entries
     * - Cleaner logic (no outdated elements)
     * - Slightly slower due to remove + insert
     *
     * ------------------------------------------------------------
     * 3) Summary (Interview Answer)
     * ------------------------------------------------------------
     * PriorityQueue:
     * - Cannot decrease key
     * - Allows duplicate/outdated entries
     * - Needs extra checks
     * - More commonly used
     *
     * TreeSet:
     * - Supports remove + reinsert (decrease-key simulation)
     * - No duplicate entries
     * - Cleaner but slightly slower
     *
     * Both approaches are CORRECT and give:
     * Time Complexity  : O((V + E) log V)
     * Space Complexity: O(V + E)
     */


    /**
     * 1. Dijkstra's Algorithm using PriorityQueue (Min-Heap)
     *
     * Graph Type      : Undirected, Weighted
     * Edge Weights    : Non-negative
     * Input Format    : edges[i] = {u, v, w}
     * Output          : Shortest distance from src to all vertices
     *
     * Time Complexity : O((V + E) log V)
     * Space Complexity: O(V + E)
     */

    public int[] dijkstra_Using_Priority_Queue(int V, int[][] edges, int src) {

        // ---------- Step 1: Build Adjacency List ----------
        // adjList[u] contains pairs {v, w} meaning edge u -> v with weight w
        List<List<int[]>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Since graph is undirected, add edges in both directions
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            adjList.get(u).add(new int[]{v, w});
            adjList.get(v).add(new int[]{u, w});
        }

        // ---------- Step 2: Min-Heap (distance, node) ----------
        // PriorityQueue ensures node with smallest distance is processed first
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // ---------- Step 3: Distance Array ----------
        // dist[i] stores the shortest distance from src to node i
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Add source node to PQ with distance 0
        pq.add(new int[]{0, src});

        // ---------- Step 4: Dijkstra’s Algorithm ----------
        while (!pq.isEmpty()) {

            // Extract node with minimum distance
            int[] curr = pq.poll();
            int currDist = curr[0];
            int node = curr[1];

            if (currDist > dist[node]) continue;

            // Relax all adjacent edges
            for (int[] neighbour : adjList.get(node)) {
                int nextNode = neighbour[0];
                int weight = neighbour[1];

                // If a shorter path to nextNode is found
                if (dist[node] + weight < dist[nextNode]) {

                    // Update shortest distance
                    dist[nextNode] = dist[node] + weight;

                    // Push updated distance into PriorityQueue
                    pq.add(new int[]{dist[nextNode], nextNode});
                }
            }
        }

        // Final shortest distances from src to all vertices
        return dist;
    }

    static class Pair{
        int dist;
        int node;

        public Pair(int dist, int node) {
            this.dist = dist;
            this.node = node;
        }
    }

    /**
     * 2.Dijkstra's Algorithm using TreeSet (Decrease-Key Simulation)
     *
     * Graph Type      : Undirected, Weighted
     * Edge Weights    : Non-negative
     * Input Format    : edges[i] = {u, v, w}
     * Output          : Shortest distance from src to all vertices
     *
     * Time Complexity : O((V + E) log V)
     * Space Complexity: O(V + E)
     */

    public int[] dijkstra_Using_Set(int V, int[][] edges, int src) {

        // ---------- Step 1: Build Adjacency List ----------
        // adjList[u] stores {v, w} meaning an edge from u -> v with weight w
        List<List<int[]>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Since graph is undirected, add edges in both directions
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            adjList.get(u).add(new int[]{v, w});
            adjList.get(v).add(new int[]{u, w});
        }

        // ---------- Step 2: TreeSet (distance, node) ----------
        // TreeSet keeps elements sorted and allows remove + reinsert (decrease-key)
        TreeSet<Pair> set = new TreeSet<>((a, b) -> {
            if (a.dist != b.dist) return a.dist - b.dist; // sort by distance
            return a.node - b.node; // tie-breaker to avoid duplicates
        });

        // ---------- Step 3: Distance Array ----------
        // dist[i] holds shortest distance from src to i
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Insert source node
        set.add(new Pair(0, src));

        // ---------- Step 4: Dijkstra’s Algorithm ----------
        while (!set.isEmpty()) {

            // Extract node with minimum distance
            Pair curr = set.pollFirst();
            int currDist = curr.dist;
            int node = curr.node;

            // Relax all adjacent edges
            for (int[] neighbour : adjList.get(node)) {
                int nextNode = neighbour[0];
                int weight = neighbour[1];

                // If a shorter path to nextNode is found
                if (dist[node] + weight < dist[nextNode]) {

                    // Remove outdated entry from TreeSet (if exists)
                    if (dist[nextNode] != Integer.MAX_VALUE) {
                        set.remove(new Pair(dist[nextNode], nextNode));
                    }

                    // Update distance
                    dist[nextNode] = dist[node] + weight;

                    // Insert updated distance into TreeSet
                    set.add(new Pair(dist[nextNode], nextNode));
                }
            }
        }

        // Final shortest distances from src
        return dist;
    }


}
