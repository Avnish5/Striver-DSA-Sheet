package DAY_27_TRIE;

import java.util.*;

public class Maximum_XOR_With_An_Element_From_Array {

    static class TrieNode {
        TrieNode left;
        TrieNode right;
    }

    public void insert(TrieNode root, int num) {
       TrieNode pcrawal = root;

        for (int i = 31; i >=0; i--) {
            int ith_bit = (num >> i) & 1;

            if (ith_bit == 1)  {
                if (pcrawal.right == null) {
                    TrieNode right = new TrieNode();
                    pcrawal.right = right;
                }

                pcrawal = pcrawal.right;
            } else {
                if (pcrawal.left == null) {
                    TrieNode left = new TrieNode();
                    pcrawal.left = left;
                }

                pcrawal = pcrawal.left;
            }
        }
    }

    public int maxOR(TrieNode root, int num) {
        TrieNode pcrawl = root;
        int maxor = 0;

        for (int i = 31; i >=0; i--) {

            int ith_bit = (num >> i) & 1;

            if (ith_bit == 1) {
                if (pcrawl.left != null) {
                    maxor += Math.pow(2, i);
                    pcrawl = pcrawl.left;
                } else {
                    pcrawl = pcrawl.right;
                }
            } else {
                if (pcrawl.right != null) {
                    maxor += Math.pow(2, i);
                    pcrawl = pcrawl.right;
                } else {
                    pcrawl = pcrawl.left;
                }
            }

        }

        return maxor;
    }

    /**
     * Time Complexity:
     *  - Sorting nums: O(n log n)
     *  - Sorting queries: O(q log q)
     *  - Inserting into Trie: O(n * 32)
     *  - Querying Trie: O(q * 32)
     *  Overall: O((n + q) * log(n + q)) + O(32 * (n + q)) → O((n + q) log(n + q))
     *
     * Space Complexity: O(32 * n) for Trie → O(n)
     */
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums); // Step 1: Sort nums

        // Step 2: Modify queries to store (x, m, index)
        int q = queries.length;
        int[][] offlineQueries = new int[q][3];
        for (int i = 0; i < q; i++) {
            offlineQueries[i][0] = queries[i][0]; // x
            offlineQueries[i][1] = queries[i][1]; // m
            offlineQueries[i][2] = i;            // original index
        }

        // Step 3: Sort queries by m
        Arrays.sort(offlineQueries, (a, b) -> a[1] - b[1]);

        int[] ans = new int[q];
        TrieNode root = new TrieNode();
        int idx = 0;
        int n = nums.length;

        // Step 4: Process each query
        for (int[] query : offlineQueries) {
            int x = query[0];
            int m = query[1];
            int originalIndex = query[2];

            // Insert nums into Trie which are <= m
            while (idx < n && nums[idx] <= m) {
                insert(root, nums[idx]);
                idx++;
            }

            // If no numbers inserted into Trie, return -1
            if (idx == 0) {
                ans[originalIndex] = -1;
            } else {
                ans[originalIndex] = maxOR(root, x);
            }
        }

        return ans;
    }
}
