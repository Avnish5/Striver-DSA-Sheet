package DAY_26_DP_PART_2;

import java.util.ArrayList;
import java.util.List;

public class Palindrome_Partitioning {

    /**
     * Palindrome Partitioning using Backtracking
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n * 2^n)
     *
     * Explanation:
     * - Each character can either start a new partition or extend an existing one
     * - Total number of possible partitions of a string is O(2^n)
     * - For each partition, palindrome checking takes O(n)
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(n)
     *
     * Explanation:
     * - Recursion stack depth can go up to n
     * - Current partition list stores at most n substrings
     * - Output list is not counted in auxiliary space
     */
        public boolean isPalindrome(String s, int l, int r) {

            // Compare characters from both ends moving inward
            while (l < r) {
                if (s.charAt(l) != s.charAt(r)) {
                    return false;
                }
                l++;
                r--;
            }

            return true;
        }

        /**
         * Backtracking helper function
         *
         * idx  -> current index in the string
         * curr -> current palindrome partition being built
         * ans  -> list of all valid palindrome partitions
         */
        public void recursionHelper(
                String s,
                int idx,
                List<List<String>> ans,
                List<String> curr
        ) {

            // Base Case:
            // If we have reached the end of the string,
            // add the current partition to the answer
            if (idx == s.length()) {
                ans.add(new ArrayList<>(curr));
                return;
            }

            // Try all possible substrings starting from idx
            for (int i = idx; i < s.length(); i++) {

                // Check if current substring is a palindrome
                if (isPalindrome(s, idx, i)) {

                    // Choose: add substring to current partition
                    curr.add(s.substring(idx, i + 1));

                    // Explore: recursively partition the remaining string
                    recursionHelper(s, i + 1, ans, curr);

                    // Un-choose (backtrack)
                    curr.remove(curr.size() - 1);
                }
            }
        }

    /**
     * 1. Recursion
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n * 2^n)
     *
     * Explanation:
     * - Each character can either start a new partition or extend an existing one
     * - Total number of possible partitions of a string is O(2^n)
     * - For each partition, palindrome checking takes O(n)
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(n)
     *
     * Explanation:
     * - Recursion stack depth can go up to n
     * - Current partition list stores at most n substrings
     * - Output list is not counted in auxiliary space
     */
        public List<List<String>> partition(String s) {

            List<List<String>> ans = new ArrayList<>();
            List<String> curr = new ArrayList<>();

            // Start backtracking from index 0
            recursionHelper(s, 0, ans, curr);

            return ans;
        }


}
