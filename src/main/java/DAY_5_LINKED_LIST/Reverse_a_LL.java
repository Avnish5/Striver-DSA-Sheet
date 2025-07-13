package DAY_5_LINKED_LIST;

public class Reverse_a_LL {

    public ListNode reverseLLRecursion(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode finalHead = reverseLLRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return finalHead;
    }

    public ListNode reverseLLIteration(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
