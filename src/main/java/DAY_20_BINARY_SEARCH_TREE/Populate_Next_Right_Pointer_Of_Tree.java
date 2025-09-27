package DAY_20_BINARY_SEARCH_TREE;

import java.util.LinkedList;
import java.util.Queue;

public class Populate_Next_Right_Pointer_Of_Tree {

    /**
     * Time Complexity: O(n)
     *   - Each node is added to and removed from the queue exactly once.
     *   - Setting next pointers is O(1) per node.
     *
     * Space Complexity: O(n)
     *   - A queue is used for BFS. In the worst case (last level of the tree),
     *     the queue can hold up to ~n/2 nodes, which is O(n).
     */
    public Node connect(Node root) {
        if (root == null) return null; // handle empty tree

        Queue<Node> q = new LinkedList<>();
        q.add(root); // start BFS with the root

        while (!q.isEmpty()) {
            int levelSize = q.size(); // number of nodes in current level

            for (int i = 0; i < levelSize; i++) {
                Node cn = q.remove(); // get current node

                // If it's the last node of the level, next = null
                // Otherwise, next = node at front of queue
                if (i == levelSize - 1) {
                    cn.next = null;
                } else {
                    cn.next = q.peek();
                }

                // Add children to the queue for next level processing
                if (cn.left != null) q.add(cn.left);
                if (cn.right != null) q.add(cn.right);
            }
        }

        return root; // return root with updated next pointers
    }

}
