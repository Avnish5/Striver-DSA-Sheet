package DAY_17_Binary_Tree_Part_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Preorder_Inorder_Postorder_In_A_Single_Traversal {
    List<Integer> preOrder = new ArrayList<>();
    List<Integer> inOrder = new ArrayList<>();
    List<Integer> postOrder = new ArrayList<>();

    void traverse(TreeNode root) {
        if (root == null) return;

        preOrder.add(root.val);
        traverse(root.left);

        inOrder.add(root.val);
        traverse(root.right);

        postOrder.add(root.val);

    }

    /**
     * 1. Using Recursion
     * <p>
     * Time Complexity:
     * - Each node is visited exactly once.
     * - At every node, we do O(1) work (adding to lists).
     * - Overall: O(n), where n = number of nodes.
     * <p>
     * Space Complexity:
     * - Output lists store 3 values per node → O(n).
     * - Recursion stack:
     * - Worst case (skewed tree): O(n).
     * - Best case (balanced tree): O(log n).
     * - Total: O(n) extra space overall.
     */
    List<List<Integer>> treeTraversal1(TreeNode root) {
        traverse(root);
        return Arrays.asList(preOrder, inOrder, postOrder);
    }

    /**
     * for the second method using stack follow below link:
     * https://takeuforward.org/data-structure/preorder-inorder-postorder-traversals-in-one-traversal/
     */
}
