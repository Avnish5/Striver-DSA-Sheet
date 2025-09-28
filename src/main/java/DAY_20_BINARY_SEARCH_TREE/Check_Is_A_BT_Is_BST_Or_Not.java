package DAY_20_BINARY_SEARCH_TREE;

public class Check_Is_A_BT_Is_BST_Or_Not {

   public int maximum(TreeNode root) {
       if (root == null) return Integer.MIN_VALUE;

       return Math.max(root.val, Math.max(maximum(root.left), maximum(root.right)));
   }

    public int minimum(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;

        return Math.min(root.val, Math.min(minimum(root.left), minimum(root.right)));
    }

    /**
     * 1. Recursion
     *
     * Time Complexity: O(n^2)
     *   - Each node is added to and removed from the queue exactly once.
     *   - Setting next pointers is O(1) per node.
     *
     * Space Complexity: O(h)
     */
    public boolean isValidBST1(TreeNode root) {
       if (root == null) return true;

       int leftMax = maximum(root.left);
       int rightMin = minimum(root.right);

       if (leftMax >= root.val) return false;
       if (rightMin <= root.val) return false;

       return isValidBST1(root.left) && isValidBST1(root.right);

    }

    /**
     * Q: Why do we use long for min and max instead of int in isValidBST?
     *
     * A: If we use int, edge cases with Integer.MIN_VALUE (-2,147,483,648) and
     *    Integer.MAX_VALUE (2,147,483,647) can break the logic.
     *
     *    Example:
     *        Tree: [2147483647]   (just a single node with Integer.MAX_VALUE)
     *
     *    If we call helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE):
     *        root.val = 2147483647
     *        max      = 2147483647
     *        Check: root.val >= max → true → returns false
     *
     *    But this single-node tree is a valid BST!
     *
     *    By using long bounds:
     *        helper(root, Long.MIN_VALUE, Long.MAX_VALUE)
     *
     *    Now:
     *        root.val = 2147483647
     *        max      = 9223372036854775807
     *        root.val >= max → false → correct result.
     *
     * Summary:
     *    - Nodes store int values.
     *    - Bounds use long to avoid overflow/underflow and handle edge cases safely.
     */

    public boolean isValidBST2(TreeNode root,long min,long max){
        if(root==null) return true;

        if(root.val<=min || root.val>=max) return false;

        return isValidBST2(root.left,min,root.val)&&isValidBST2(root.right,root.val,max);
    }

    /**
     * 2. Recursion with better TC
     *
     * Time Complexity: O(n)
     *
     * Space Complexity: O(h)
     */
    public boolean isValidBST2(TreeNode root) {

        return isValidBST2(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }


}
