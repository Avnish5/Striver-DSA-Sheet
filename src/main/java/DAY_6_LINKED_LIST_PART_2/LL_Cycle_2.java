package DAY_6_LINKED_LIST_PART_2;

import java.util.HashSet;

public class LL_Cycle_2 {

    //1. Brute-Force
    public ListNode detectCycle1(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();

        while (head != null) {
            if (visited.contains(head)) {
                return head;
            }

            visited.add(head);
            head = head.next;
        }

        return null;
    }

    //2. Optimal
    public ListNode detectCycle2(ListNode head) {
        if(head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        ListNode entry = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast =  fast.next.next;

            if (slow == fast) {
                while (slow != entry) {
                    slow = slow.next;
                    entry = entry.next;
                }

                return entry;
            }
        }

        return null;
    }
}
