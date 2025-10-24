package DAY_27_TRIE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Power_Set_String {

    /**
     *
     * Time Complexity:  O(2^n * n)
     *     - There are 2^n possible subsequences for a string of length n.
     *     - Each subsequence construction (StringBuilder toString + copy) costs up to O(n).
     *
     * Space Complexity: O(2^n * n)
     *     - To store all subsequences in the result list.
     *     - The recursion stack uses O(n) auxiliary space.
     */
    public List<String> AllPossibleStrings(String s) {
        List<String> ans = new ArrayList<>();
        helper(s, 0, new StringBuilder(), ans);
        Collections.sort(ans);  // Sort lexicographically
      //  ans.remove("");         // Optional: remove empty string if not required
        return ans;
    }

    private void helper(String s, int i, StringBuilder current, List<String> ans) {
        // Base case: if we've processed all characters, add the built string
        if (i == s.length()) {
            ans.add(current.toString());
            return;
        }

        // Choice 1: Include the current character
        current.append(s.charAt(i));
        helper(s, i + 1, current, ans);

        // Backtrack: remove the last character before exploring the next choice
        current.deleteCharAt(current.length() - 1);

        // Choice 2: Exclude the current character
        helper(s, i + 1, current, ans);
    }

    public int maxProfit(int[] prices) {
      int ans = 0;
      int minimum = prices[0];

      for (int i = 1; i < prices.length; i++) {
          if (prices[i] < minimum) {
              minimum = prices[i];
          }

          if (prices[i] - minimum > 0) {
              ans = Math.max(ans, prices[i] - minimum);
          }
      }
      return ans;
    }
}
