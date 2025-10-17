package DAY_21_BINARY_SEARCH_TREE_PART_2;

import java.util.HashSet;
import java.util.Set;

public class Find_A_Pair_With_A_Given_Sum_In_BST {

    /**
     * Time Complexity: O(n)
     * - Each node in the tree is visited once.
     * <p>
     * Space Complexity: O(n)
     * - In the worst case, the HashSet stores all n node values.
     * - Plus recursion stack space in skewed tree: O(h), where h = height of tree.
     */
    Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        // Base case: if current node is null, return false
        if (root == null) return false;

        // If we already saw a number such that (k - root.val) exists, we found a pair
        if (set.contains(k - root.val)) return true;

        // Otherwise, store the current node's value in the set
        set.add(root.val);

        // Recurse into left and right subtrees
        // OR is used because if any subtree finds the target pair, return true
        return findTarget(root.left, k) || findTarget(root.right, k);

    }

}
