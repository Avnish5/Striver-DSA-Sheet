package DAY_5_LINKED_LIST;

public class Merge_Two_Sorted_Lists {

    /**
     *
     * Time Complexity: O(n + m)
     * - Each node from both lists is visited exactly once.
     * - 'n' = number of nodes in list1, 'm' = number of nodes in list2.
     * - The total time taken is proportional to the combined length of both lists.
     *
     * Space Complexity: O(1)
     * - The merge is performed in-place using existing nodes.
     * - Only a few pointer variables are used (head, tail, list1, list2).
     * - No additional data structures are created.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // If one of the lists is empty, return the other directly.
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Initialize head and tail pointers for the merged list.
        ListNode head = null;
        ListNode tail = null;

        // Determine the first node (smallest value) to start the merged list.
        if (list1.val > list2.val) {
            head = list2;
            tail = list2;
            list2 = list2.next;
        } else {
            head = list1;
            tail = list1;
            list1 = list1.next;
        }

        // Traverse both lists until one of them becomes empty.
        // Always attach the node with the smaller value to the merged list.
        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            } else {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            }
        }

        // Attach the remaining nodes from whichever list is not yet exhausted.
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        // Return the head of the merged sorted list.
        return head;
    }

}
