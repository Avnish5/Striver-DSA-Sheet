package DAY_4_Arrays_Part_4;

import java.util.HashSet;
import java.util.Set;

public class Longest_Substring_Without_Repeating_Characters {

    /**
     * 1.Brute-Force
     *
     * Time Complexity: O(n ^ 2)
     *
     * Space Complexity: O(n)
     */
    public int lengthOfLongestSubstring1(String s) {

        int n = s.length();
        int ans = 0; // Stores the maximum length of substring without repeating characters

        // Outer loop: pick each index as starting point
        for(int i = 0; i < n ; i++) {

            Set<Character> set = new HashSet<>(); // To track unique characters in current window
            set.add(s.charAt(i));
            int temp = 1; // Current substring length

            // Inner loop: expand substring from index i
            for(int j = i + 1; j < n; j++) {

                // If character is not seen before, expand window
                if(!set.contains(s.charAt(j))) {
                    temp++;
                    set.add(s.charAt(j));
                }
                // If duplicate found, stop expansion
                else {
                    ans = Math.max(ans, temp);
                    break;
                }
            }

            // Update answer for cases where no duplicate was found till end
            ans = Math.max(ans, temp);
        }

        return ans;
    }

    /**
     * 2.Optimized
     *
     * Time Complexity: O(n)
     *   - Each character is visited at most twice (once by right pointer and once by left pointer).
     *
     * Space Complexity: O(min(n, m))
     *   - n = length of the string
     *   - m = size of the character set (e.g., 26 for lowercase letters, 128 for ASCII)
     *   - The set stores characters in the current window, so max size is limited.
     */
    public int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<>();  // Stores unique characters in current window
        int max = 0;                           // Length of longest substring found
        int left = 0;                          // Left boundary of sliding window

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If character c already exists in the current window,
            // shrink window from the left until c can be added without duplication
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;  // Move left pointer forward to shrink window
            }

            set.add(c);  // Add current character to the window
            // Update max length if current window is longer
            max = Math.max(max, right - left + 1);
        }

        return max;
    }


}
