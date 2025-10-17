package DAY_5_LINKED_LIST;

public class Middle_of_LL {

    /**
     * Time Complexity: O(n)
     * - The list is traversed once.
     * - The 'fast' pointer moves two steps while 'slow' moves one step per iteration.
     * - Hence, total time taken is linear with respect to the number of nodes.
     *
     * Space Complexity: O(1)
     * - Only two pointers ('slow' and 'fast') are used regardless of the list size.
     * - No additional data structures are required.
     */
    public ListNode middleNode(ListNode head) {
        // Initialize two pointers starting at the head.
        // 'slow' moves one step at a time, 'fast' moves two steps at a time.
        ListNode slow = head;
        ListNode fast = head;

        // Continue until 'fast' reaches the end of the list.
        // When 'fast' is at the end, 'slow' will be at the middle.
        while (fast != null && fast.next != null) {
            slow = slow.next;         // move slow by one
            fast = fast.next.next;    // move fast by two
        }

        // When the loop ends, 'slow' points to the middle node.
        return slow;
    }

}
