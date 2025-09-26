package DAY_19_Binary_Tree_Part_3;

public class Maximum_Path_Sum {
    int max_sum = Integer.MIN_VALUE;

    /**
     * Time Complexity: O(N)
     * ---------------------
     * - Each node is visited exactly once.
     * - At each node we do constant-time operations.
     * - Therefore, total = O(N), where N = number of nodes.
     *
     * Space Complexity: O(H)
     * ----------------------
     * - Recursion stack depends on the height of the tree.
     * - Worst case (skewed tree): O(N).
     * - Best/average case (balanced tree): O(log N).
     * - No extra space other than recursion stack.
     */
    public int solve(TreeNode root) {
        if (root == null) return 0;

        int left = solve(root.left);
        int right = solve(root.right);

        int niche_hi_milgya_answer = left + right + root.val;
        int root_hi_ascha_ha = root.val;
        int koi_ek_ascha = Math.max(left, right) + root.val;
        max_sum = Math.max(Math.max(max_sum, niche_hi_milgya_answer), Math.max(root_hi_ascha_ha, koi_ek_ascha));

        return Math.max(root_hi_ascha_ha, koi_ek_ascha);

    }

    public int maxPathSum(TreeNode root) {
       solve(root);
       return max_sum;
    }
}
