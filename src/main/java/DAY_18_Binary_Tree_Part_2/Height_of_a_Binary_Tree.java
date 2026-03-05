package DAY_18_Binary_Tree_Part_2;


import java.util.LinkedList;
import java.util.Queue;

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
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;

        int left = 1 + maxDepth1(root.left);
        int right = 1 + maxDepth1(root.right);

        return Math.max(left, right);
    }

    /**
     * 2. Iterative Solution (BFS - Level Order Traversal) - Max Depth of Binary Tree
     *
     * Time Complexity: O(n)
     * - We visit each node exactly once.
     * - Each node is added to and removed from the queue once.
     * - Therefore, total operations are proportional to number of nodes.
     *
     * Space Complexity: O(n)
     * - Space is used by the queue to store nodes level by level.
     *
     * - Best case (balanced tree): O(n)
     *   -> In a balanced tree, the last level can contain up to n/2 nodes.
     *   -> The queue may store up to n/2 nodes at once.
     *
     * - Worst case (skewed tree): O(n)
     *   -> If the tree is completely skewed, the queue will contain
     *      at most one node at a time.
     *   -> But overall space complexity is still considered O(n).
     */

    public int maxDepth(TreeNode root) {

        // If tree is empty, depth is 0
        if (root == null) return 0;

        // This will store number of levels (depth)
        int level = 0;

        // Queue for BFS (level-order traversal)
        Queue<TreeNode> q = new LinkedList<>();

        // Start by adding root node
        q.add(root);

        // Process level by level
        while (!q.isEmpty()) {

            // Number of nodes at current level
            int levelSize = q.size();

            // Process all nodes of current level
            for (int i = 0; i < levelSize; i++) {

                // Remove current node
                TreeNode node = q.poll();

                // Add left child if exists
                if (node.left != null) {
                    q.add(node.left);
                }

                // Add right child if exists
                if (node.right != null) {
                    q.add(node.right);
                }
            }

            // After finishing one full level, increase depth
            level++;
        }

        // Return total number of levels
        return level;
    }

}
