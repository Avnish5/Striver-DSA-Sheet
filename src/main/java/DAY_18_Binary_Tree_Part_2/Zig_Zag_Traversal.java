package DAY_18_Binary_Tree_Part_2;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Zig_Zag_Traversal {

    /**
     *
     * Time Complexity: O(n)
     * - Each node is visited exactly once during the traversal.
     * - n is the total number of nodes in the binary tree.
     *
     * Space Complexity: O(n)
     * - In the worst case, the queue (Deque) holds all nodes of the largest level,
     *   which could be up to n/2 nodes (in a complete binary tree).
     * - Additionally, the result list holds all nodes, which takes O(n) space.
     * - Therefore, total space complexity is O(n).
     */

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // Double-ended queue to allow adding/removing nodes from both ends
        Deque<TreeNode> dq = new LinkedList<>();
        dq.add(root);

        // Result list to store each level's nodes
        List<List<Integer>> ans = new ArrayList<>();

        // Edge case: if root is null, return empty result
        if (root == null) return ans;

        // Boolean flag to control the zigzag direction
        boolean reverse = false;

        // Traverse until there are no more nodes to process
        while (!dq.isEmpty()) {
            int levelSize = dq.size(); // Number of nodes at the current level
            List<Integer> smallAns = new ArrayList<>(); // List for current level's values

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                if (!reverse) {
                    // Left to right: pop from front of deque
                    TreeNode node = dq.removeFirst();
                    smallAns.add(node.val);
                    // Add children in normal order: left then right
                    if (node.left != null) dq.addLast(node.left);
                    if (node.right != null) dq.addLast(node.right);
                } else {
                    // Right to left: pop from back of deque
                    TreeNode node = dq.removeLast();
                    smallAns.add(node.val);
                    // Add children in reverse order: right then left
                    if (node.right != null) dq.addFirst(node.right);
                    if (node.left != null) dq.addFirst(node.left);
                }
            }

            // After processing the level, flip the direction for the next level
            reverse = !reverse;

            // Add the current level's result to the final answer
            ans.add(smallAns);
        }

        return ans;
    }

}
