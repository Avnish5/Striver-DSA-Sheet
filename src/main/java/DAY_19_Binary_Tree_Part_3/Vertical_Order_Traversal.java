package DAY_19_Binary_Tree_Part_3;

import java.util.*;

public class Vertical_Order_Traversal {

    class CustomNode {
        TreeNode node;
        int row;
        int col;

        CustomNode(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    /**
     *
     * Time Complexity: O(n log n)
     * - BFS visits each node once → O(n)
     * - For each column, sorting the list of nodes by (row, value)
     *   costs O(k log k), where k is number of nodes in that column.
     * - Summed across all columns → O(n log n).
     *
     * Space Complexity: O(n)
     * - TreeMap stores all nodes → O(n).
     * - BFS queue → O(n) in worst case.
     * - No extra structures beyond proportional to number of nodes.
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // col -> list of (row, val)
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();

        Queue<CustomNode> q = new LinkedList<>();
        // Start BFS with root at row=0, col=0
        q.add(new CustomNode(root, 0, 0));

        // BFS traversal to assign row & col for each node
        while (!q.isEmpty()) {
            CustomNode cn = q.poll();
            TreeNode node = cn.node;

            // Group nodes by column
            map.computeIfAbsent(cn.col, k -> new ArrayList<>())
                    .add(new int[]{cn.row, node.val});

            // Left child → row+1, col-1
            if (node.left != null) {
                q.add(new CustomNode(node.left, cn.row + 1, cn.col - 1));
            }
            // Right child → row+1, col+1
            if (node.right != null) {
                q.add(new CustomNode(node.right, cn.row + 1, cn.col + 1));
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        // For each column (in sorted order by TreeMap)
        for (List<int[]> list : map.values()) {
            // Sort nodes in this column:
            // 1. By row (top to bottom)
            // 2. By value if same row
            Collections.sort(list, (a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1]; // same row → smaller value first
                }
                return a[0] - b[0];     // smaller row first
            });

            // Extract just values into final answer
            List<Integer> colList = new ArrayList<>();
            for (int[] pair : list) {
                colList.add(pair[1]); // pair[1] = node value
            }
            ans.add(colList);
        }

        return ans;
    }


}
