package DAY_20_BINARY_SEARCH_TREE;

public class Check_Is_A_BT_Is_BST_Or_Not {

    public boolean isValidBST(TreeNode root) {
         if (root == null || root.left == null && root.right == null) return true;

         if (root.left != null) {
             if (root.left.val > root.val) return false;
         }

        if (root.right != null) {
            if (root.right.val > root.val) return false;
        }

        return isValidBST(root.left) && isValidBST(root.right);
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int s, int e) {
        if (s < 0) return null;
        if (s > e) return null;

        TreeNode root = new TreeNode(preorder[s]);
        int t = root.val;
        int leftEnd = e + 1;

        for (int i = s; i <= e; i++) {
            if (preorder[i] > t) {
                leftEnd = i;
                break;
            }
        }

        root.left = helper(preorder, s + 1, leftEnd - 1);
        root.right = helper(preorder, leftEnd, e);

        return root;

    }
}
