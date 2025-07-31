package DAY_15_String_Part_1;

import java.util.Arrays;

public class Longest_Common_Prefix {

    /**
     *
     * Time Complexity: O(N * log N + M) =  O(N * log N)
     *   - O(N * log N) for sorting the array of strings (N = number of strings).
     *   - O(M) for comparing the first and last strings (M = length of the shortest string).
     *
     * Space Complexity: O(1)
     *   - No extra space used apart from the output StringBuilder (ignoring input and output).
     *
     * @param strs Array of input strings.
     * @return The longest common prefix.
     */
    public String longestCommonPrefix(String[] strs) {
        // Sort the array of strings lexicographically
        Arrays.sort(strs);

        // Create a StringBuilder to build the common prefix
        StringBuilder ans = new StringBuilder();

        // Convert the first and last strings in the sorted array to character arrays
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();

        // Compare characters from the beginning until a mismatch is found
        for (int i = 0; i < first.length; i++) {
            if (first[i] != last[i]) {
                break;
            }
            ans.append(first[i]); // Append matching characters to the result
        }

        // Return the longest common prefix
        return ans.toString();
    }

}
