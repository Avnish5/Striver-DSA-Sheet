package DAY_19_Binary_Tree_Part_3;

import java.util.HashMap;
import java.util.List;

public class Construct_Binary_Tree_From_Inorder_And_Postorder {

    /**
     * Time Complexity: O(n)
     *   - Each node is created once.
     *   - HashMap lookup for inorder index is O(1).
     *   - Overall traversal of all nodes is linear in size of input.
     *
     * Space Complexity: O(n)
     *   - HashMap stores all inorder indices → O(n).
     *   - Recursion stack can go up to O(n) in the worst case (skewed tree).
     *   - So total = O(n).
     */

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Build a HashMap for quick lookup of inorder index by value
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hm.put(inorder[i], i);
        }

        // Call recursive helper with full inorder and postorder ranges
        return buildTreeHelper(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1,
                hm);
    }

    private TreeNode buildTreeHelper(int[] inorder, int inStart, int inEnd,
                                     int[] postorder, int postStart, int postEnd,
                                     HashMap<Integer, Integer> hm) {
        // Base case: no elements left in inorder or postorder range
        if (inStart > inEnd || postStart > postEnd) return null;

        // Last element in postorder is the root of current subtree
        TreeNode root = new TreeNode(postorder[postEnd]);

        // Find root index in inorder array
        int inRoot = hm.get(root.val);

        // Number of nodes in left subtree
        int numLeft = inRoot - inStart;

        // Recursively build left subtree
        // inorder: left half [inStart → inRoot-1]
        // postorder: left half [postStart → postStart+numLeft-1]
        root.left = buildTreeHelper(inorder, inStart, inRoot - 1,
                postorder, postStart, postStart + numLeft - 1,
                hm);

        // Recursively build right subtree
        // inorder: right half [inRoot+1 → inEnd]
        // postorder: right half [postStart+numLeft → postEnd-1]
        root.right = buildTreeHelper(inorder, inRoot + 1, inEnd,
                postorder, postStart + numLeft, postEnd - 1,
                hm);

        return root;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
      int ans = Integer.MAX_VALUE;
      int temp = 0;

      for (List<Integer> list : triangle) {
          int min = Integer.MAX_VALUE;
          for (int i : list) {
              min = Math.min(min, i);
          }
          temp += min;
          ans = Math.max(temp, min);
        }

      return ans;
    }

}
