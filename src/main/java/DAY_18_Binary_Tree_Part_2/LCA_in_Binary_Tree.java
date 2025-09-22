package DAY_18_Binary_Tree_Part_2;

public class LCA_in_Binary_Tree {

    /**
     * 2. Recursive Solution - Lowest Common Ancestor (LCA) of a Binary Tree
     *
     * Time Complexity: O(n)
     * - Each node in the tree is visited at most once.
     * - At each node, we make recursive calls for the left and right subtrees.
     *
     * Space Complexity: O(h)
     * - Due to the recursive call stack.
     * - h = height of the tree.
     * - Best case (balanced tree): O(log n)
     * - Worst case (skewed tree): O(n)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case:
        // If root is null, or root matches p, or root matches q,
        // then root itself is the LCA (or null if tree is empty).
        if (root == null || root == p || root == q) {
            return root;
        }

        // Recurse on the left subtree to search for p and q
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // Recurse on the right subtree to search for p and q
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both left and right are non-null,
        // it means p and q are found in different subtrees,
        // so root is their lowest common ancestor.
        if (left != null && right != null) {
            return root;
        }

        // Otherwise, return the non-null child (either left or right).
        // This bubbles up the found node until the LCA is determined.
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }

        // If both sides return null, then neither p nor q exist in this subtree.
        return null;
    }

}
