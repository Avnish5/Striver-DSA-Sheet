package DAY_25_DP;

import java.util.Arrays;

public class Matrix_Chain_Multiplication {
    /**
     *1. Recursion
     *
     * Time Complexity (TC):
     * ---------------------
     * Exponential: O(2^n)
     *
     * Reason:
     * - Each call tries all possible split points (k)
     * - Same subproblems are recomputed many times
     * - This is the pure recursive (brute-force) version
     *
     * Space Complexity (SC):
     * ----------------------
     * O(n)
     *
     * Reason:
     * - Maximum recursion depth is equal to the number of matrices
     * - No extra data structures used
     */
    public int matrixChainOrder(int[] arr, int i, int j) {

        // Base case:
        // If there is only one matrix (i == j),
        // no multiplication is required, so cost is 0
        if (i == j) return 0;

        // Initialize the minimum cost to a very large value
        // This will be updated with the minimum possible cost
        int minCost = Integer.MAX_VALUE;

        // Try all possible places to split the matrix chain
        // k represents the position where we split:
        // (Mi ... Mk) x (Mk+1 ... Mj)
        for (int k = i; k < j; k++) {

            // Cost of multiplying matrices from Mi to Mk
            int cost1 = matrixChainOrder(arr, i, k);

            // Cost of multiplying matrices from Mk+1 to Mj
            int cost2 = matrixChainOrder(arr, k + 1, j);

            // Cost of multiplying the two resulting matrices:
            // Dimensions:
            // Left result  = arr[i-1] x arr[k]
            // Right result = arr[k]   x arr[j]
            // Multiplication cost = arr[i-1] * arr[k] * arr[j]
            int costMultiply = arr[i - 1] * arr[k] * arr[j];

            // Total cost for this particular split
            int total = cost1 + cost2 + costMultiply;

            // Update minimum cost among all possible splits
            minCost = Math.min(minCost, total);
        }

        // Return the minimum cost to multiply matrices from i to j
        return minCost;
    }

    public int matrixMultiplication1(int arr[]) {

        int n = arr.length;


        // Solve for full matrix chain from M1 to M(n-1)
        return matrixChainOrder(arr, 1, n - 1);
    }

    /**
     * Time Complexity (TC):
     * ---------------------
     * O(n^3)
     *
     * Explanation:
     * - There are O(n^2) unique subproblems (i, j) where i <= j
     * - Each subproblem tries all possible split points k (O(n))
     * - Total = O(n^2 * n) = O(n^3)
     *
     * Space Complexity (SC):
     * ----------------------
     * O(n^2) + O(n)
     *
     * Explanation:
     * - O(n^2) for memoization table
     * - O(n) for recursion stack (maximum depth)
     */
    public int helper(int arr[], int i, int j, int[][] memo) {

        // Base case:
        // If there is only one matrix, no multiplication is required
        if (i == j) return 0;

        // If the result for this subproblem is already computed,
        // return it to avoid recomputation
        if (memo[i][j] != -1) return memo[i][j];

        // Initialize answer with a very large value
        int ans = Integer.MAX_VALUE;

        // Try all possible places to split the matrix chain
        // k represents the split point:
        // (Mi ... Mk) × (Mk+1 ... Mj)
        for (int k = i; k < j; k++) {

            // Cost to compute left subchain (Mi to Mk)
            int costLeft = helper(arr, i, k, memo);

            // Cost to compute right subchain (Mk+1 to Mj)
            int costRight = helper(arr, k + 1, j, memo);

            // Cost to multiply the two resulting matrices
            // Left result dimension  = arr[i-1] × arr[k]
            // Right result dimension = arr[k] × arr[j]
            int costMultiply = arr[i - 1] * arr[k] * arr[j];

            // Total cost for this split
            int totalCost = costLeft + costRight + costMultiply;

            // Update minimum cost
            ans = Math.min(ans, totalCost);
        }

        // Store result in memo table and return
        return memo[i][j] = ans;
    }

    public int matrixMultiplication2(int arr[]) {

        int n = arr.length;

        // Memoization table to store results of subproblems
        int[][] memo = new int[n + 1][n + 1];

        // Initialize memo table with -1 (indicates unsolved subproblem)
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        // Solve for full matrix chain from M1 to M(n-1)
        return helper(arr, 1, n - 1, memo);
    }


}
