package DAY_17_Binary_Tree_Part_1;

import java.util.ArrayList;
import java.util.LinkedList;
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
    ArrayList<Integer> rightView(TreeNode root) {
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
}
