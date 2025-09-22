package DAY_18_Binary_Tree_Part_2;

import java.util.ArrayList;

public class Boundary_Traversal_Of_Binary_Tree {

    public boolean isLeaf( TreeNode root){
        return root.left == null && root.right ==null;
    }

    public void leftNodes(TreeNode node, ArrayList<Integer> res) {
        if (node == null || isLeaf(node)) return ;

        res.add(node.val);
        if (node.left != null) {
            leftNodes(node.left, res);
        } else {
            leftNodes(node.right, res);
        }


    }

    public void rightNodes(TreeNode node, ArrayList<Integer> res) {
        if (node == null || isLeaf(node)) return;

        if (node.right != null) {
            rightNodes(node.right, res);
        } else {
            rightNodes(node.left, res);
        }

        res.add(node.val);
    }

    public void leafNodes(TreeNode node, ArrayList<Integer> res) {
        if (node == null) return ;

        if (isLeaf(node)) res.add(node.val);
        leafNodes(node.left, res);
        leafNodes(node.right, res);
    }

    /**
     * 1. Recursion
     *
     * Time Complexity: O(n)
     * - Each node is visited at most once.
     * - Left boundary: O(h)
     * - Leaf nodes: O(n)
     * - Right boundary: O(h)
     * - Total ≈ O(n)
     *
     * Space Complexity: O(n)
     * - Result list stores up to n nodes in worst case.
     * - Recursive call stack: O(h)
     *   - Balanced tree: O(log n)
     *   - Skewed tree: O(n)
     */

    ArrayList<Integer> boundaryTraversal1(TreeNode node) {
        ArrayList<Integer> ans = new ArrayList<>();

        if (!isLeaf(node)) ans.add(node.val);

        leftNodes(node.left, ans);;
        leafNodes(node, ans);
        rightNodes(node.right, ans);
         return ans;
    }
}
