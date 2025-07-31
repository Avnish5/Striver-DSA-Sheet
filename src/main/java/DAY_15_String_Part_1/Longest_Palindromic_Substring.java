package DAY_15_String_Part_1;

public class Longest_Palindromic_Substring {

    private boolean isPalindrome1(String s, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (s.charAt(left) == s.charAt(right)) {
            return isPalindrome1(s, left+1, right - 1);
        }

        return false;
    }
    /**
     * 1. Recursion
     *
     * Time Complexity: O(n^3)
     *   - The method uses two nested loops to generate all substrings, which is O(n^2).
     *   - For each substring, it calls isPalindrome, which in the worst case checks up to O(n) characters.
     *   - Overall: O(n^2) * O(n) = O(n^3).
     *
     * Space Complexity: O(n)
     *   - The recursion stack for isPalindrome can go as deep as the length of the substring, which is O(n).
     *   - Apart from that, only a few variables are used, so space complexity is dominated by recursion stack.
     */
    public String longestPalindrome1(String s) {
        int maxLength = 0;  // Length of the longest palindrome found so far
        int start = -1;     // Starting index of the longest palindrome
        int n = s.length();

        // Check all possible substrings s[i...j]
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // If substring s[i...j] is palindrome and longer than current max
                if (isPalindrome1(s, i, j) && (j - i + 1 > maxLength)) {
                    maxLength = j - i + 1;  // Update max palindrome length
                    start = i;              // Update start index
                }
            }
        }

        // Return the longest palindromic substring found
        return s.substring(start, start + maxLength);
    }

    private boolean isPalindrome2(String s, int left, int right, Boolean[][] memo) {

        if (left >= right) {
            memo[left][right] = true;
            return true;
        }

        if(memo[left][right] != null) {
            return memo[left][right];
        }

        if (s.charAt(left) == s.charAt(right)) {
            memo[left][right] = isPalindrome2(s, left+1, right-1, memo);
            return memo[left][right];
        } else {
            memo[left][right]= false;
            return false;
        }
    }

    /**
     * 2. Memoization
     *
     * Time Complexity: O(n^2)
     *   - Two nested loops iterate over all substring start and end indices.
     *   - Each palindrome check is O(1) on average due to memoization.
     *
     * Space Complexity: O(n^2)
     *   - A 2D Boolean memo array of size n x n is used to cache palindrome results.
     */

    public String longestPalindrome2(String s) {
        int maxLength = 0;  // Length of the longest palindrome found so far
        int start = -1;     // Starting index of the longest palindrome
        int n = s.length();

        Boolean[][] memo =new Boolean[n][n];


        // Check all possible substrings s[i...j]
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // If substring s[i...j] is palindrome and longer than current max
                if (isPalindrome2(s, i, j, memo) && (j - i + 1 > maxLength)) {
                    maxLength = j - i + 1;  // Update max palindrome length
                    start = i;              // Update start index
                }
            }
        }

        // Return the longest palindromic substring found
        return s.substring(start, start + maxLength);
    }

    private int expandFromCenter(String s, int left, int right) {

        while (left >=0 && right < s.length()  && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    /**
     * 3. Two Pointer approach.
     * expand-around-center approach.
     *
     * Time Complexity: O(n^2)
     *   - For each character in the string, we expand around center twice
     *     (once for odd length palindromes and once for even length palindromes).
     *   - Each expansion takes O(n) in the worst case.
     *
     * Space Complexity: O(1)
     *   - Only constant extra space is used.
     */
    public String longestPalindrome3(String s) {
        int start = 0, end = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // Length of longest odd-length palindrome centered at i
            int odd = expandFromCenter(s, i, i);
            // Length of longest even-length palindrome centered between i and i+1
            int even = expandFromCenter(s, i, i + 1);
            // Total length:  max(odd, even))
            int len = Math.max(odd, even);

            // If the found palindrome is longer than the current longest, update indices
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        // Return the longest palindrome substring found
        return s.substring(start, end + 1);
    }

}
