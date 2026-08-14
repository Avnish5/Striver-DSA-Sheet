package DAY_19_Binary_Tree_Part_3;

import java.util.LinkedList;
import java.util.Queue;

public class IS_Symmetric {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);

        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();

            // both null -> continue
            if (t1 == null && t2 == null) {
                continue;
            }

            // one null -> not symmetric
            if (t1 == null || t2 == null) {
                return false;
            }

            // values differ
            if (t1.val != t2.val) {
                return false;
            }

            // mirror order
            q.add(t1.left);
            q.add(t2.right);

            q.add(t1.right);
            q.add(t2.left);
        }

        return true;
    }
}
