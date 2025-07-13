package DAY_6_LINKED_LIST_PART_2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Flattening_Of_LL {

 /*
    //1.Optimal
    Node flatten(Node root) {

      if (root == null) return null;;

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
    public int compare(Node a, Node b) {
        return a.data - b.data;  // Min-heap (ascending order)
    }
     });

     Node dummy = new Node(0);
     Node temp = dummy;

     for (Node curr = root; curr != null; curr = curr.next) {
         pq.add(curr);
     }

     while(!pq.isEmpty()) {
         Node minNode = pq.poll();
         temp.bottom = minNode;
         temp = minNode;

         if (minNode.bottom != null) {
             pq.offer(minNode.bottom);
         }

         minNode.bottom = null;

     }

     return dummy.bottom;

    }

   */



}
