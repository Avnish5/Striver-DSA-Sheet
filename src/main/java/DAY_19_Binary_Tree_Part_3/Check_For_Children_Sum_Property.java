package DAY_19_Binary_Tree_Part_3;

public class Check_For_Children_Sum_Property {

    class CustomNode {
        int val;
        boolean isProperty;

        public CustomNode(int val, boolean isProperty) {
            this.val = val;
            this.isProperty = isProperty;
        }
    }

    public CustomNode isSumPropertyHelper(TreeNode root) {
        if (root == null) {
            return new CustomNode(0, true);
        }

        if (root.left == null && root.right == null) {
            return new CustomNode(root.val, true);
        }

        CustomNode left = isSumPropertyHelper(root.left);
        CustomNode right = isSumPropertyHelper(root.right);

        boolean isProperty = (left.val + right.val == root.val) && (left.isProperty && right.isProperty);

        return new CustomNode(root.val, isProperty);
    }

    /**
     * 1. Using Recursion with custom node.
     *
     * Children Sum Property: For every node, the value of the node should be equal to
     * the sum of the values of its left and right children (if they exist).
     *
     * Time Complexity: O(n)
     * - Each node is visited exactly once.
     * - At each node, constant work is done (checking sum and recursive calls).
     *
     * Space Complexity: O(h)
     * - h = height of the tree, due to recursive call stack.
     * - Best case (balanced tree): O(log n)
     * - Worst case (completely skewed tree): O(n)
     *
     */
    public boolean isSumProperty1(TreeNode root) {
      return isSumPropertyHelper(root).isProperty;

    }

/**
 * 2. Using Recursion
 *
 * Children Sum Property: For every node, the value of the node should be equal to
 * the sum of the values of its left and right children (if they exist).
 *
 * Time Complexity: O(n)
 * - Each node is visited exactly once.
 * - At each node, constant work is done (checking sum and recursive calls).
 *
 * Space Complexity: O(h)
 * - h = height of the tree, due to recursive call stack.
 * - Best case (balanced tree): O(log n)
 * - Worst case (completely skewed tree): O(n)
 *
 */
    public boolean isSumProperty2(TreeNode root) {
        //  code here
        // Base case: null or leaf
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        // Calculate sum of children
        int leftVal = (root.left != null) ? root.left.val : 0;
        int rightVal = (root.right != null) ? root.right.val : 0;

        // Check property at current node + recurse on children
        return (root.val == leftVal + rightVal)
                && isSumProperty2(root.left)
                && isSumProperty2(root.right);
    }
}
