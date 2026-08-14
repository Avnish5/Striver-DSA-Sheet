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

    class TrieNode {
        TrieNode[] children = new TrieNode[26]; // links for 'a' to 'z'
        boolean isEnd = false; // marks end of a word
        int childCount = 0;    // number of non-null children (used to detect branching)
    }

    private TrieNode root;

    /**
     * 2. Using Trie
     *
     * Time Complexity: O(N * M)
     *   - O(N * M) to insert all strings into Trie
     *       (N = number of strings, M = average length of string)
     *   - O(M) to traverse Trie for LCP
     *   - Overall: O(N * M)
     *
     * Space Complexity: O(N * M)
     *   - Trie stores all characters of all strings in worst case
     *   - Each node contains 26 pointers
     *
     */
    public String longestCommonPrefix2(String[] strs) {
        root = new TrieNode();

        // Step 1: Insert all strings into Trie
        for (String word : strs) {
            insert(word);
        }

        // Step 2: Traverse Trie to find LCP
        StringBuilder prefix = new StringBuilder();
        TrieNode node = root;

        while (node != null) {

            // Stop if:
            // 1. More than one child → branching occurs
            // 2. End of a word → one string finished
            if (node.childCount != 1 || node.isEnd) break;

            // Find the only child and move forward
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    // Convert index to character and append
                    prefix.append((char) ('a' + i));

                    // Move to next node
                    node = node.children[i];
                    break; // only one child exists, so stop loop
                }
            }
        }

        return prefix.toString();
    }

    // Insert a word into Trie
    private void insert(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int index = ch - 'a';

            // Create new node if not present
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
                node.childCount++; // increase child count
            }

            node = node.children[index];
        }

        node.isEnd = true; // mark end of word
    }

}
