package DAY_20_BINARY_SEARCH_TREE;

public class Construct_A_BST_From_A_Preorder_Traversal {

    private TreeNode helper1(int[] preorder, int s, int e) {
        if (s < 0) return null; // invalid index case
        if (s > e) return null; // no elements in this range

        // First element in this range is the root
        TreeNode root = new TreeNode(preorder[s]);
        int t = root.val;

        // Find the first element greater than root -> start of right subtree
        int leftEnd = e + 1;
        for (int i = s; i <= e; i++) {
            if (preorder[i] > t) {
                leftEnd = i;
                break;
            }
        }

        // Left subtree = elements between (s+1, leftEnd-1)
        root.left = helper1(preorder, s + 1, leftEnd - 1);

        // Right subtree = elements between (leftEnd, e)
        root.right = helper1(preorder, leftEnd, e);

        return root;
    }

    /**
     * 1. Recursion
     *
     * Time Complexity: O(n^2)
     *   - For each recursive call, we may scan the subarray (worst case).
     *   - Example worst case: preorder is sorted ascending/descending (skewed tree).
     *
     * Space Complexity: O(n)
     *   - Recursion stack in the worst case (skewed tree).
     *   - In the best case (balanced tree), stack depth is O(log n), but worst-case O(n).
     */
    public TreeNode bstFromPreorder1(int[] preorder) {
        return helper1(preorder, 0, preorder.length - 1);
    }

    private int i = 0; // shared index across recursive calls

    private TreeNode helper(int ub, int[] preorder) {
        // Base case: out of range OR element too large for this subtree
        if (i >= preorder.length || preorder[i] > ub) return null;

        // Create root node from preorder[i], then move index forward
        TreeNode root = new TreeNode(preorder[i++]);

        // Build left subtree (all values smaller than root)
        root.left = helper(root.val, preorder);

        // Build right subtree (values up to parent's upper bound)
        root.right = helper(ub, preorder);

        return root;
    }

    /**
     * 2.With better TC
     *
     * Time Complexity: O(n)
     *   - Each element is processed exactly once.
     *
     * Space Complexity: O(n)
     *   - Due to recursion stack in the worst case (skewed tree).
     *   - Best case (balanced tree): O(log n).
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        return helper(Integer.MAX_VALUE, preorder);
    }

}
