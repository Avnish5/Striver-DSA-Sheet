package DAY_21_BINARY_SEARCH_TREE_PART_2;

public class Maximum_Sum_BSTIn_Binary_Tree {

    int ansSum = 0; // Global variable to keep track of the maximum sum BST

    // Custom class to hold information about a subtree
    class Custom {
        int sum;       // Sum of values in this subtree if it is a BST
        boolean isBST; // Whether this subtree is a BST
        int leftMax;   // Maximum value in this subtree
        int rightMin;  // Minimum value in this subtree

        public Custom(int sum, boolean isBST, int leftMax, int rightMin) {
            this.sum = sum;
            this.isBST = isBST;
            this.leftMax = leftMax;
            this.rightMin = rightMin;
        }
    }

    // Recursive helper function that returns info about the subtree rooted at `root`
    public Custom helper(TreeNode root) {
        // Base case: empty node is considered a BST with sum = 0
        // For min/max propagation, use extreme values
        if (root == null) {
            return new Custom(0, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        // Recurse on left and right children
        Custom left = helper(root.left);
        Custom right = helper(root.right);

        // Compute min/max for this subtree
        int leftMax = Math.max(root.val, Math.max(left.leftMax, right.leftMax));
        int rightMin = Math.min(root.val, Math.min(left.rightMin, right.rightMin));

        // A subtree is a BST if:
        // 1. Left and right subtrees are BSTs
        // 2. Current node value is greater than max in left subtree
        // 3. Current node value is smaller than min in right subtree
        boolean isBST = left.isBST &&
                right.isBST &&
                root.val > left.leftMax &&
                root.val < right.rightMin;

        // If this subtree is BST, calculate its sum
        int sum = isBST ? left.sum + right.sum + root.val : 0;

        // Update global maximum sum
        ansSum = Math.max(sum, ansSum);

        // Return info about this subtree
        return new Custom(sum, isBST, leftMax, rightMin);
    }

    /**
     * 1. Recursion
     *
     * Time Complexity: O(n)
     *   - We visit each node exactly once.
     *   - At each node we do O(1) work (comparisons and math ops).
     *
     * Space Complexity: O(h)
     *   - h = height of the tree.
     *   - Best / average case (balanced tree): O(log n).
     *   - Worst case (skewed tree): O(n).
     */
    public int maxSumBST1(TreeNode root) {
        helper(root);
        return ansSum;
    }

    public int[] postorder(TreeNode root) {
        // Base case: empty tree is a BST with sum = 0
        // Use extreme values for min/max to simplify comparisons
        if (root == null) {
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0}; //{leftMax, rightMin, 0}
        }

        // Recurse on left and right subtrees
        int[] leftTree = postorder(root.left);
        int[] rightTree = postorder(root.right);

        // If either subtree is not BST, or root violates BST property → not a BST
        if (leftTree == null ||
                rightTree == null ||
                root.val <= leftTree[0] || // root must be greater than left.max
                root.val >= rightTree[1]) { // root must be smaller than right.min
            return null;
        }

        // Subtree sum = root + left sum + right sum
        int sum = root.val + leftTree[2] + rightTree[2];
        ansSum = Math.max(ansSum, sum); // update global max

        // Update min and max for this subtree
        int max = Math.max(root.val, rightTree[0]);
        int min = Math.min(root.val, leftTree[1]);

        // Return {max value, min value, sum}
        return new int[]{max, min, sum};
    }

    /**
     * 2. Post Order Traversal
     * Time Complexity: O(n)
     *   - Each node is visited exactly once.
     *   - Constant-time work is done per node.
     *
     * Space Complexity: O(h)
     *   - h = height of the tree (recursion stack).
     *   - Balanced tree: O(log n)
     *   - Worst case (skewed tree): O(n)
     */
    public int maxSumBST2(TreeNode root) {
        postorder(root);
        return ansSum;
    }

}
