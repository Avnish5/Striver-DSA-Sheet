package DAY_21_BINARY_SEARCH_TREE_PART_2;

import java.util.ArrayList;
import java.util.Stack;

public class Find_K_th_Smallest_Element_In_BST {

    public void inorderTraversal(ArrayList<Integer> inorder, TreeNode root) {
        if (root == null) return;
        inorderTraversal(inorder, root.left);
        inorder.add(root.val);
        inorderTraversal(inorder, root.right);
    }

    public int kthSmallest1(TreeNode root, int k) {
        ArrayList<Integer> inorder = new ArrayList<>();
        inorderTraversal(inorder, root);

        for (int i = 0; i < inorder.size(); i++) {
            if (i == k - 1) return inorder.get(i);
        }

        return -1;
    }

    // Counter to track the number of nodes visited during traversal
    int count = 0;

    // Stores the reference to the kth smallest node
    TreeNode ans = null;

    private void helper(TreeNode root, int k) {
        // Base case: if node is null, return
        if (root == null) return;

        // Traverse left subtree first (smaller values)
        helper(root.left, k);

        // Process current node: increment count
        count++;

        // If we've reached the kth node, store it and return
        if (count == k) {
            ans = root;
            return;
        }

        // Traverse right subtree (larger values)
        helper(root.right, k);
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
    public int kthSmallest2(TreeNode root, int k) {
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
    public int kthSmallest3(TreeNode root, int k) {
        // Stack to simulate the call stack of recursive in-order traversal
        Stack<TreeNode> s = new Stack<>();
        TreeNode curr = root;

        // Continue until stack is empty AND current node is null
        while (!s.isEmpty() || curr != null) {

            // Inner loop: Go to the leftmost node, pushing all nodes along the path
            while (curr != null) {
                s.add(curr);           // Push current node to stack
                curr = curr.left;      // Move to left child
            }

            // Pop the leftmost unvisited node (this is the next smallest element)
            curr = s.pop();

            // Decrement k as we've visited one more node in sorted order
            k--;

            // If k reaches 0, we've found the kth smallest element
            if (k == 0) return curr.val;

            // Move to right subtree to continue in-order traversal
            curr = curr.right;
        }

        // Return -1 if k is larger than the number of nodes (edge case)
        return -1;
    }
}
