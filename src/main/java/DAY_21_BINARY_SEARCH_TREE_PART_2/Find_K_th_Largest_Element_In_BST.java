package DAY_21_BINARY_SEARCH_TREE_PART_2;

import java.util.Stack;

public class Find_K_th_Largest_Element_In_BST {
    int count  = 0;
    TreeNode ans = null;

    private void helper(TreeNode root, int k) {
        if (root == null) return;
        helper(root.right, k);

        count++;
        if (count == k) {
            ans = root;
            return;
        }

        helper(root.left, k);
    }

    /**
     * 1. Using Recursion
     *
     * Time Complexity: O(H + k), where H is the height of the tree
     *   - In worst case (skewed tree): O(n)
     *   - In best case (balanced tree): O(log n + k)
     *
     * Space Complexity: O(H) for the recursion stack
     *   - In worst case (skewed tree): O(n)
     *   - In best case (balanced tree): O(log n)
     */
    public int kthLargest1(TreeNode root, int k) {
       helper(root, k);
       return ans.val;
    }

    /**
     * 2. Using Stack
     *
     * Approach: Uses a stack to simulate in-order traversal iteratively (without recursion).
     * Visits nodes in ascending order and decrements k until we reach the kth smallest element.
     *
     * Time Complexity: O(H + k), where H is the height of the tree
     *   - In worst case (skewed tree): O(n)
     *   - In best case (balanced tree): O(log n + k)
     *   - We traverse down to the leftmost node (O(H)) and then visit k nodes (O(k))
     *
     * Space Complexity: O(H) for the stack
     *   - In worst case (skewed tree): O(n)
     *   - In best case (balanced tree): O(log n)
     *   - Stack stores nodes along the path from root to current node
     */
    public int kthLargest2(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode curr = root;

        while (!s.isEmpty() || curr != null) {
            while (curr != null) {
                s.add(curr);
                curr = curr.right;
            }

            curr = s.pop();
            k--;
            if (k == 0) return curr.val;
            curr = curr.left;
        }
        return -1;
    }

}
