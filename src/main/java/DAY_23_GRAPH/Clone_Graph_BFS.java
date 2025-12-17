package DAY_23_GRAPH;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Clone_Graph_BFS {

         /**
         * Clones an undirected graph using BFS
         *
         * Time Complexity: O(N)
         * - Each node is visited once
         * - Each edge is processed once
         *
         * Space Complexity: O(N)
         * - HashMap stores all cloned nodes
         * - Queue stores nodes during BFS
         */
        public Node cloneGraph(Node node) {
            // Edge case: empty graph
            if (node == null) return null;

            // Map: original node → cloned node
            Map<Node, Node> map = new HashMap<>();

            // Queue for BFS traversal
            Queue<Node> q = new LinkedList<>();

            // Create clone of starting node
            Node cloneStart = new Node(node.val);
            map.put(node, cloneStart);
            q.add(node);

            // BFS traversal
            while (!q.isEmpty()) {
                Node curr = q.poll();

                // Traverse neighbors
                for (Node neighbour : curr.neighbors) {

                    // If neighbor is not cloned yet
                    if (!map.containsKey(neighbour)) {
                        map.put(neighbour, new Node(neighbour.val));
                        q.add(neighbour);
                    }

                    // Add cloned neighbor to current cloned node
                    map.get(curr).neighbors.add(map.get(neighbour));
                }
            }

            // Return cloned graph start node
            return cloneStart;
        }

}
