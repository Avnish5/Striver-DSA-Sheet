package DAY_21_BINARY_SEARCH_TREE_PART_2;

import java.util.LinkedList;
import java.util.Queue;

public class Serialize_and_Deserialize_Binary_Tree {
    /**
     *  * ---------------------------------------------------------
     *  *
     *  * Time Complexity:
     *  *   serialize()   → O(N)
     *  *   deserialize() → O(N)
     *  *   (Each node processed once)
     *  *
     *  * Space Complexity:
     *  *   O(N)
     *  *   - Queue can hold up to N nodes.
     *  *   - Output string stores N elements.
     *  *
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null) return "#";

        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        q.add(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr == null) {
                sb.append("#,");
            } else {
                sb.append(curr.val).append(",");
                q.add(curr.left);
                q.add(curr.right);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data.equals("#")) return null;

        String[] values = data.split(",");

        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int i = 1;

        while (!q.isEmpty() && i < values.length) {

            TreeNode curr = q.poll();

            // Process left child
            if (!values[i].equals("#")) {
                TreeNode leftNode = new TreeNode(Integer.parseInt(values[i]));
                curr.left = leftNode;
                q.add(leftNode);
            }
            i++;

            // Process right child
            if (i < values.length && !values[i].equals("#")) {
                TreeNode rightNode = new TreeNode(Integer.parseInt(values[i]));
                curr.right = rightNode;
                q.add(rightNode);
            }
            i++;
        }

        return root;
    }
}
