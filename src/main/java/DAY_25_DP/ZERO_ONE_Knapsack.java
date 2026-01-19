package DAY_25_DP;

import java.util.Arrays;

public class ZERO_ONE_Knapsack {

    public int knapSackRec(int capacity, int[] wt, int[] val, int n) {

        // Base case: no items or no capacity
        if (n == 0 || capacity == 0) {
            return 0;
        }

        // If weight of current item is more than capacity, skip it
        if (wt[n - 1] > capacity) {
            return knapSackRec(capacity, wt, val, n - 1);
        }

        // Choice: take or not take the item
        int take = val[n - 1] + knapSackRec(capacity - wt[n - 1], wt, val, n - 1);
        int notTake = knapSackRec(capacity, wt, val, n - 1);

        return Math.max(take, notTake);
    }

    /**
     * 1.Recursive 0-1 Knapsack
     *
     * Time Complexity (TC):
     *   O(2^n)  → each item has 2 choices (take / not take)
     *
     * Space Complexity (SC):
     *   O(n) → recursion stack
     */
    public int knapsack1(int W, int val[], int wt[]) {
        // code here
        return knapSackRec(W, val, wt, val.length);
    }

    public int knapSackMemo(int capacity, int[] wt, int[] val, int n, int[][] dp) {

        // Base case
        if (n == 0 || capacity == 0) {
            return 0;
        }

        // If already solved, return stored value
        if (dp[n][capacity] != -1) {
            return dp[n][capacity];
        }

        // If item cannot be taken
        if (wt[n - 1] > capacity) {
            return dp[n][capacity] =
                    knapSackMemo(capacity, wt, val, n - 1, dp);
        }

        // Take or not take
        int take = val[n - 1]
                + knapSackMemo(capacity - wt[n - 1], wt, val, n - 1, dp);

        int notTake =
                knapSackMemo(capacity, wt, val, n - 1, dp);

        return dp[n][capacity] = Math.max(take, notTake);
    }

    /**
     * Memoized 0-1 Knapsack (Top-Down DP)
     *
     * Time Complexity (TC):
     *   O(n * capacity)
     *
     * Space Complexity (SC):
     *   O(n * capacity) → DP table
     *   O(n) → recursion stack
     */
    public int knapsack2(int W, int val[], int wt[]) {
        int[][] dp = new int[val.length + 1][W+1];

        for(int i = 0; i < val.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return knapSackMemo(W, val, wt, val.length, dp);
    }

    /**
     * 3. Tabulation 0-1 Knapsack (Bottom-Up DP)
     *
     * Time Complexity (TC):
     *   O(n * capacity)
     *
     * Space Complexity (SC):
     *   O(n * capacity)
     */
    public int knapSack(int capacity, int[] wt, int[] val, int n) {

        // dp[i][w] = max value using first i items and capacity w
        int[][] dp = new int[n + 1][capacity + 1];

        // Build DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {

                if (wt[i - 1] <= w) {
                    int take = val[i - 1] + dp[i - 1][w - wt[i - 1]];
                    int notTake = dp[i - 1][w];
                    dp[i][w] = Math.max(take, notTake);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }
}
