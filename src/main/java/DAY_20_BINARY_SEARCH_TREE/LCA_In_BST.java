package DAY_20_BINARY_SEARCH_TREE;

public class LCA_In_BST {

    /**
            * Time Complexity: O(H)
 *   - We traverse only one path from root to leaf.
 *   - H = height of tree.
 *   - Worst case (skewed tree): O(N)
 *   - Best case (balanced tree): O(log N)
 *
         * Space Complexity: O(H)
 *   - Due to recursion stack.
            *   - Worst case: O(N)
 *   - Best case: O(log N)
 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;

        int curr = root.val;

        // Both nodes lie in right subtree
        if (curr < p.val && curr < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // Both nodes lie in left subtree
        if (curr > p.val && curr > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // Split point found — this is the LCA
        return root;
    }
}
