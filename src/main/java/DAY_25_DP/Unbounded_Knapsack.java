package DAY_25_DP;

import java.util.Arrays;

public class Unbounded_Knapsack {

    /**
     * 1. Recursion - Unbounded Knapsack
     *
     * In Unbounded Knapsack, we are allowed to take an item multiple times.
     * So when we "take" an item, we do NOT move to the previous index.
     * Instead, we stay on the same index because the item can be reused.
     *
     * Time Complexity (TC):
     *   O(2^n)
     *   Each item has two choices: take or not take, leading to an exponential recursion tree.
     *
     * Space Complexity (SC):
     *   O(n)
     *   Due to recursion stack depth in the worst case.
     */

    public int helper(int val[], int wt[], int capacity, int n) {

        // Base Case:
        // If only the first item is left, we can take it multiple times
        // to fill the remaining capacity.
        if(n == 0) {
            return (capacity / wt[0]) * val[0];
        }

        // Option 1: Do not take the current item
        int notTake = helper(val, wt, capacity, n-1);

        // Option 2: Take the current item (if weight allows)
        int take = Integer.MIN_VALUE;

        if(wt[n] <= capacity) {
            // Since this is unbounded knapsack,
            // we stay at the same index after taking the item
            take = val[n] + helper(val, wt, capacity - wt[n], n);
        }

        // Return the maximum value obtained
        return Math.max(notTake, take);
    }

    public int knapSack1(int val[], int wt[], int capacity) {

        // Start recursion from the last item
        return helper(val, wt, capacity, val.length - 1);
    }

    /**
     * 2. Memoization - Unbounded Knapsack (Top-Down DP)
     *
     * In Unbounded Knapsack, we can take an item multiple times.
     * Therefore, when we "take" an item, we stay at the same index
     * instead of moving to the previous item.
     *
     * State Definition:
     *   dp[n][capacity] = Maximum value obtainable using items from 0..n
     *                     with remaining knapsack capacity = capacity.
     *
     * Time Complexity (TC):
     *   O(n * capacity)
     *   Each state (n, capacity) is computed once and stored in dp.
     *
     * Space Complexity (SC):
     *   O(n * capacity)  → DP table
     *   O(n)             → Recursion stack depth
     */

    public int helper(int val[], int wt[], int capacity, int n, int[][] dp) {

        // Base Case:
        // If only item 0 is available, we can take it multiple times
        // to fill the remaining capacity.
        if(n == 0) {
            return (capacity / wt[0]) * val[0];
        }

        // If already computed, return stored result
        if(dp[n][capacity] != -1) {
            return dp[n][capacity];
        }

        // Option 1: Do not take the current item
        int notTake = helper(val, wt, capacity, n-1, dp);

        // Option 2: Take the current item (if it fits)
        int take = Integer.MIN_VALUE;

        if(wt[n] <= capacity) {
            // Stay on the same item index because items are unlimited
            take = val[n] + helper(val, wt, capacity - wt[n], n, dp);
        }

        // Store and return the best result
        return dp[n][capacity] = Math.max(notTake, take);
    }

    public int knapSack2(int val[], int wt[], int capacity) {

        // DP table for memoization
        int[][] dp = new int[val.length + 1][capacity + 1];

        // Initialize DP table with -1 (means not computed yet)
        for(int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        // Start recursion from the last item
        return helper(val, wt, capacity, val.length - 1, dp);
    }
}
