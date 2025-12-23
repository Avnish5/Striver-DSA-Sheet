package DAY_24_GRAPH_2;

import java.util.ArrayList;
import java.util.Stack;

public class Strongly_Connected_Algorithm {

        private void dfsFill(int curr, Stack<Integer> st, boolean[] visited,
                             ArrayList<ArrayList<Integer>> adj) {

            visited[curr] = true;

            for (int neighbour : adj.get(curr)) {
                if (!visited[neighbour]) {
                    dfsFill(neighbour, st, visited, adj);
                }
            }

            // Push after exploring all neighbors (finish time)
            st.push(curr);
        }

        private void dfs(int curr, boolean[] visited,
                         ArrayList<ArrayList<Integer>> adjRev) {

            visited[curr] = true;

            for (int neighbour : adjRev.get(curr)) {
                if (!visited[neighbour]) {
                    dfs(neighbour, visited, adjRev);
                }
            }
        }

        /*
         * kosaraju()
         * Purpose: Find number of Strongly Connected Components (SCCs)
         *          using Kosaraju's Algorithm.
         *
         * Overall Time Complexity: O(V + E)
         * Overall Space Complexity: O(V + E)
         *   - Adjacency list: O(V + E)
         *   - Reversed graph: O(V + E)
         *   - Stack + visited array: O(V)
         */
        public int kosaraju(ArrayList<ArrayList<Integer>> adj) {

            int V = adj.size();
            Stack<Integer> st = new Stack<>();
            boolean[] visited = new boolean[V];

            // Step 1: DFS to fill stack by finish time
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    dfsFill(i, st, visited, adj);
                }
            }

            // Step 2: Reverse the graph
            ArrayList<ArrayList<Integer>> adjRev = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adjRev.add(new ArrayList<>());
            }

            for (int i = 0; i < V; i++) {
                for (int neighbour : adj.get(i)) {
                    adjRev.get(neighbour).add(i);
                }
            }

            // Step 3: Process nodes in stack order
            visited = new boolean[V];
            int scc = 0;

            while (!st.isEmpty()) {
                int node = st.pop();

                if (!visited[node]) {
                    dfs(node, visited, adjRev);
                    scc++; // One DFS = One SCC
                }
            }

            return scc;
        }

}
