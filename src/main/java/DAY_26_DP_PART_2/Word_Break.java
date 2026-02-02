package DAY_26_DP_PART_2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Word_Break {

        private boolean memoHelper(
                String s,
                int idx,
                int n,
                Set<String> wordSet,
                Boolean[] memo
        ) {

            // Base case: reached end of string
            if (idx == n) {
                return true;
            }

            // Return cached result if already computed
            if (memo[idx] != null) {
                return memo[idx];
            }

            // Try all possible splits starting from idx
            for (int endIdx = idx + 1; endIdx <= n; endIdx++) {

                // Current substring s[idx..endIdx-1]
                String split = s.substring(idx, endIdx);

                // If prefix is in dictionary and remaining suffix is valid
                if (wordSet.contains(split)
                        && memoHelper(s, endIdx, n, wordSet, memo)) {

                    memo[idx] = true;
                    return true;
                }
            }

            // No valid segmentation found
            memo[idx] = false;
            return false;
        }

    /**
     * Word Break using Recursion + Memoization (Optimized with HashSet)
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n^2)
     *
     * Explanation:
     * - There are O(n) states for idx (0 → n)
     * - For each idx, we try all endIdx from idx+1 → n → O(n)
     * - HashSet lookup is O(1)
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(n)
     *
     * Explanation:
     * - memo[] array of size n
     * - recursion stack depth up to n
     * - HashSet stores dictionary words
     */
        public boolean wordBreak1(String s, List<String> wordDict) {

            int n = s.length();

            // Convert List to HashSet for O(1) lookups
            Set<String> wordSet = new HashSet<>(wordDict);

            // memo[i] = can s[i..n-1] be segmented?
            Boolean[] memo = new Boolean[n + 1];

            return memoHelper(s, 0, n, wordSet, memo);
        }

    public boolean wordBreak2(String s, List<String> wordDict) {

        // Put dictionary words in HashSet for fast lookup
        Set<String> dict = new HashSet<>(wordDict);

        int n = s.length();

        // dp[i] = true means s[0 .. i-1] can be formed using dictionary words
        boolean[] dp = new boolean[n + 1];

        // Empty string is always valid
        dp[0] = true;

        // i = ending position of the string
        for (int i = 1; i <= n; i++) {

            // j = where we cut the string
            for (int j = 0; j < i; j++) {

                // If left part is valid
                // and right part is a dictionary word
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // no need to check further
                }
            }
        }

        // Answer for full string
        return dp[n];
    }
}
