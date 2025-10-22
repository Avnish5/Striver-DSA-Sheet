package DAY_27_TRIE;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Number_Of_Distinct_Substrings_In_A_String {

    /**
     * 1. Brute Force
     *
     * Time Complexity:
     * ----------------
     * - Outer loop runs n times.
     * - Inner loop runs ~n times => Total substring generation = O(n^2)
     * - substring(i, j+1) creates a new String -> O(n) time (because it copies characters)
     * - Inserting into HashSet takes O(n) for hashing and comparison.
     * - ✅ Final TC = O(n^2) * O(n) = O(n^3)
     *
     * Space Complexity:
     * -----------------
     * - In worst case (all substrings are unique, e.g., "abcdef"),
     *   number of substrings stored in HashSet = O(n^2)
     * - Each substring has average length O(n), so total memory = O(n^3) characters.
     * - ✅ SC = O(n^2) strings or O(n^3) total characters stored.
     */
    public int countDistinctSubstring1(String st) {
        Set<String> set = new HashSet<>();
        int n = st.length();

        // Generate all possible substrings
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // Add substring from index i to j (inclusive) to the set
                set.add(st.substring(i, j + 1));
            }
        }

        // Return the number of unique substrings stored in HashSet
        return set.size();
    }

    static class TrieNode {
        TrieNode[] childrens = new TrieNode[26];
        boolean isTerminated;

        public boolean contains(char ch) {
            return childrens[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return childrens[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            childrens[ch - 'a'] = node;
        }

        public boolean isTerminated() {
            return isTerminated;
        }

        public void setTerminated(boolean terminated) {
            isTerminated = terminated;
        }
    }

    /**
     * 2. Using Trie
     *
     * Time Complexity:
     * ----------------
     * - Outer loop runs n times.
     * - Inner loop ideally should run from j = i to n-1 (substring from i to j),
     *   but in your code j starts from 0 (this is a bug, explained below).
     * - Each Trie insertion is O(1) per character (constant 26 children).
     * - ✅ Effective TC = O(n^2)
     *
     * Space Complexity:
     * -----------------
     * - In worst case (all substrings unique like "abcdef"), Trie stores O(n^2) nodes.
     * - ✅ SC = O(n^2)
     */
    public int countDistinctSubstring2(String st) {
        TrieNode root = new TrieNode();  // Root of Trie
        int n = st.length();
        int ans = 0; // To count distinct substrings

        // Start from each index i
        for (int i = 0; i < n; i++) {
            TrieNode node = root; // Reset to root for each new starting point

            // Generate substrings starting from index i
            for (int j = i; j < n; j++) {
                char ch = st.charAt(j);

                // If character not present, insert into Trie => New distinct substring
                if (!node.contains(ch)) {
                    node.put(ch, new TrieNode());
                    ans++; // Increment count only when a new branch is created
                }

                // Move to next node
                node = node.get(ch);
            }
        }
        return ans + 1; // +1 to include empty substring ""
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer a, Integer b) -> b - a);

        for (int val : nums) {
            if(pq.size() == k) {
                if (pq.peek() > val) {
                    pq.remove();
                    pq.add(val);
                }
            }

            pq.add(val);
        }

        for (int i = 0; i < k - 1; i++) {
            pq.remove();
        }

        return pq.peek();
    }

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("HELLO","HOW","ARE");
        Stream<String> filterStream = stream.map(String::toLowerCase);

        List<String> list = filterStream.toList();
        for (String s : list) {
            System.out.println(s);
        }
    }

}
