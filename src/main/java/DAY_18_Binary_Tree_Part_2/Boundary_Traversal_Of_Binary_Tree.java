package DAY_18_Binary_Tree_Part_2;

import java.util.ArrayList;
import java.util.Collections;

public class Boundary_Traversal_Of_Binary_Tree {
    // Check if a node is a leaf node
    // A leaf node has no left and right child
    public boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    // Add left boundary nodes (excluding leaf nodes)
    // We always try to go left first, if not possible then go right
    public void addLeftBoundary(TreeNode root, ArrayList<Integer> res) {
        TreeNode curr = root.left;

        while (curr != null) {

            // Add only non-leaf nodes
            if (!isLeaf(curr)) {
                res.add(curr.val);
            }

            // Prefer left child, otherwise go right
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    // Add right boundary nodes (excluding leaf nodes)
    // Similar to left boundary but traverse from bottom to top
    public void addRightBoundary(TreeNode root, ArrayList<Integer> res) {
        TreeNode curr = root.right;

        ArrayList<Integer> temp = new ArrayList<>();

        while (curr != null) {

            // Add only non-leaf nodes
            if (!isLeaf(curr)) {
                temp.add(curr.val);
            }

            // Prefer right child, otherwise go left
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        // Add right boundary in reverse order
        for (int i = temp.size() - 1; i >= 0; i--) {
            res.add(temp.get(i));
        }
    }

    // Add all leaf nodes (in-order traversal)
    public void addLeafs(TreeNode root, ArrayList<Integer> res) {

        if (root == null) return;

        // If node is leaf, add to result
        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }

        // Recurse on left and right subtree
        if (root.left != null) addLeafs(root.left, res);
        if (root.right != null) addLeafs(root.right, res);
    }

    // Main function for boundary traversal
    ArrayList<Integer> boundaryTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        // Add root only if it is not a leaf
        if (!isLeaf(root)) {
            res.add(root.val);
        }

        // Step 1: Add left boundary (excluding leaf nodes)
        addLeftBoundary(root, res);

        // Step 2: Add all leaf nodes
        addLeafs(root, res);

        // Step 3: Add right boundary (excluding leaf nodes, reversed)
        addRightBoundary(root, res);

        return res;
    }
}
