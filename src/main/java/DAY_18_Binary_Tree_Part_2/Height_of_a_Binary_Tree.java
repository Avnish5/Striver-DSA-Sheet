package DAY_18_Binary_Tree_Part_2;



public class Height_of_a_Binary_Tree {

    /**
     * 1. Recursive Solution - Max Depth of Binary Tree
     *
     * Time Complexity: O(n)
     * - We visit each node exactly once.
     * - At each node, we make two recursive calls (left and right), but each node is visited only once.
     *
     * Space Complexity: O(n)
     * - Due to the recursive call stack.
     * - h = height of the tree.
     * - Best case (balanced tree): O(log n)
     * - Worst case (skewed tree): O(n)
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int left = 1 + maxDepth(root.left);
        int right = 1 + maxDepth(root.right);

        return Math.max(left, right);
    }

}
