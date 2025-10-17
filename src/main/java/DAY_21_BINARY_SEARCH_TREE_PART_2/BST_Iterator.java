package DAY_21_BINARY_SEARCH_TREE_PART_2;

import java.util.Stack;

public class BST_Iterator {

    Stack<TreeNode> s;
    public BST_Iterator(TreeNode root) {
       s = new Stack<>();
        TreeNode node = root;

        while (node != null) {
            s.add(node);
            node = node.left;
        }
    }

    public int next() {
         TreeNode node = s.pop();
         int val = node.val;

        while (node != null) {
            s.add(node);
            node = node.right;
        }

        return val;
    }

    public boolean hasNext() {
     return !s.isEmpty();
    }
}
