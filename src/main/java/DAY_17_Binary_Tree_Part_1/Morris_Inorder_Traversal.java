package DAY_17_Binary_Tree_Part_1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Morris_Inorder_Traversal {

   /**
    * Note: See the video of Aryan Mittal on Youtube
    *
      Time Complexity (TC): O(n)
    - Each node is visited at most twice (once going down, once while removing thread).
    - So overall linear time.

     Space Complexity (SC): O(1)
    - We are not using recursion or stack.
    - Only a few pointer variables are used.

     This is the Morris Inorder Traversal algorithm.
   */
   public List<Integer> inorderTraversal(TreeNode root) {
       List<Integer> ans = new ArrayList<>();

       TreeNode curr = root;

       while (curr != null) {
           if (curr.left == null) {
               ans.add(curr.val);
               curr = curr.right;
           } else {
               TreeNode pre = curr.left;

               while (pre.right != null) {
                   pre = pre.right;
               }

               pre.right = curr;

               TreeNode temp = curr;
               curr = curr.left;
               temp.left = null;
           }
       }

       return ans;
   }

}
