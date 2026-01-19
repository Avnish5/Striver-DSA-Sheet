package DAY_25_DP;

import java.util.Arrays;

public class Max_Sum_Increasing_Subsequence {


    public int helper(int[] arr, int prev, int curr, int[][] memo) {

        // Base case: all elements have been processed
        if (curr >= arr.length) {
            return 0;
        }

        // Return cached result if already computed
        if (prev != -1 && memo[prev][curr] != -1) {
            return memo[prev][curr];
        }

        // Case 1: Exclude current element
        // Move to next index without changing 'prev'
        int exclude = helper(arr, prev, curr + 1, memo);

        int include = 0;

        // Case 2: Include current element
        // Allowed only if:
        // 1) No previous element is chosen (prev == -1)
        // 2) Current element is greater than the previously chosen element
        if (prev == -1 || arr[curr] > arr[prev]) {
            include = arr[curr] + helper(arr, curr, curr + 1, memo);
        }

        // Store result in memo table when prev is a valid index
        if (prev != -1) {
            memo[prev][curr] = Math.max(include, exclude);
            return memo[prev][curr];
        }

        // When prev == -1, just return the computed result
        return Math.max(include, exclude);
    }


    /**
     * Computes the Maximum Sum Increasing Subsequence (MSIS)
     * using recursion with memoization.
     *
     * Time Complexity:
     *  - O(n^2)
     *
     * Space Complexity:
     *  - O(n^2) for memo table
     *  - O(n) recursion stack
     */
    public int maxSumIS(int[] arr) {

        // Memo table initialization with -1
        int[][] memo = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        // Start recursion with no element chosen initially
        return helper(arr, -1, 0, memo);
    }

}
