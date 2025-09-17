package DAY_18_Binary_Tree_Part_2;

public class Balanced_Binary_Tree {

    class Helper {
        int height;
        boolean isBal;

        public Helper(int height, boolean isBal) {
            this.height = height;
            this.isBal = isBal;
        }
    }

    public Helper isBalancedHelper(TreeNode root) {
        if (root == null) {
            return new Helper(0, true);
        }

        Helper left = isBalancedHelper(root.left);
        Helper right = isBalancedHelper(root.right);

        int height = 1 + Math.max(left.height, right.height);
        boolean isBal = left.isBal && right.isBal && Math.abs(left.height - right.height) <= 1;

        return new Helper(height, isBal);

    }
    /**
     *
     * Time Complexity: O(n)
     * - We visit every node once to calculate the height and check balance.
     * - For each node, the height calculation is done using results from left and right children,
     *   so no repeated computations occur.
     * - Total work done is proportional to the number of nodes n.
     *
     * Space Complexity: O(h)
     * - The space used is mainly due to the recursion call stack.
     * - h is the height of the tree.
     * - In the worst case (skewed tree), h = n (linear recursion depth).
     * - In the best case (balanced tree), h = log n.
     * - Additional space used in the Helper objects is constant per call.
     */
    public boolean isBalanced(TreeNode root) {
          return isBalancedHelper(root).isBal;
    }
}
