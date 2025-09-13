package DAY_17_Binary_Tree_Part_1;

import java.util.*;

public class TopView_Of_Binary_Tree {

    class CustomNode {
        TreeNode node;
        int col;
        CustomNode(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    /**
     *
     * Time Complexity: O(n log n)
     * - BFS visits each node once → O(n)
     * - TreeMap insertion/search for each node → O(log n)
     *
     * Space Complexity: O(n)
     * - Queue stores up to width of tree nodes
     * - TreeMap stores at most one value per column → O(n)
     */
    ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result; // handle empty tree

        // Queue stores nodes along with their column
        Queue<CustomNode> q = new LinkedList<>();
        // TreeMap stores the first node value for each column in sorted order
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        q.add(new CustomNode(root, 0)); // root is at column 0

        while (!q.isEmpty()) {
            CustomNode customNode = q.remove();
            TreeNode node = customNode.node;
            int col = customNode.col;

            // If this column is seen first time, it's part of top view
            if (!treeMap.containsKey(col)) {
                treeMap.put(col, node.val);
            }

            // Add left child with column-1
            if (node.left != null) {
                q.add(new CustomNode(node.left, col - 1));
            }

            // Add right child with column+1
            if (node.right != null) {
                q.add(new CustomNode(node.right, col + 1));
            }
        }

        // Extract top view nodes from leftmost to rightmost column
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

}
