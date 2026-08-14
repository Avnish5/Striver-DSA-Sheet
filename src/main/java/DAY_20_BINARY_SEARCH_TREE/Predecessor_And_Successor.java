package DAY_20_BINARY_SEARCH_TREE;

import java.util.ArrayList;

public class Predecessor_And_Successor {
    /**
     * Finds the inorder successor of the given key in a Binary Search Tree (BST).
     *
     * Inorder Successor:
     * The node with the smallest value greater than the given key.
     *
     * Approach:
     * - If key is smaller than current node's value, current node can be a potential successor.
     *   Move left to find a smaller valid successor.
     * - Otherwise, move right to look for larger values.
     *
     * Time Complexity: O(H), where H is the height of the BST.
     * Space Complexity: O(1)
     *
     * @param root Root node of the BST
     * @param key  Target key
     * @return Inorder successor node, or null if no successor exists
     */
    public Node findSuccessor(Node root, int key) {
        Node ans = null;

        while (root != null) {
            if (key < root.val) {
                // Current node can be a successor
                ans = root;
                root = root.left;
            } else {
                // Successor must be in the right subtree
                root = root.right;
            }
        }

        return ans;
    }

    /**
     * Finds the inorder predecessor of the given key in a Binary Search Tree (BST).
     *
     * Inorder Predecessor:
     * The node with the largest value smaller than the given key.
     *
     * Approach:
     * - If key is greater than current node's value, current node can be a potential predecessor.
     *   Move right to find a larger valid predecessor.
     * - Otherwise, move left to look for smaller values.
     *
     * Time Complexity: O(H), where H is the height of the BST.
     * Space Complexity: O(1)
     *
     * @param root Root node of the BST
     * @param key  Target key
     * @return Inorder predecessor node, or null if no predecessor exists
     */
    public Node findPre(Node root, int key) {
        Node ans = null;

        while (root != null) {
            if (key > root.val) {
                // Current node can be a predecessor
                ans = root;
                root = root.right;
            } else {
                // Predecessor must be in the left subtree
                root = root.left;
            }
        }

        return ans;
    }

    /**
     * Finds both inorder predecessor and inorder successor of a given key in a BST.
     *
     * Result:
     * - Index 0 -> Predecessor
     * - Index 1 -> Successor
     *
     * @param root Root node of the BST
     * @param key  Target key
     * @return ArrayList containing predecessor and successor nodes
     */
    public ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> ans = new ArrayList<>();

        // Find predecessor
        ans.add(findPre(root, key));

        // Find successor
        ans.add(findSuccessor(root, key));

        return ans;
    }
}
