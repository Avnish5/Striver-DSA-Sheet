package DAY_26_DP_PART_2;

import java.util.Arrays;

public class Road_Cutting {

    /**
     * 1. Recursion + Memoization (Top-Down DP)
     *
     * Time Complexity (TC):
     * ---------------------
     * O(m³)
     *
     * Explanation:
     * - Let `m` be the number of cuts
     * - We add two artificial cuts: 0 and n
     * - DP state is defined by (left, right) → current stick segment
     * - Total DP states = O(m²)
     * - For each state, we try all possible cuts between (left, right)
     *   which takes O(m) time
     * - Hence overall complexity = O(m² × m) = O(m³)
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(m²)
     *
     * Explanation:
     * - Memoization table of size (m+2) × (m+2)
     * - Recursive stack depth can go up to O(m)
     * - Memo table dominates space usage
     */
    private int memoHelper(int[] newCuts, int left, int right, int[][] memo) {

        // Base case:
        // If there is no cut between left and right,
        // i.e., they are adjacent in the cuts array
        if (right - left == 1) {
            return 0;
        }

        // If already computed, return stored result
        if (memo[left][right] != -1) {
            return memo[left][right];
        }

        int result = Integer.MAX_VALUE;

        // Try making each possible cut between left and right
        for (int index = left + 1; index <= right - 1; index++) {

            // Cost of current cut =
            // length of current stick segment
            int cost = newCuts[right] - newCuts[left];

            // Add cost of left and right subsegments
            cost += memoHelper(newCuts, left, index, memo)
                    + memoHelper(newCuts, index, right, memo);

            // Take minimum among all choices
            result = Math.min(result, cost);
        }

        // Store result in memo table
        memo[left][right] = result;
        return result;
    }

    public int minCost(int n, int[] cuts) {

        // Sort cuts to ensure correct segment ordering
        Arrays.sort(cuts);

        int m = cuts.length;

        // Create a new cuts array with boundaries included
        int[] newCuts = new int[m + 2];
        newCuts[0] = 0;
        newCuts[m + 1] = n;

        // Copy original cuts
        for (int i = 0; i < m; i++) {
            newCuts[i + 1] = cuts[i];
        }

        // Memoization table
        int[][] memo = new int[m + 2][m + 2];

        // Initialize memo table with -1 (uncomputed)
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Compute minimum cost for the full stick
        return memoHelper(newCuts, 0, m + 1, memo);
    }

}
