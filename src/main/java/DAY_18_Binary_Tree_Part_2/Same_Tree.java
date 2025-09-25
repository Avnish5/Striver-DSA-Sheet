package DAY_18_Binary_Tree_Part_2;

public class Same_Tree {

    /**
     * Time Complexity: O(n)
     * - Each node is visited once in both trees.
     * - n = number of nodes in the smaller tree (if sizes differ, mismatch is found earlier).
     *
     * Space Complexity: O(h)
     * - h = height of the tree (recursive call stack).
     * - Best case (balanced tree): O(log n)
     * - Worst case (skewed tree): O(n)
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;  // Both null → same
        if (p == null || q == null || p.val != q.val) return false; // Structure/value mismatch

        // Recurse on left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }



}
