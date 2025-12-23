package DAY_23_GRAPH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Is_Graph_Bipartite_DFS {

    /**
     * Checks whether a given graph is bipartite using Depth First Search (DFS).
     * Time Complexity: O(V + E)
     * -------------------------
     * - Each vertex is visited at most once → O(V).
     * - Each edge is examined once during DFS → O(E).
     * - Therefore, total time complexity = O(V + E).
     *
     * Space Complexity: O(V)
     * ----------------------
     * - Color array uses O(V) space.
     * - Recursion stack can go up to O(V) in the worst case.
     * - No extra adjacency list is created.

     */
    public boolean isBipartite(int[][] graph) {

        int n = graph.length;

        // color[i] = -1 → uncolored
        // color[i] = 0 or 1 → assigned color
        int[] color = new int[n];
        Arrays.fill(color, -1);

        // Graph can be disconnected, so check each component
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!dfs(i, 0, color, graph)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int node, int currColor,
                        int[] color, int[][] graph) {

        // Assign color to current node
        color[node] = currColor;

        // Traverse all neighbors
        for (int neighbor : graph[node]) {

            // If neighbor is uncolored, assign opposite color
            if (color[neighbor] == -1) {
                if (!dfs(neighbor, 1 - currColor, color, graph)) {
                    return false;
                }
            }
            // If neighbor already has same color, graph is not bipartite
            else if (color[neighbor] == currColor) {
                return false;
            }
        }

        return true;
    }

    public int maxTwoEvents(int[][] events) {
          int n = events.length;
          int max = 0;

        Arrays.sort(events, (a, b) -> a[0] - b[0]);

          for(int i = 0; i < n; i++) {
              int[] e1 = events[i];
              int sum = 0;
              sum += e1[2];

              for(int j = i + 1; j < n; j++) {
                  int[] e2 = events[j];
                  if(e2[0] > e1[1] && e1[1] != e2[1]) {
                      sum += e2[2];
                      max =Math.max(sum, max);
                      sum = e1[1];
                  }
              }

              max =Math.max(sum, max);

          }

          return max;
    }

    public static void main(String[] args) {
        Is_Graph_Bipartite_DFS d = new Is_Graph_Bipartite_DFS();
        int[][] events = {
                {1, 3, 2},
                {4, 5, 2},
                {2, 4, 3}
        };

        System.out.println(d.maxTwoEvents(events));

    }
}
