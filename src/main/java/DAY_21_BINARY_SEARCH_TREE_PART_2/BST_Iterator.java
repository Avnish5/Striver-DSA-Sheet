package DAY_21_BINARY_SEARCH_TREE_PART_2;

import java.util.Stack;

import java.util.Stack;

/**
 * BSTIterator
 *
 * Design an iterator over a Binary Search Tree (BST)
 * that returns elements in ascending order.
 *
 * We simulate inorder traversal (Left → Root → Right)
 * using a stack instead of generating the full inorder list.
 *
 * Key Idea:
 *  - Always keep the next smallest element on top of the stack.
 *  - Push all left descendants during initialization.
 *  - When calling next():
 *      • Pop the top element
 *      • If it has a right subtree,
 *        push all left descendants of that subtree.
 *
 * This ensures amortized O(1) time per next() call.
 *
 * -------------------------------------------------------
 *
 * Time Complexity:
 *
 * Constructor: O(H)
 *   - Push all left nodes from root (H = height of tree).
 *
 * next(): Amortized O(1)
 *   - Each node is pushed once and popped once.
 *   - Total work across N calls = O(N).
 *
 * hasNext(): O(1)
 *
 * Overall traversal: O(N)
 *
 * -------------------------------------------------------
 *
 * Space Complexity: O(H)
 *   - Stack stores at most H nodes.
 *   - Worst case (skewed tree): O(N)
 *   - Balanced tree: O(log N)
 *
 */
class BST_Iterator {

    private Stack<TreeNode> stack;

    // Constructor
    public BST_Iterator(TreeNode root) {
        stack = new Stack<>();
        pushLeft(root);
    }

    /**
     * Push all left descendants of given node
     * onto the stack.
     */
    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /**
     * Returns the next smallest element in BST.
     */
    public int next() {
        TreeNode node = stack.pop();
        int result = node.val;

        // If right subtree exists,
        // process its left boundary.
        if (node.right != null) {
            pushLeft(node.right);
        }

        return result;
    }

    /**
     * Returns true if there are more elements to process.
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
