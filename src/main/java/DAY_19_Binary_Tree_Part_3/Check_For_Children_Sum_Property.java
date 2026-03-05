package DAY_19_Binary_Tree_Part_3;

import java.util.LinkedList;
import java.util.Queue;

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

    /**
     * 3. Using BFS
     *
     * Time Complexity: O(n)
     * - Each node is visited exactly once.
     * - For every node, we compute the sum of its children in O(1).
     * - Total work is proportional to number of nodes n.
     *
     * Space Complexity: O(n)
     * - In the worst case, the queue may store all nodes at the largest level.
     * - In a complete binary tree, that can be up to n/2 nodes.
     * - Therefore, overall space complexity is O(n).
     */

    public boolean isSumProperty3(TreeNode root) {

        // If tree is empty OR single node tree → property holds
        if (root == null || (root.left == null && root.right == null))
            return true;

        // Queue for BFS traversal
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        // Traverse level by level
        while (!q.isEmpty()) {

            TreeNode node = q.poll();

            int sum = 0;

            // Skip leaf nodes (no need to check them)
            if (node.left == null && node.right == null) {
                continue;
            }

            // Add left child value if exists
            if (node.left != null) {
                sum += node.left.val;
            }

            // Add right child value if exists
            if (node.right != null) {
                sum += node.right.val;
            }

            // If current node does not satisfy child sum property → return false
            if (node.val != sum) {
                return false;
            }

            // Add children to queue for further checking
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }

        // If all nodes satisfy property
        return true;
    }
}
