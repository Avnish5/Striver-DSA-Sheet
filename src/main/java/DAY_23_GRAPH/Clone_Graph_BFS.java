package DAY_23_GRAPH;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Clone_Graph_BFS {

        // Map to store mapping from original node → cloned node
        // Helps to:
        // 1. Avoid cloning the same node multiple times
        // 2. Handle cycles in the graph
        HashMap<Node, Node> map = new HashMap<>();

        /**
         * Clones an undirected graph using DFS
         *
         * @param node the starting node of the graph
         * @return cloned graph's starting node
         *
         * Time Complexity: O(N)
         * - Each node is visited exactly once
         * - Each edge is traversed once
         *
         * Space Complexity: O(N)
         * - HashMap stores all N nodes
         * - Recursion stack can go up to N in worst case (deep graph)
         */
        public Node cloneGraph(Node node) {
            // Base case: empty graph
            if (node == null) return null;

            // If this node is already cloned, return the clone
            // This prevents infinite recursion in cyclic graphs
            if (map.containsKey(node)) {
                return map.get(node);
            }

            // Create a new cloned node with the same value
            Node clone = new Node(node.val);

            // Store the mapping before cloning neighbors
            map.put(node, clone);

            // Clone all neighbors recursively and add to cloned node
            for (Node neighbour : node.neighbors) {
                clone.neighbors.add(cloneGraph(neighbour));
            }

            // Return the cloned node
            return clone;
        }

}
