package DAY_17_Binary_Tree_Part_1;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Maximum_Width_of_Binary_Tree {
    class CustomNode {
        TreeNode node;
        int idx;

        public CustomNode(TreeNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }

    /**
     * Calculates the maximum width of a binary tree.
     *
     * Time Complexity (TC): O(n)
     *   - Every node is visited exactly once during the BFS traversal.
     * Space Complexity (SC): O(w) where w = maximum width of the tree
     *   - The queue stores nodes level by level. In worst case (perfect binary tree), w ≈ n/2.
     *
     * Note on Integer Overflow:
     *   - In a deep tree, CBT-style indices (2*idx, 2*idx+1) grow exponentially.
     *   - Without normalization, indices can become very large and may overflow 32-bit integers.
     *   - Normalization (idx = customNode.idx - first) shifts indices so leftmost node at each level is 0.
     *     This keeps all calculations safe and avoids overflow, while preserving relative positions.
     */
    public int widthOfBinaryTree(TreeNode root) {
        Queue<CustomNode> q = new LinkedList<>();
        q.add(new CustomNode(root, 0));
        int maxWidth = 0;

        while (!q.isEmpty()) {
            int levelSize = q.size();
            int first = q.peek().idx; // index of leftmost node at this level
            int last = first;         // will be updated to rightmost node's index

            for (int i = 0; i < levelSize; i++) {
                CustomNode customNode = q.remove();
                TreeNode node = customNode.node;

                // Normalize index to prevent integer overflow in deep trees
                // idx = distance from leftmost node at current level
                int idx = customNode.idx - first;

                if (i == levelSize - 1) last = customNode.idx; // track rightmost node

                // Add left child with CBT-style index
                if (node.left != null) {
                    q.add(new CustomNode(node.left, 2 * idx));
                }

                // Add right child with CBT-style index
                if (node.right != null) {
                    q.add(new CustomNode(node.right, 2 * idx + 1));
                }
            }

            // Width of current level = rightmost index - leftmost index + 1
            maxWidth = Math.max(maxWidth, (last - first) + 1);
        }

            return maxWidth;
    }
}
