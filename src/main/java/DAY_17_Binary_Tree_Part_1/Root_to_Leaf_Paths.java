package DAY_17_Binary_Tree_Part_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Root_to_Leaf_Paths {

    public void traverse(TreeNode node, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
       if (node == null) return;

       path.add(node.val);

       if (node.left == null && node.right == null) {
           result.add(new ArrayList<>(path));
       } else {
           traverse(node.left, path, result);
           traverse(node.right, path, result);
       }

       path.removeLast();
    }

    /**
     * Time Complexity:
     * ----------------
     * - Each node is visited once → O(N), where N = number of nodes.
     * - At each leaf node, we copy the current path (of length ≤ H).
     *   If there are L leaf nodes, this copying costs O(L * H).
     *
     * Overall: O(N + L * H)
     * - Skewed tree (like a linked list): H = N, L = 1 → O(N).
     * - Balanced tree: H ≈ logN, L ≈ N/2 → O(N logN).
     *
     *
     * Space Complexity:
     * -----------------
     * - Recursion stack: O(H), where H = tree height.
     * - Current path storage: O(H).
     * - Result storage: O(L * H) in worst case (all root-to-leaf paths).
     *
     * Overall: O(H + L * H)
     * - Skewed tree: O(N).
     * - Balanced tree: O(N logN).
     */
    public ArrayList<ArrayList<Integer>> Paths(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        traverse(root, list, result);
        return result;

    }
}
