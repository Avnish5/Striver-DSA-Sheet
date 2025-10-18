package DAY_27_TRIE;

import java.util.HashSet;
import java.util.Set;

public class Maximum_XOR_Of_Two_Numbers_In_An_Array {

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
     * ----------------
     * 1. Building the Trie → O(n * 32) = O(n)
     *    - We insert n numbers, each up to 32 bits.
     * 2. Querying max XOR for each number → O(n * 32) = O(n)
     *    - For each number, we traverse at most 32 levels in the Trie.
     * Total TC → O(n) + O(n) = O(n)
     *
     * Space Complexity:
     * -----------------
     * - In the worst case, each number creates a unique path of 32 nodes.
     * - Total nodes possible = n * 32 → O(n)
     * SC → O(n)
     */
    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();

        for (int val : nums) {
            insert(root, val);
        }

        int ans = 0;

        for (int val : nums) {
            ans = Math.max(ans, maxOR(root, val));
        }

        return ans;
    }
}
