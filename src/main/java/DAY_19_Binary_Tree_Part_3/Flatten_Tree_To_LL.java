package DAY_19_Binary_Tree_Part_3;

public class Flatten_Tree_To_LL {

    public TreeNode helper(TreeNode root) {

        // Base case:
        // If the node is null OR it is already a leaf node,
        // return the node itself as the tail.
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        // Recursively flatten the left subtree
        // and get the tail of the flattened left subtree.
        TreeNode leftTail = helper(root.left);

        // Recursively flatten the right subtree
        // and get the tail of the flattened right subtree.
        TreeNode rightTail = helper(root.right);

        // If a left subtree exists,
        // move it to the right side of the current node.
        if (leftTail != null) {

            // Store the original right subtree
            TreeNode temp = root.right;

            // Move the flattened left subtree to the right
            root.right = root.left;

            // Set left child to null as required
            root.left = null;

            // Attach the original right subtree
            // to the end of the moved left subtree
            leftTail.right = temp;
        }

        // Return the tail of the flattened tree.
        // If right subtree exists, its tail is the overall tail.
        // Otherwise, the left subtree tail becomes the tail.
        return rightTail != null ? rightTail : leftTail;
    }

    public void flatten(TreeNode root) {
        // Start flattening the binary tree from the root
        helper(root);
    }
}
