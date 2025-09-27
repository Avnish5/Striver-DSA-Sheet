package DAY_20_BINARY_SEARCH_TREE;

public class Search_Given_Key_in_BST {

    /**
     * Time Complexity: O(h)
     *   - In the worst case, we traverse from the root to the leaf.
     *   - h = height of the BST.
     *   - For a balanced BST: O(log n)
     *   - For a skewed BST (like a linked list): O(n)
     *
     * Space Complexity: O(h)
     *   - Due to recursive call stack depth.
     *   - For a balanced BST: O(log n)
     *   - For a skewed BST: O(n)
     */
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root.val == val) return root;

        return val > root.val ? searchBST1(root.right, val) : searchBST1(root.left, val);
    }

    /**
     * Time Complexity: O(h)
     *   - In the worst case, we traverse from the root to the leaf.
     *   - h = height of the BST.
     *   - For a balanced BST: O(log n)
     *   - For a skewed BST (like a linked list): O(n)
     *
     * Space Complexity: O(1)
     *   - Iterative approach uses constant extra space (just a few variables).
     *   - No recursive call stack is needed.
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) return root;     // found
            else if (val > root.val) root = root.right;  // move right
            else root = root.left;                        // move left
        }
        return null; // not found
    }
}
