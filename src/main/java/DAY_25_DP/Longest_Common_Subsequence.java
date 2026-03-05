package DAY_25_DP;

import java.util.Arrays;

public class Longest_Common_Subsequence {

    private int recursionHelper(int i, int j, String text1, String text2) {

        // Base Case:
        // If either string is fully processed,
        // no common subsequence is possible
        if (i >= text1.length() || j >= text2.length()) {
            return 0;
        }

        // If current characters match,
        // include this character in LCS
        if (text1.charAt(i) == text2.charAt(j)) {
            return 1 + recursionHelper(i + 1, j + 1, text1, text2);
        }

        // If characters do not match:
        // Option 1: Skip current character of text1
        int left = recursionHelper(i + 1, j, text1, text2);

        // Option 2: Skip current character of text2
        int right = recursionHelper(i, j + 1, text1, text2);

        // Take the maximum of both choices
        return Math.max(left, right);
    }

    /**
     * 1. Recursion
     *
     * Time Complexity (TC):
     * ---------------------
     * Exponential: O(2^(m+n))
     *
     * Explanation:
     * - At each mismatch, two recursive calls are made:
     *   (i+1, j) and (i, j+1)
     * - The same subproblems are recomputed many times
     * - Hence exponential time complexity
     *
     * Space Complexity (SC):
     * ----------------------
     * O(m + n)
     *
     * Explanation:
     * - Maximum depth of recursion is m + n
     * - No extra data structures are used
     *
     * Where:
     * m = length of text1
     * n = length of text2
     */
    public int longestCommonSubsequence1(String text1, String text2) {

        // Start recursion from index 0 of both strings
        return recursionHelper(0, 0, text1, text2);
    }

    private int memoizationHelper(int i, int j,
                                  String text1, String text2,
                                  int[][] memo) {

        // Base Case:
        // If either string is fully processed,
        // no further common subsequence is possible
        if (i >= text1.length() || j >= text2.length()) {
            return 0;
        }

        // If result for this state is already computed,
        // return it to avoid recomputation
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // If current characters match,
        // include this character in the LCS
        if (text1.charAt(i) == text2.charAt(j)) {
            return memo[i][j] =
                    1 + memoizationHelper(i + 1, j + 1, text1, text2, memo);
        }

        // If characters do not match:
        // Option 1: Skip current character of text1
        int left = memoizationHelper(i + 1, j, text1, text2, memo);

        // Option 2: Skip current character of text2
        int right = memoizationHelper(i, j + 1, text1, text2, memo);

        // Store and return the maximum of both options
        return memo[i][j] = Math.max(left, right);
    }

    /**
     * 2. Memoization
     *
     * Time Complexity (TC):
     * ---------------------
     * O(m × n)
     *
     * Explanation:
     * - There are m × n unique subproblems defined by (i, j)
     * - Each subproblem is solved only once due to memoization
     * - Each state does constant work
     *
     * Space Complexity (SC):
     * ----------------------
     * O(m × n) + O(m + n)
     *
     * Explanation:
     * - O(m × n) for the memoization table
     * - O(m + n) for recursion stack depth
     *
     * Where:
     * m = length of text1
     * n = length of text2
     */
    public int longestCommonSubsequence2(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        // Memoization table where memo[i][j] stores
        // LCS length of text1[i…] and text2[j…]
        int[][] memo = new int[m + 1][n + 1];

        // Initialize memo table with -1 (uncomputed states)
        for (int i = 0; i <= m; i++) {
            Arrays.fill(memo[i], -1);
        }

        // Start recursion from index 0 of both strings
        return memoizationHelper(0, 0, text1, text2, memo);
    }

    /**
     * 3. Iterative or Bottom-Up
     *
     * Time Complexity (TC):
     * ---------------------
     * O(m × n)
     *
     * Explanation:
     * - We fill a DP table of size (m+1) × (n+1)
     * - Each cell is computed once in constant time
     *
     * Space Complexity (SC):
     * ----------------------
     * O(m × n)
     *
     * Explanation:
     * - DP table stores results for all prefix combinations
     *
     * Where:
     * m = length of text1
     * n = length of text2
     */
    public int longestCommonSubsequence3(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        // dp[i][j] = length of LCS between
        // text1[0..i-1] and text2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Base Case:
        // dp[0][*] = 0  -> empty text1
        // dp[*][0] = 0  -> empty text2
        // (Java initializes arrays with 0, so no need to fill explicitly)

        // Build the DP table bottom-up
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // If characters match,
                // include this character in LCS
                //we do i-1 and j-1 to prevent string out of bound exception
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                // If characters do not match,
                // take the maximum of skipping one character
                else {
                    dp[i][j] = Math.max(
                            dp[i - 1][j],   // skip char from text1
                            dp[i][j - 1]    // skip char from text2
                    );
                }
            }
        }

        // Final answer is stored at dp[m][n]
        return dp[m][n];
    }



}
