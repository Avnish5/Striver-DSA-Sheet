package DAY_26_DP_PART_2;

import java.util.Arrays;

public class Egg_Drop {

    /**
     * 1. Recursion.
     *
     *
     * Time Complexity (TC):
     * ---------------------
     * Exponential: O(2^k)
     *
     * Explanation:
     * - For each floor (1..k), we try dropping an egg
     * - This results in two recursive calls:
     *      1) Egg breaks   → eggDrop(n-1, i-1)
     *      2) Egg survives → eggDrop(n, k-i)
     * - Subproblems are recomputed many times
     * - Hence exponential growth of recursion tree
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(k)
     *
     * Explanation:
     * - Maximum recursion depth occurs when we test floors linearly
     * - No extra data structures are used
     */

    public int eggDrop1(int n, int k) {

        // Base case: no floors or one floor
        // If there are 0 floors → 0 attempts
        // If there is 1 floor  → 1 attempt
        if (k == 0 || k == 1) {
            return k;
        }

        // Base case: only one egg left
        // We must try every floor from bottom to top
        if (n == 1) {
            return k;
        }

        int res = Integer.MAX_VALUE;

        // Try dropping the egg from every floor (1 to k)
        for (int i = 1; i <= k; i++) {

            // Case 1: Egg breaks at floor i
            // → check floors below i with n-1 eggs
            int breakCase = eggDrop1(n - 1, i - 1);

            // Case 2: Egg does not break at floor i
            // → check floors above i with same number of eggs
            int notBreakCase = eggDrop1(n, k - i);

            // We take the worst case among both possibilities
            int curr = 1 + Math.max(breakCase, notBreakCase);

            // Choose the minimum attempts among all floors
            res = Math.min(res, curr);
        }

        return res;
    }

    /**
     *2. Recursion + Memoization
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n × k²)
     *
     * Explanation:
     * - There are `n × k` unique states → (eggs, floors)
     * - For each state (n, k), we try dropping the egg from all `k` floors
     * - Each state is computed only once due to memoization
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(n × k)  → memoization table
     * O(k)      → recursion stack (worst case)
     */

    private  int memoHelper(int n, int k, int[][] memo) {

        // If result for this state is already computed, return it
        if (memo[n][k] != -1) {
            return memo[n][k];
        }

        // Base case: no floors or one floor
        // 0 floors → 0 attempts
        // 1 floor  → 1 attempt
        if (k == 0 || k == 1) {
            return k;
        }

        // Base case: only one egg left
        // Must try all floors linearly
        if (n == 1) {
            return k;
        }

        int res = Integer.MAX_VALUE;

        // Try dropping the egg from every floor (1 to k)
        for (int i = 1; i <= k; i++) {

            // Case 1: Egg breaks at floor i
            // → Check floors below i with one less egg
            int breakCase = memoHelper(n - 1, i - 1, memo);

            // Case 2: Egg does not break at floor i
            // → Check floors above i with same number of eggs
            int notBreakCase = memoHelper(n, k - i, memo);

            // We take the worst-case scenario
            int curr = 1 + Math.max(breakCase, notBreakCase);

            // Choose the strategy with minimum worst-case attempts
            res = Math.min(res, curr);
        }

        // Store result in memo table
        memo[n][k] = res;
        return memo[n][k];
    }

    public int eggDrop2(int n, int k) {

        // memo[n][k] stores the minimum attempts needed
        // with n eggs and k floors
        int[][] memo = new int[n + 1][k + 1];

        // Initialize memo table with -1 (uncomputed states)
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return memoHelper(n, k, memo);
    }

    /**
     * 3. DP with Binary Search Optimization
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n × k × log k)
     *
     * Explanation:
     * - There are `n × k` DP states
     * - For each state, instead of trying all `k` floors,
     *   we apply binary search over possible drop floors
     * - Binary search works due to monotonic behavior of subproblems
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(n × k)
     *
     * Explanation:
     * - DP table of size (n+1) × (k+1)
     * - No additional recursive stack
     */

    public int eggDrop(int n, int k) {

        // dp[i][j] = minimum number of attempts needed
        // in the worst case with i eggs and j floors
        int[][] dp = new int[n + 1][k + 1];

        // Base case 1: 0 floors → 0 attempts
        // Base case 2: 1 floor  → 1 attempt
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        // Base case: only 1 egg
        // Must try all floors sequentially
        for (int j = 1; j <= k; j++) {
            dp[1][j] = j;
        }

        // Fill the DP table for remaining states
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {

                int low = 1, high = j;
                int ans = Integer.MAX_VALUE;

                // Binary search to find optimal dropping floor
                while (low <= high) {
                    int mid = (low + high) / 2;

                    // Case 1: Egg breaks at floor mid
                    // → Check floors below with one less egg
                    int breakCase = dp[i - 1][mid - 1];

                    // Case 2: Egg does not break at floor mid
                    // → Check floors above with same number of eggs
                    int notBreakCase = dp[i][j - mid];

                    // Worst-case attempts for this drop
                    int temp = 1 + Math.max(breakCase, notBreakCase);

                    // Choose minimum among all worst-case attempts
                    ans = Math.min(ans, temp);

                    // Binary search decision:
                    // If breaking case is worse, move downward
                    if (breakCase > notBreakCase) {
                        high = mid - 1;
                    }
                    // Otherwise, move upward
                    else {
                        low = mid + 1;
                    }
                }

                dp[i][j] = ans;
            }
        }

        return dp[n][k];
    }


}
