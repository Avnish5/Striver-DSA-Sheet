package DAY_3_Arrays_Part_3;

import java.util.Arrays;

public class Unique_Paths {


    /**
     * 1. Recursive Solution
     *
     * Time Complexity:  O(2^(m + n))
     *    - Because at each cell, we branch into two recursive calls (right and down)
     *    - This results in an exponential number of calls in worst case.
     *
     * Space Complexity: O(m + n)
     *    - Due to the maximum depth of the recursive call stack
     *    - In the worst case, you move (m - 1) down and (n - 1) right
     */
    private int countPaths(int i, int j, int m, int n) {
        if (i == m - 1 && j == n - 1) return 1;      // Reached destination
        if (i >= m || j >= n) return 0;             // Out of bounds

        // Move down (↓) and right (→)
        return countPaths(i + 1, j, m, n)           // Move down
                + countPaths(i, j + 1, m, n);          // Move right
    }


    /**
     * 2. Top-Down Dynamic Programming (Memoization)
     *
     * Time Complexity:  O(m * n)
     *    - Each unique cell (i, j) is computed only once
     *    - There are m * n such unique subproblems
     *
     * Space Complexity: O(m * n)
     *    - For the memoization table (dp array)
     *    - Plus O(m + n) for the recursion call stack (maximum depth)
     */
    private int countPathsDP(int i, int j, int m, int n, int[][] dp) {
        if(i==m-1 && j == n-1) return 1;
        if (i >=m || j>=n) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        return dp[i][j] =  countPathsDP(i+1,j,m,n, dp) + countPathsDP(i,j+1,m,n, dp);
    }

    /**
     * 3. Bottom-Up Dynamic Programming (Tabulation or iterative)
     *
     * Time Complexity:  O(m * n)
     *    - We fill an m x n table where each cell is computed once
     *    - Each cell computation is O(1), so total is m * n
     *
     * Space Complexity: O(m * n)
     *    - For the 2D DP table (arr) used to store intermediate results
     */

    private int uniquePathsBottomUp(int m, int n) {
        int[][] arr = new int[m][n];
        arr[0][0] = 1;

        for (int col = 1; col < n; col++) {
            arr[0][col] = 1;
        }

        for (int row = 1; row < m; row++) {
            arr[row][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = arr[i-1][j] + arr[i][j-1];
            }
        }

        return arr[m-1][n-1];


    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return countPathsDP(0, 0, m, n, dp);
    }
}
