package DAY_9_Recursion;

import java.util.ArrayList;
import java.util.List;

public class Palindrome_Partitioning {

    public boolean isPalindrome(String s, int low, int high) {
           while (low < high) {
               if (s.charAt(low) != s.charAt(high)) {
                   return false;
               }
               low++;
               high--;
           }

           return true;
    }

    public void helper(String s, int index, List<List<String>> ans, List<String> curr) {
        //  Base case: if we reached end of string, add current partition to result
        if (index == s.length()) {
            ans.add(new ArrayList<>(curr)); // Make a copy to avoid mutation
            return;
        }

        // Try all possible substring cuts starting at current index
        for (int i = index; i < s.length(); i++) {
            // Check if substring s[index...i] is a palindrome
            if (isPalindrome(s, index, i)) {
                // Choose: add substring to current partition
                curr.add(s.substring(index, i + 1));

                // Explore: recursively partition remaining substring
                helper(s, i + 1, ans, curr);

                // Unchoose: backtrack (remove last added substring)
                curr.remove(curr.size() - 1);
            }
        }
    }

    /**
     * T.C : O(n * 2^n) - For a string of length n, there are 2^(𝑛 − 1) potential ways to partition it(since each position can either be a cut or not). and we also check palindrome O(n)
     * S.C : O(n * 2^n) - Number of partitions * their length
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        helper(s, 0, ans, new ArrayList<>());
        return ans;
    }
}
