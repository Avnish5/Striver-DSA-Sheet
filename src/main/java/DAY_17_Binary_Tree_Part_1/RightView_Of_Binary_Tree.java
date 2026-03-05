package DAY_17_Binary_Tree_Part_1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightView_Of_Binary_Tree {

    /**
     Time Complexity: O(n)
     - We visit each node exactly once during BFS traversal.
     - n = number of nodes in the tree.

     Space Complexity: O(w)
     - w = maximum width of the tree (maximum number of nodes in any level).
     - This is the maximum size the queue can reach at any point.
     */
    ArrayList<Integer> rightView1(TreeNode root) {
        // List to store the left view nodes
        ArrayList<Integer> result = new ArrayList<>();

        // If tree is empty, return empty list
        if (root == null) return result;

        // Queue for BFS (level order traversal)
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        // Perform BFS
        while (!q.isEmpty()) {
            int levelSize = q.size();  // Number of nodes at current level

            // Process all nodes at this level
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = q.remove();

                // The first node of this level is part of the left view
                if (i == levelSize - 1) {
                    result.add(curr.val);
                }

                // Add children for next level
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }

        return result;
    }

    public void helper(TreeNode root, int level, List<Integer> ans) {

        // Base case: if node is null, return
        if (root == null) {
            return;
        }

        // If we are visiting this level for the first time,
        // add the current node value (rightmost node of that level)
        if (ans.size() == level) {
            ans.add(root.val);
        }

        // Traverse right subtree first
        helper(root.right, level + 1, ans);

        // Then traverse left subtree
        helper(root.left, level + 1, ans);
    }

    /**
     * Time Complexity: O(n)
     * - Each node is visited exactly once.
     *
     * Space Complexity: O(h)
     * - h = height of the tree (recursion stack space).
     * - In worst case (skewed tree), h = n.
     * - In balanced tree, h = log n.
     */
    public List<Integer> rightView2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        // Start DFS from level 0
        helper(root, 0, ans);

        return ans;
    }
}
