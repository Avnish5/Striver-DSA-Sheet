package DAY_23_GRAPH;

import java.util.LinkedList;
import java.util.Queue;

public class Number_of_Islands_BFS {

        // Directions: down, up, right, left
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


        public void bfs(int i, int j, int m, int n, char[][] grid) {

            Queue<int[]> q = new LinkedList<>();

            // Add starting cell and mark as visited
            q.add(new int[]{i, j});
            grid[i][j] = 'v';

            while (!q.isEmpty()) {
                int[] front = q.poll();
                int x = front[0];
                int y = front[1];

                // Explore all 4 directions
                for (int[] dir : directions) {
                    int a = x + dir[0];
                    int b = y + dir[1];

                    // Skip invalid cells or non-land cells
                    if (a < 0 || a >= m || b < 0 || b >= n || grid[a][b] != '1') {
                        continue;
                    }

                    // Mark neighbor as visited and add to queue
                    grid[a][b] = 'v';
                    q.add(new int[]{a, b});
                }
            }
        }

        /**
         * Counts number of islands using BFS
         *
         * Time Complexity: O(M × N)
         * - Each cell is visited at most once
         *
         * Space Complexity: O(M × N)
         * - Queue can store all cells in worst case (all land)
         */
        public int numIslands(char[][] grid) {

            int m = grid.length;
            int n = grid[0].length;
            int ans = 0;

            // Traverse entire grid
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    // Start BFS when an unvisited land cell is found
                    if (grid[i][j] == '1') {
                        bfs(i, j, m, n, grid);
                        ans++; // One island completed
                    }
                }
            }
            return ans;
        }
}
