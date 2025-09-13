package DAY_17_Binary_Tree_Part_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Inorder_Traversal {

    /**
     * Inorder Traversal of a Binary Tree (Recursive)
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

    public List<Integer> inorderTraversalRecursion1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        return helper(root, ans);
    }

    private List<Integer> helper(TreeNode root, List<Integer> ans) {
        if (root == null) return ans;
        helper(root.left, ans);
        ans.add(root.val);
        helper(root.right, ans);
        return ans;

    }
}
