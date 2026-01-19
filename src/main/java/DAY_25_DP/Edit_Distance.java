package DAY_25_DP;

import java.util.Arrays;

public class Edit_Distance {

    public int helper(int i, int j, int m, int n, String s1, String s2) {

        // Base Case 1:
        // If all characters of s1 are processed,
        // we must insert all remaining characters of s2
        if (i == m) {
            return n - j;
        }

        // Base Case 2:
        // If all characters of s2 are processed,
        // we must delete all remaining characters of s1
        if (j == n) {
            return m - i;
        }

        // If current characters match,
        // move both pointers without any operation
        if (s1.charAt(i) == s2.charAt(j)) {
            return helper(i + 1, j + 1, m, n, s1, s2);
        }

        // Operation 1: Insert
        // Insert s2[j] into s1, so we move j forward
        int insert = 1 + helper(i, j + 1, m, n, s1, s2);

        // Operation 2: Delete
        // Delete s1[i], so we move i forward
        int delete = 1 + helper(i + 1, j, m, n, s1, s2);

        // Operation 3: Replace
        // Replace s1[i] with s2[j], move both pointers
        int replace = 1 + helper(i + 1, j + 1, m, n, s1, s2);

        // Return the minimum cost among all operations
        return Math.min(insert, Math.min(delete, replace));
    }

    /**
     * 1.Recursion
     *
     * Time Complexity (TC):
     * ---------------------
     * Exponential: O(3^(m+n))
     *
     * Explanation:
     * - At each mismatch, we try up to 3 operations:
     *   Insert, Delete, Replace
     * - This creates a branching factor of 3
     * - Same subproblems are recomputed multiple times
     * - Hence exponential time complexity
     *
     * Space Complexity (SC):
     * ----------------------
     * O(m + n)
     *
     * Explanation:
     * - Maximum depth of recursion is m + n
     * - No extra data structures are used
     */
    public int minDistance1(String word1, String word2) {
        // Start recursion from index 0 of both strings
        return helper(0, 0, word1.length(), word2.length(), word1, word2);
    }


    public int helper2(int i, int j, int m, int n,
                       String s1, String s2,
                       int[][] memo) {

        // Base Case 1:
        // If all characters of s1 are processed,
        // we must insert all remaining characters of s2
        if (i == m) {
            return memo[i][j] = n - j;
        }

        // Base Case 2:
        // If all characters of s2 are processed,
        // we must delete all remaining characters of s1
        if (j == n) {
            return memo[i][j] = m - i;
        }

        // If result already computed, return it
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // If current characters match,
        // move both pointers without any operation
        if (s1.charAt(i) == s2.charAt(j)) {
            return memo[i][j] =
                    helper2(i + 1, j + 1, m, n, s1, s2, memo);
        }

        // Operation 1: Insert
        // Insert s2[j] into s1, so j moves forward
        int insert = 1 + helper2(i, j + 1, m, n, s1, s2, memo);

        // Operation 2: Delete
        // Delete s1[i], so i moves forward
        int delete = 1 + helper2(i + 1, j, m, n, s1, s2, memo);

        // Operation 3: Replace
        // Replace s1[i] with s2[j], move both pointers
        int replace = 1 + helper2(i + 1, j + 1, m, n, s1, s2, memo);

        // Store and return the minimum cost
        return memo[i][j] = Math.min(insert, Math.min(delete, replace));
    }

    /**
     * 2. Memoization
     *
     * Time Complexity (TC):
     * ---------------------
     * O(m × n)
     *
     * Explanation:
     * - There are m × n unique states defined by (i, j)
     * - Each state is computed only once due to memoization
     * - Each computation does constant work (3 choices)
     *
     * Space Complexity (SC):
     * ----------------------
     * O(m × n) + O(m + n)
     *
     * Explanation:
     * - O(m × n) for memoization table
     * - O(m + n) for recursion stack depth
     */
    public int minDistance2(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        // Memo table where memo[i][j] stores
        // min operations to convert word1[i…] → word2[j…]
        int[][] memo = new int[m + 1][n + 1];

        // Initialize memo table with -1 (uncomputed)
        for (int i = 0; i <= m; i++) {
            Arrays.fill(memo[i], -1);
        }

        // Start recursion from index 0 of both strings
        return helper2(0, 0, m, n, word1, word2, memo);
    }

    /**
     * 3. Bottom-Up
     *
     * Time Complexity (TC):
     * ---------------------
     * O(m × n)
     *
     * Space Complexity (SC):
     * ----------------------
     * O(m × n)
     *
     * Where:
     * m = length of word1
     * n = length of word2
     */
    public int minDistance3(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        // dp[i][j] = minimum operations to convert
        // word1[0..i-1] → word2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Base Case 1:
        // Convert word1[0..i-1] → empty string (delete all characters)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Base Case 2:
        // Convert empty string → word2[0..j-1] (insert all characters)
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // If characters match, no operation needed
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // If characters do not match
                else {
                    int insert  = dp[i][j - 1];     // Insert
                    int delete  = dp[i - 1][j];     // Delete
                    int replace = dp[i - 1][j - 1]; // Replace

                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        // Final answer: full string → full string
        return dp[m][n];
    }







}
