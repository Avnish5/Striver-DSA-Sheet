package DAY_19_Binary_Tree_Part_3;

public class Convert_TO_CSP {

    /**
     * Time Complexity: O(n)
     * - Each node is visited once.
     * - Constant work per node (sum calculation, value adjustments).
     *
     * Space Complexity: O(h)
     * - h = height of the tree (recursion stack).
     * - Best case (balanced tree): O(log n)
     * - Worst case (skewed tree): O(n)
     */
    public void convertCSP(TreeNode root) {
        if (root == null) return;

        // Step 1: First recursively convert left and right subtrees
        convertCSP(root.left);
        convertCSP(root.right);

        // Step 2: Calculate sum of children
        int leftVal = (root.left != null) ? root.left.val : 0;
        int rightVal = (root.right != null) ? root.right.val : 0;
        int childSum = leftVal + rightVal;

        // Step 3: Adjust current node or push value down to children
        if (childSum >= root.val) {
            root.val = childSum;
        } else {
            // If node value is greater, push it down
            if (root.left != null) root.left.val = root.val;
            if (root.right != null) root.right.val = root.val;
        }

        // Step 4: After updating children, fix current node again
        // because children's values might have changed
        int newLeftVal = (root.left != null) ? root.left.val : 0;
        int newRightVal = (root.right != null) ? root.right.val : 0;
        if (root.left != null || root.right != null) {
            root.val = newLeftVal + newRightVal;
        }
    }
}
