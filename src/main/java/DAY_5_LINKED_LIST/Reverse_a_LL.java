package DAY_5_LINKED_LIST;

public class Reverse_a_LL {

    /**
     * 1.Using recursion.
     *
     * Time Complexity: O(n)
     * - Each recursive call processes one node.
     * - There are 'n' nodes, and each node is visited exactly once.
     *
     * Space Complexity: O(n)
     * - Recursive call stack stores one frame per node.
     * - Hence, O(n) additional space is used due to recursion.
     */
    public ListNode reverseLLRecursion(ListNode head) {
        // Base condition: if the list is empty or contains only one node,
        // return the head as it is already reversed.
        if (head == null || head.next == null) return head;

        // Recursively reverse the rest of the list starting from head.next.
        ListNode finalHead = reverseLLRecursion(head.next);

        // Reverse the current node's link:
        // The next node's next pointer should point back to the current node.
        head.next.next = head;

        // Break the original forward link to avoid cycle.
        head.next = null;

        // Return the head of the reversed list obtained from the recursion.
        return finalHead;
    }


    /**
     * 2.Using an iterative approach.
     *
     * Time Complexity: O(n)
     * - Each node is visited exactly once in the while loop.
     * - Therefore, total time taken is proportional to the number of nodes.
     *
     * Space Complexity: O(1)
     * - Only a constant number of pointers (prev, curr, next) are used.
     * - No extra data structures are needed.
     */
    public ListNode reverseLLIteration(ListNode head) {
        // Initialize two pointers:
        // 'prev' will eventually point to the new head of the reversed list.
        ListNode prev = null;
        // 'curr' is used to traverse the original list.
        ListNode curr = head;

        // Traverse the list until all nodes are processed.
        while (curr != null) {
            // Temporarily store the next node before changing the link.
            ListNode next = curr.next;

            // Reverse the current node's pointer.
            curr.next = prev;

            // Move 'prev' and 'curr' one step forward.
            prev = curr;
            curr = next;
        }

        // After the loop, 'prev' points to the new head of the reversed list.
        return prev;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
       ListNode head = null, tail = null;

       if (list1.val > list2.val) {
           head = list2;
           tail = list2;
           list2 = list2.next;
       } else {
           head = list1;
           tail = list1;
           list1 = list1.next;
       }

       while (list1 != null && list2 != null) {

           if (list1.val > list2.val) {
               tail.next = list2;
               tail = list2;
               list2 = list2.next;
           } else {
               tail.next = list1;
               tail = list1;
               list1 = list1.next;
           }
       }

       while (list1 != null) {
           tail.next = list1;
           tail = list1;
           list1 = list1.next;
       }
        while (list2 != null) {
            tail.next = list2;
            tail = list1;
            list1 = list2.next;
        }

        return head;

    }

}
