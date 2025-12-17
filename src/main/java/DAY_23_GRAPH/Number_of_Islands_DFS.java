package DAY_23_GRAPH;

public class Number_of_Islands_DFS {


        public void dfs(int i, int j, int m, int n, char[][] grid) {

            // Boundary check + water check + visited check
            if (i < 0 || i >= m || j < 0 || j >= n ||
                    grid[i][j] == '0' || grid[i][j] == 'v') {
                return;
            }

            // Mark current land cell as visited
            grid[i][j] = 'v';

            // Explore all 4 directions
            dfs(i + 1, j, m, n, grid); // down
            dfs(i - 1, j, m, n, grid); // up
            dfs(i, j + 1, m, n, grid); // right
            dfs(i, j - 1, m, n, grid); // left
        }

        /**
         * Counts number of islands in the grid
         *
         * An island is formed by connecting adjacent lands horizontally or vertically.
         *
         * Time Complexity: O(M × N)
         * - Each cell is visited at most once
         *
         * Space Complexity: O(M × N)
         * - Worst-case recursion stack when grid is all land
         * - No extra data structures used
         */
        public int numIslands(char[][] grid) {

            int m = grid.length;
            int n = grid[0].length;

            int ans = 0;

            // Traverse entire grid
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    // Start DFS when an unvisited land is found
                    if (grid[i][j] == '1') {
                        dfs(i, j, m, n, grid);
                        ans++; // One complete island found
                    }
                }
            }

            return ans;
        }
}
