package DAY_21_BINARY_SEARCH_TREE_PART_2;

public class Ceil_In_BST {

    private int helper(TreeNode root, int x, int pa) {
        if (root == null) return pa; // base case: return the best candidate so far

        if (root.val == x) {
            // Case 1: exact match → x itself is the ceil
            return x;
        }

        if (root.val > x) {
            // Case 2: current node is a valid candidate (≥ x) → update pa
            pa = root.val;
            // but check left subtree for a smaller valid ceil
            return helper(root.left, x, pa);
        }

        if (root.val < x) {
            // Case 3: current node is too small → move to right subtree
            return helper(root.right, x, pa);
        }

        return pa; // fallback (shouldn't be reached due to above conditions)
    }

    /**
     * Time Complexity:
     * - O(h), where h = height of the BST.
     * - Worst case (skewed tree): O(n).
     * - Best/average case (balanced tree): O(log n).
     *
     * Space Complexity:
     * - O(h) due to recursion stack.
     * - Worst case: O(n), Best case: O(log n).
     */

    int findCeil1(TreeNode root, int x) {
        int pa = -1; // holds the best candidate (potential ceil value)
        return helper(root, x, pa);
    }

    /**
     * Time Complexity: O(h)
     * - O(h), where h = height of the BST.
     * - In the worst case (skewed tree), h = O(n).
     * - In the best case (balanced tree), h = O(log n).
     *
     * Space Complexity:O(1)
     */
    int findCeil2(TreeNode root, int x) {
        int pa = -1; // holds the best candidate (potential ceil value)
        while (root != null) {
            if(root.val == x) return x;

            if (root.val > x) {
                pa = root.val;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return pa;

    }
}
