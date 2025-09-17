package DAY_17_Binary_Tree_Part_1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Morris_Inorder_Traversal {

   /**
      Time Complexity (TC): O(n)
    - Each node is visited at most twice (once going down, once while removing thread).
    - So overall linear time.

     Space Complexity (SC): O(1)
    - We are not using recursion or stack.
    - Only a few pointer variables are used.

     This is the Morris Inorder Traversal algorithm.
   */
    public List<Integer> inorderTraversal(TreeNode root) {
        // Result list to store inorder traversal
        List<Integer> result = new ArrayList<>();

        // Current pointer starting from root
        TreeNode curr = root;

        // Traverse until current becomes null
        while (curr != null) {
            // Case 1: If left child does not exist, process current node and move to right child
            if (curr.left == null) {
                result.add(curr.val);   // Visit node
                curr = curr.right;      // Move right
            }
            // Case 2: Left child exists → Find inorder predecessor (IP)
            else {
                TreeNode IP = curr.left;
                // Move to the rightmost node in left subtree OR until we find a thread back to curr
                while (IP.right != null && IP.right != curr) {
                    IP = IP.right;
                }

                // Case 2a: If thread does not exist, create it and move to left child
                if (IP.right == null) {
                    IP.right = curr;    // Create thread to return back
                    curr = curr.left;   // Move left
                }
                // Case 2b: If thread exists, it means left subtree is already visited
                else {
                    result.add(curr.val);  // Visit current node
                    IP.right = null;       // Remove the thread
                    curr = curr.right;     // Move right
                }
            }
        }

        return result; // Return final inorder traversal list
    }

}
