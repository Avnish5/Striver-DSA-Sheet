package DAY_4_Arrays_Part_4;

import java.util.HashSet;
import java.util.Set;

public class Longest_Substring_Without_Repeating_Characters {

    /**
     * Finds the length of the longest substring without repeating characters.
     *
     * Time Complexity: O(n)
     *   - Each character is visited at most twice (once by right pointer and once by left pointer).
     *
     * Space Complexity: O(min(n, m))
     *   - n = length of the string
     *   - m = size of the character set (e.g., 26 for lowercase letters, 128 for ASCII)
     *   - The set stores characters in the current window, so max size is limited.
     */
    public int lengthOfLongestSubstring(String s) {
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
