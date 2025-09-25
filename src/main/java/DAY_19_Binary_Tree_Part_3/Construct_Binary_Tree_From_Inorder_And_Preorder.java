package DAY_19_Binary_Tree_Part_3;

import java.util.HashMap;

public class Construct_Binary_Tree_From_Inorder_And_Preorder {

    /**
     * Time Complexity: O(n)
     *   - Each node is created once.
     *   - HashMap lookup for inorder index is O(1).
     *   - Total traversal over all nodes = O(n).
     *
     * Space Complexity: O(n)
     *   - HashMap stores all inorder indices → O(n).
     *   - Recursion stack in worst case (skewed tree) → O(n).
     *   - So overall = O(n).
     */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Build HashMap for quick lookup of inorder index by value
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hm.put(inorder[i], i);
        }

        // Call recursive helper with full preorder and inorder ranges
        return buildTreeHelper(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1,
                hm);
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                                     int[] inorder, int inStart, int inEnd,
                                     HashMap<Integer, Integer> hm) {
        // Base case: no elements left to process
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // First element in preorder is the root of current subtree
        TreeNode root = new TreeNode(preorder[preStart]);

        // Find root index in inorder array
        int inRoot = hm.get(root.val);

        // Number of nodes in left subtree
        int leftTreeSize = inRoot - inStart;

        // Recursively build left subtree
        // preorder: [preStart+1 → preStart+leftTreeSize]
        // inorder:  [inStart → inRoot-1]
        root.left = buildTreeHelper(preorder,
                preStart + 1, preStart + leftTreeSize,
                inorder,
                inStart, inRoot - 1,
                hm);

        // Recursively build right subtree
        // preorder: [preStart+leftTreeSize+1 → preEnd]
        // inorder:  [inRoot+1 → inEnd]
        root.right = buildTreeHelper(preorder,
                preStart + leftTreeSize + 1, preEnd,
                inorder,
                inRoot + 1, inEnd,
                hm);

        return root;
    }


}


