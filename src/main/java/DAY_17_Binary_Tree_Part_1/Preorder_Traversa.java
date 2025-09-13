package DAY_17_Binary_Tree_Part_1;

import java.util.ArrayList;
import java.util.List;

public class Preorder_Traversa {

    /**
     * Preorder Traversal of a Binary Tree (Recursive)
     *
     * Time Complexity: O(n)
     *  - We visit each node exactly once, where n is the number of nodes in the tree.
     *
     * Space Complexity: O(n)
     *  - The output list stores all node values, requiring O(n) space.
     *  - The recursion stack space depends on the height of the tree:
     *    - O(n) in the worst case (skewed tree),
     *    - O(log n) in the best case (balanced tree).
     *  - Overall space complexity is dominated by the output list, O(n).
     */
    public List<Integer> preorderTraversal(TreeNode root) {
           List<Integer> ans = new ArrayList<>();
           helper(root, ans);
           return ans;
    }

    private void helper(TreeNode root, List<Integer> ans) {
        if(root == null) return;
        ans.add(root.val);
        helper(root.left, ans);
        helper(root.right, ans);
    }
}
