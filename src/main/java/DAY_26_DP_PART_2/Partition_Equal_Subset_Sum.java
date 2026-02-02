package DAY_26_DP_PART_2;

import java.util.Arrays;

public class Partition_Equal_Subset_Sum {

    private boolean memoHelper(int x, int[] nums, int i, int n, int[][] memo) {

        // If required sum becomes 0, subset found
        if (x == 0) {
            return true;
        }

        // If all elements are used and sum is still not 0
        if (i >= n) {
            return false;
        }

        // Return cached result if available
        if (memo[i][x] != -1) {
            return memo[i][x] == 1;
        }

        boolean take = false;

        // Option 1: take current element if it does not exceed remaining sum
        if (nums[i] <= x) {
            take = memoHelper(x - nums[i], nums, i + 1, n, memo);
        }

        // Option 2: skip current element
        boolean notTake = memoHelper(x, nums, i + 1, n, memo);

        // Store result in memo table
        memo[i][x] = (take || notTake) ? 1 : 0;

        return take || notTake;
    }

    /**
     * 1. Recursion
     *
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n * target)
     *
     * Explanation:
     * - target = sum / 2
     * - Recursive DP explores at most n * target states
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(n * target) for memo table
     * O(n) recursion stack
     */
    public boolean canPartition(int[] nums) {

        int sum = 0;

        // Compute total sum
        for (int x : nums) {
            sum += x;
        }

        // If total sum is odd, partition is impossible
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;

        // Memo table initialization
        int[][] memo = new int[201][20001];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return memoHelper(target, nums, 0, nums.length, memo);
    }


        /**
         * 2. Bottom-Up DP
         *
         * Time Complexity (TC):
         * ---------------------
         * O(n * target)
         *
         * Space Complexity (SC):
         * ----------------------
         * O(target)
         *
         * Where:
         * - n = number of elements
         * - target = totalSum / 2
         */
        public boolean canPartition2(int[] nums) {

            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            // If total sum is odd, equal partition is impossible
            if (sum % 2 != 0) return false;

            int target = sum / 2;

            // dp[s] = true if subset with sum 's' can be formed
            boolean[] dp = new boolean[target + 1];

            // Base case: sum 0 is always achievable (empty subset)
            dp[0] = true;

            // Process each number
            for (int num : nums) {

                // Traverse backwards to avoid reusing the same element
                for (int s = target; s >= num; s--) {
                    dp[s] = dp[s] || dp[s - num];
                }
            }

            return dp[target];
        }



}
