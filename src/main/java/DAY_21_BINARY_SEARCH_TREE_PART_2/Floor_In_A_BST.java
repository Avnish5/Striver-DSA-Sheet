package DAY_21_BINARY_SEARCH_TREE_PART_2;

public class Floor_In_A_BST {

    private int helper(TreeNode root, int x, int pa) {
        if (root == null) return pa; // base case: return the best candidate so far

        if (root.val == x) {
            // Case 1: exact match → x itself is the floor
            return x;
        }

        if (root.val > x) {
            // Case 2: current node is too big → check left subtree
            return helper(root.left, x, pa);
        }

        if (root.val < x) {
            // Case 3: current node is a valid candidate → update pa
            pa = root.val;
            // but check right subtree for a possibly larger valid floor
            return helper(root.right, x, pa);
        }

        return pa; // fallback (shouldn't be reached due to above conditions)
    }
    /**
     * Time Complexity: O(h)
     * - O(h), where h = height of the BST.
     * - In the worst case (skewed tree), h = O(n).
     * - In the best case (balanced tree), h = O(log n).
     *
     * Space Complexity:O(h)
     * - O(h) due to recursion stack.
     * - Worst case: O(n), Best case: O(log n).
     */

    public int floor1(TreeNode root, int x) {
        int pa = -1; // holds the best candidate (potential floor value)
        return helper(root, x, pa);
    }


    /**
 Question:related to floor2 solution
 ----------
 You are given a BST (Binary Search Tree) with 'n' nodes and a value 'x'.
 Your task is to find the greatest value node in the BST that is smaller
 than or equal to 'x' (floor of x). If no such node exists (x is smaller
 than the smallest node), return -1.

 Previous Attempt (Buggy Code):
 -------------------------------
 public static int floor(Node root, int x) {
     int pa = -1;
     Node curr = root;

     while (curr != null) {
         if (curr.data == x) return x;

         if (curr.data > x) {
             curr = curr.left;
         }

         if (curr.data < x) {   // ❌ PROBLEM: not in else block
             pa = curr.data;
             curr = curr.right;
         }
     }

     return pa;
 }

 Problem in Above Code:
 -----------------------
 - Both conditions (curr.data > x) and (curr.data < x) are written as
   independent `if` blocks.
 - After executing `curr = curr.left;`, `curr` may become null.
 - But the next `if (curr.data < x)` still runs, trying to access `curr.data`
   on a null → ❌ NullPointerException.

 Corrected Code:
 ---------------
 - Use `if ... else` so that only one branch executes per iteration.
 - This prevents accessing `curr.data` after reassigning `curr`.
  */

    /**
     * Time Complexity: O(h)
     * - O(h), where h = height of the BST.
     * - In the worst case (skewed tree), h = O(n).
     * - In the best case (balanced tree), h = O(log n).
     *
     * Space Complexity:O(1)
     */
    public static int floor(TreeNode root, int x) {
        int pa = -1;               // stores potential answer
        TreeNode curr = root;

        while (curr != null) {
            if (curr.val == x)
                return x;           // exact match → floor found

            if (curr.val < x) {
                pa = curr.val;     // update floor candidate
                curr = curr.right;  // move right for larger valid candidate
            } else {
                curr = curr.left;   // move left to find smaller value
            }
        }

        return pa;                  // final floor value or -1 if none exists
    }


}
