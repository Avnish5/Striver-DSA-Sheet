package DAY_18_Binary_Tree_Part_2;

import java.util.ArrayList;
import java.util.Collections;

public class Boundary_Traversal_Of_Binary_Tree {
    // Utility to check if a node is a leaf
    public boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    // Collect the left boundary (top → bottom, excluding leaves)
    public void leftNodes(TreeNode node, ArrayList<Integer> res) {
        if (node == null || isLeaf(node)) return;

        TreeNode curr = node;
        while (curr != null) {
            if (!isLeaf(curr)) {
                res.add(curr.val); // add non-leaf node
            }

            // Prefer left child, otherwise go right
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    // Collect the right boundary (bottom → top, excluding leaves)
    public void rightNodes(TreeNode node, ArrayList<Integer> res) {
        if (node == null || isLeaf(node)) return;

        TreeNode curr = node;
        ArrayList<Integer> temp = new ArrayList<>();

        while (curr != null) {
            if (!isLeaf(curr)) {
                temp.add(curr.val); // collect in temp
            }

            // Prefer right child, otherwise go left
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        // Reverse so that it becomes bottom → top
        Collections.reverse(temp);
        res.addAll(temp);
    }

    // Collect all leaf nodes (in-order traversal)
    public void leafNodes(TreeNode node, ArrayList<Integer> res) {
        if (node == null) return;

        if (isLeaf(node)) {
            res.add(node.val);
        }
        leafNodes(node.left, res);
        leafNodes(node.right, res);
    }

    /**
     * Boundary Traversal of Binary Tree
     *
     * Time Complexity: O(n)
     * - Each node is processed at most once (in leftNodes, rightNodes, or leafNodes).
     * - Reversal of right boundary costs O(h), where h is the tree height.
     * - Total: O(n + h) = O(n).
     *
     * Space Complexity: O(n)
     * - Result list stores up to all nodes → O(n).
     * - Recursion stack in leafNodes: O(h).
     * - Temporary list for right boundary: O(h).
     * - Worst case (skewed tree): O(n).
     * - Best case (balanced tree): O(log n).
     */
    ArrayList<Integer> boundaryTraversal(TreeNode node) {
        ArrayList<Integer> ans = new ArrayList<>();

        // Add root only if it's not a leaf
        if (!isLeaf(node)) ans.add(node.val);

        // Step 1: left boundary
        leftNodes(node.left, ans);

        // Step 2: leaf nodes
        leafNodes(node, ans);

        // Step 3: right boundary
        rightNodes(node.right, ans);

        return ans;
    }
}
