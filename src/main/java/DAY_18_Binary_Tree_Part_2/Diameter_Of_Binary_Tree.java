package DAY_18_Binary_Tree_Part_2;

public class Diameter_Of_Binary_Tree {

    class Pair {
        int diameter;
        int height;

        public Pair(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }

    public Pair diameterOfBinaryTreeHelper(TreeNode root) {
        if (root == null) {
            return new Pair(0 ,0);
        }

        Pair left = diameterOfBinaryTreeHelper(root.left);
        Pair right = diameterOfBinaryTreeHelper(root.right);

        int height = 1 + Math.max(left.height, right.height);
        int diameter = Math.max(left.height + right.height , Math.max(left.diameter, right.diameter));

        return new Pair(diameter, height);
    }

    /**
     *
     * Time Complexity: O(n)
     * - The function visits each node exactly once.
     * - At each node, it computes the height and diameter using the results from left and right subtrees.
     * - No repeated calculations, so total work is proportional to number of nodes n.
     *
     * Space Complexity: O(n)
     * - The space is primarily used by the recursion call stack.
     * - h is the height of the tree.
     * - Worst case (skewed tree): h = n, so space is O(n).
     * - Best case (balanced tree): h = log n, so space is O(log n).
     * - Additional space used by Pair objects is constant per call.
     */
    public int diameterOfBinaryTree(TreeNode root) {
         return diameterOfBinaryTreeHelper(root).diameter;
    }
}
