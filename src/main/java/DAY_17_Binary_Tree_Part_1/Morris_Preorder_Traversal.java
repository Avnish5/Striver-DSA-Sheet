package DAY_17_Binary_Tree_Part_1;

import java.util.ArrayList;
import java.util.List;

public class Morris_Preorder_Traversal {

    /**
     Time Complexity (TC): O(n)
    - Each node is visited at most twice (once when creating thread, once when removing).
    - Total O(n).

     Space Complexity (SC): O(1)
    - No recursion, no stack.
    - Only pointers + result list.

      This is Morris Preorder Traversal.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                // Case 1: No left child → visit & move right
                result.add(curr.val);
                curr = curr.right;
            } else {
                // Case 2: Left child exists → find inorder predecessor (IP)
                TreeNode IP = curr.left;
                while (IP.right != null && IP.right != curr) {
                    IP = IP.right;
                }

                if (IP.right == null) {
                    // First time visiting → preorder = visit before going left
                    result.add(curr.val);

                    // Create thread & move left
                    IP.right = curr;
                    curr = curr.left;
                } else {
                    // Thread exists → left subtree already done
                    IP.right = null; // Remove thread
                    curr = curr.right; // Move right
                }
            }
        }

        return result;
    }

}
