package DAY_26_DP_PART_2;

import java.util.Arrays;

public class Minimum_Path_Sum {
        private int dfs(int i, int j, int[][] grid) {

            int m = grid.length;
            int n = grid[0].length;

            // Out of bounds → invalid path
            if (i >= m || j >= n) {
                return Integer.MAX_VALUE;
            }

            // Destination reached
            if (i == m - 1 && j == n - 1) {
                return grid[i][j];
            }

            // Move right
            int right = dfs(i, j + 1, grid);

            // Move down
            int down = dfs(i + 1, j, grid);

            // Current cell value + minimum of both paths
            return grid[i][j] + Math.min(right, down);
        }

    /**
     * 1. Recursion
     *
     * Time Complexity (TC):
     * ---------------------
     * O(2^(m+n))
     *
     * Explanation:
     * - From each cell, we have 2 choices (right or down)
     * - Exponential number of paths
     *
     * Space Complexity (SC):
     * ----------------------
     * O(m + n)
     *
     * Explanation:
     * - Maximum recursion depth is m+n
     */
        public int minPathSum1(int[][] grid) {
            return dfs(0, 0, grid);
        }

    /**
     * 2. Memoization
     *
     * Time Complexity (TC):
     * ---------------------
     * O(m × n)
     *
     * Explanation:
     * - Each cell (i, j) is computed only once
     * - Memoization avoids recomputation of overlapping subproblems
     *
     * Space Complexity (SC):
     * ----------------------
     * O(m × n) + O(m + n)
     *
     * Explanation:
     * - O(m × n) for memoization table
     * - O(m + n) for recursion stack depth
     *
     * Where:
     * m = number of rows
     * n = number of columns
     */
    private int dfs(int i, int j, int m, int n, int[][] grid, int[][] memo) {

        // If we go out of bounds, return a very large value
        // so this path is never chosen as minimum
        if (i >= m || j >= n) {
            return Integer.MAX_VALUE;
        }

        // If destination cell is reached,
        // return its value as path sum
        if (i == m - 1 && j == n - 1) {
            return grid[i][j];
        }

        // If this subproblem is already solved,
        // return the stored result
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // Move down
        int down = dfs(i + 1, j, m, n, grid, memo);

        // Move right
        int right = dfs(i, j + 1, m, n, grid, memo);

        // Store and return minimum path sum from current cell
        return memo[i][j] = grid[i][j] + Math.min(down, right);
    }

    public int minPathSum2(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Memo table where memo[i][j] stores
        // minimum path sum from (i, j) to destination
        int[][] memo = new int[m][n];

        // Initialize memo table with -1 (uncomputed state)
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        // Start DFS from top-left corner
        return dfs(0, 0, m, n, grid, memo);
    }


    /**
     * 3. Bottom-Up
     *
     * Time Complexity (TC):
     * ---------------------
     * O(m × n)
     *
     * Explanation:
     * - Each cell of the m × n grid is processed exactly once
     *
     * Space Complexity (SC):
     * ----------------------
     * O(m × n)
     *
     * Explanation:
     * - A DP table of size m × n is used to store minimum path sums
     *
     * Where:
     * m = number of rows
     * n = number of columns
     */
    public int minPathSum3(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j] represents the minimum path sum
        // to reach cell (i, j) from the top-left corner
        int[][] dp = new int[m][n];

        // Base Case:
        // Starting cell has the same value as grid[0][0]
        dp[0][0] = grid[0][0];

        // Fill the first column
        // Only downward movement is possible
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        // Fill the first row
        // Only rightward movement is possible
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }

        // Fill the rest of the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                // Minimum path sum to current cell
                // comes from either top or left
                dp[i][j] = grid[i][j] +
                        Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // Bottom-right cell contains the final answer
        return dp[m - 1][n - 1];
    }




}
