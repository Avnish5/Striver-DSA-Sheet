package DAY_26_DP_PART_2;

import java.util.Arrays;

public class Coin_Change {

    /**
     * 1. Pure Recursion
     *
     * Time Complexity (TC):
     * ---------------------
     * Exponential → O(k^amount)
     *
     * Explanation:
     * - For every amount, we try all coin denominations
     * - Each recursive call branches into `coins.length` possibilities
     * - Same subproblems are recomputed many times
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(amount)
     *
     * Explanation:
     * - Maximum recursion depth occurs when we keep subtracting
     *   the smallest coin repeatedly
     */
    public int recursionHelper(int[] coins, int amount) {

        // Base case: exact amount achieved
        if (amount == 0) {
            return 0;
        }

        // Base case: amount becomes negative → invalid
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }

        int minCoins = Integer.MAX_VALUE;

        // Try using each coin
        for (int coin : coins) {

            int res = recursionHelper(coins, amount - coin);

            // If valid solution exists
            if (res != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, 1 + res);
            }
        }

        return minCoins;
    }

    public int coinChange1(int[] coins, int amount) {

        int result = recursionHelper(coins, amount);

        // If not possible, return -1
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * 2. Recursion + Memoization
     *
     * Time Complexity (TC):
     * ---------------------
     * O(amount × n)
     *
     * Explanation:
     * - `amount` different states (0 → amount)
     * - For each state, we try all `n` coins
     * - Each subproblem is computed only once
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(amount)
     *
     * Explanation:
     * - Memo array of size (amount + 1)
     * - Recursion stack depth up to O(amount)
     */
    private int memoHelper(int[] coins, int amount, int[] memo) {

        // Base case: exact amount achieved
        if (amount == 0) {
            return 0;
        }

        // Base case: invalid amount
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }

        // If already computed, return memoized result
        if (memo[amount] != -1) {
            return memo[amount];
        }

        int minCoins = Integer.MAX_VALUE;

        // Try every coin
        for (int coin : coins) {

            int res = memoHelper(coins, amount - coin, memo);

            if (res != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, 1 + res);
            }
        }

        // Store result
        memo[amount] = minCoins;
        return minCoins;
    }

    public int coinChange2(int[] coins, int amount) {

        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);

        int result = memoHelper(coins, amount, memo);

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * 3. Bottom-Up Dynamic Programming (Tabulation)
     *
     * Problem:
     * --------
     * Given coin denominations and a target amount,
     * find the minimum number of coins needed to make that amount.
     *
     *
     * Time Complexity (TC):
     * ---------------------
     * O(amount × n)
     *
     * Explanation:
     * - We iterate over all amounts from 1 → amount
     * - For each amount, we try all `n` coin denominations
     * - Each DP state is computed once
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(amount)
     *
     * Explanation:
     * - DP array of size (amount + 1)
     * - No recursion stack is used
     */

    public int coinChange3(int[] coins, int amount) {

        // dp[i] = minimum number of coins needed to make amount i
        int[] dp = new int[amount + 1];

        // Base case:
        // dp[0] = 0 → 0 coins needed to make amount 0
        dp[0] = 0;

        // Build DP table from smaller amounts to larger amounts
        for (int i = 1; i <= amount; i++) {

            // Initialize current state as unreachable
            dp[i] = Integer.MAX_VALUE;

            // Try using each coin
            for (int coin : coins) {

                // Check if coin can be used
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {

                    // Transition:
                    // dp[i] = min(dp[i], 1 + dp[i - coin])
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        // If amount cannot be formed, return -1
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    static final long NEG_INF = Long.MIN_VALUE / 2;

    public long solve(int i, int trend, int n, int[] nums, long[][] memo) {
        if(i == n) {
            return trend == 3 ? 0: NEG_INF;
        }

        if(memo[i][trend] != Long.MIN_VALUE) {
            return memo[i][trend];
        }

        long take = NEG_INF;
        long skip = NEG_INF;

        if(trend == 0) {
            skip = solve(i + 1, 0, n, nums, memo);
        }

        if(trend == 3) {
            take = nums[i];
        }

        if(i+1 < n) {
            int curr = nums[i];
            int next = nums[i+1];

            if(trend == 0) {
                take = Math.max(take, curr + solve(i+1, 1, n, nums, memo));
            } else if (trend == 1) {
                if(next > curr) {
                    take = Math.max(take, curr + solve(i+1, 1, n, nums, memo));
                } else if (next < curr) {
                    take = Math.max(take, curr + solve(i+1, 2, n, nums, memo));
                }
            } else if (trend == 2) {
                if(next < curr) {
                    take = Math.max(take, curr + solve(i+1, 2, n, nums, memo));
                } else if (next > curr) {
                    take = Math.max(take, curr + solve(i+1, 3, n, nums, memo));
                }
            } else if (trend == 3 && next >  curr) {
                take = Math.max(take, curr + solve(i+1, 3, n, nums, memo));
            }
        }

        return memo[i][trend] = Math.max(skip, take);
    }
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long[][] memo = new long[n+1][4];

        for(long[] arr : memo) {
            Arrays.fill(arr, Long.MIN_VALUE);
        }

        return solve(0, 0 , n , nums, memo);
    }

}
