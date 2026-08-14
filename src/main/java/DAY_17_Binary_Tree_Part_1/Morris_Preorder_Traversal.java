package DAY_17_Binary_Tree_Part_1;

import java.util.ArrayList;
import java.util.List;

public class Morris_Preorder_Traversal {

    /**
     * Note: See the video of Aryan Mittal on Youtube
     *
     Time Complexity (TC): O(n)
    - Each node is visited at most twice (once when creating thread, once when removing).
    - Total O(n).

     Space Complexity (SC): O(1)
    - No recursion, no stack.
    - Only pointers + result list.

      This is Morris Preorder Traversal.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;

        while(curr != null) {
            if(curr.left == null) {
                ans.add(curr.val);
                curr = curr.right;
            }else {
                ans.add(curr.val);
                TreeNode pre = curr.left;
                while(pre.right != null) {
                    pre = pre.right;
                }

                pre.right = curr.right;
                TreeNode temp = curr;
                curr = curr.left;
                temp.left= null;
            }
        }

        return ans;
    }

}
